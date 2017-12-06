import { AwTask } from './../domain/aw-task';
import { TaskService } from './../task/task.service';
import { AwStory } from './../domain/aw-story';
import { Component, OnInit, Input } from '@angular/core';
import { StoryService } from './story.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AwSwimlane } from '../domain/aw-swimlane';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AwBoard } from './../domain/aw-board';
import { AwHistory } from '../domain/aw-history';
import { HistoryService } from './../history.service';

@Component({
  selector: 'aw-story',
  templateUrl: './story.component.html',
  styleUrls: ['./story.component.css']
})
export class StoryComponent implements OnInit {
  @Input() story: AwStory;
  @Input() swimlane: AwSwimlane;
  @Input() board: AwBoard;

  otherSwimlanes: AwSwimlane[];
  //variables used for updating
  //only ones that can be edited will be used(i.e. not all fields will be listed)
  editor: AwStory;
  isCompleted: boolean;

  st2: AwStory; //story that is displaced(i.e. not the one being moved) when you move a story
  activity: AwHistory;

  newTask: AwTask = new AwTask();

  constructor(private sts: StoryService,
              private ts: TaskService,
              private historyService: HistoryService,
              private route: ActivatedRoute,
              private router: Router) {}

  ngOnInit() {
    this.ts.getTasks(this.story.id).subscribe((tasks: AwTask[]) => {
      this.story.tasks = tasks;
    });
    //set them to the actual values by default so they'll show up with 'default' values in the edit screen
    if(this.story.timeCompleted != null) {
      this.isCompleted = true;
    }
    else { 
      this.isCompleted = false;
    }
  }

  setEditor() {
    this.editor = {...this.story};
  }

  updateStory() {
    this.editor.timeCompleted = (this.isCompleted) ? Date.now() : null;
    this.sts.updateStory(this.editor).subscribe(st => {
      this.historyService.createHistory(" has updated a story from (" + this.story.title + ", " + this.story.description + ", " + this.story.points + ", " + (this.story.timeCompleted != null)
    + ") to (" + this.editor.title + ", " + this.editor.description + ", " + this.editor.points + ", " + this.isCompleted + ")").subscribe(hist => {
        this.board.history.unshift(hist);
        this.story = {...this.editor};
      });
    });
  }

  moveUp() {
    var ogOrder = this.story.order;
    this.story.order = ogOrder - 1;
    this.st2 = this.swimlane.stories[this.story.order];
    this.st2.order = ogOrder;
    this.swimlane.stories[ogOrder] = this.st2;
    this.swimlane.stories[ogOrder-1] = this.story;
    this.sts.updateStory(this.story).subscribe(st => {
      this.historyService.createHistory(" has moved story [" + st.title + "] up and [" + this.st2.title + "] down").subscribe(hist => {
        this.board.history.unshift(hist);
      });
    });
    this.sts.updateStory(this.st2).subscribe();
  }
  
  moveDown() {
    var ogOrder = this.story.order;
    this.story.order = ogOrder + 1;
    this.st2 = this.swimlane.stories[this.story.order];
    this.st2.order = ogOrder;
    this.swimlane.stories[ogOrder] = this.st2;
    this.swimlane.stories[ogOrder+1] = this.story;
    this.sts.updateStory(this.story).subscribe(st => {
      this.historyService.createHistory(" has moved story [" + st.title + "] down and [" + this.st2.title + "] up").subscribe(hist => {
        this.board.history.unshift(hist);
      });
    });
    this.sts.updateStory(this.st2).subscribe();
  }

  deleteStory(st: AwStory) {
    this.sts.deleteStory(st).subscribe((b: Boolean) => {
      this.swimlane.stories = this.swimlane.stories.filter(obj => {
        return obj.id !== st.id;
      });
      this.swimlane.stories.forEach(obj => {
        if(obj.order > st.order) {
          obj.order -= 1;
          this.sts.updateStory(obj).subscribe();
        }
      });
      this.historyService.createHistory(" has deleted story [" + st.title + "]").subscribe(hist => {
        this.board.history.unshift(hist);
      });
    })
  }

  loadOtherSwimlanes(): AwSwimlane[] {
    return this.board.swimlanes.filter(obj => {
      return obj.id !== this.swimlane.id;
    });
  }

  createTask(task: AwTask) {
    task.order = (this.story.tasks) ? this.story.tasks.length : 0;
    task.storyId = this.story.id;

    this.ts.createTask(task).subscribe((result: AwTask) => {
      this.story.tasks.push(result);
      this.newTask = new AwTask();
      
      this.historyService.createHistory(" has created a task [" + result.name + "]").subscribe(hist => {
        this.board.history.unshift(hist);
      });
    });
     
  }
  
  //takes in id of new swimlane, new order aka last in the new swimlane, and the place of the swimlane
  moveTo(swimlaneId: number, newOrder: number, slOrder: number) {
    var oldSwimlane = this.swimlane.name;
    var newSwimlane = this.board.swimlanes[slOrder].name;
    
    this.board.swimlanes[this.swimlane.order].stories = this.board.swimlanes[this.swimlane.order].stories.filter(obj => {
      return obj.id !== this.story.id;
    })
    this.board.swimlanes[this.swimlane.order].stories.forEach(obj => {
      if(obj.order > this.story.order) {
        obj.order -= 1;
        this.sts.updateStory(obj).subscribe();
      }
    });
    this.story.swimlaneId = swimlaneId;
    this.story.order = newOrder;

    this.sts.updateStory(this.story).subscribe(obj => {
      this.historyService.createHistory(" has moved the story [" + this.story.title + "] from swimlane [" + oldSwimlane + "] to [" 
    + newSwimlane + "]").subscribe(hist => {
      this.board.history.unshift(hist);
    })
    });
    this.board.swimlanes[slOrder].stories[newOrder] = this.story;
  }
}
