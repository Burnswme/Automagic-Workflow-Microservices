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
  newTitle: string;
  newDesc: string;
  newPoints: number;
  isCompleted: boolean;

  st2: AwStory; //story that is displaced(i.e. not the one being moved) when you move a story
  activity: AwHistory;

  newTask: AwTask = {
    id: 0,
    storyId: 0,
    name: "",
    completed: false,
    order: 0
  }

  constructor(private sts: StoryService,
              private ts: TaskService,
              private historyService: HistoryService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.ts.getTasks(this.story.id).subscribe((tasks: AwTask[]) => {
      this.story.tasks = tasks;
    });
    //set them to the actual values by default so they'll show up with 'default' values in the edit screen
    this.newTitle = this.story.title;
    this.newDesc = this.story.description;
    this.newPoints = this.story.points;
    if(this.story.timeCompleted != null) {
      this.isCompleted = true;
    }
    else { 
      this.isCompleted = false;
    }
  }

  updateStory() {
    console.log("UPDATE STORY");
    console.log(this.newTitle);
    console.log(this.newDesc);
    console.log(this.newPoints);
    console.log(this.isCompleted);
    var oldTitle = this.story.title;
    var oldDesc = this.story.description;
    var oldPoints = this.story.points;
    var oldStatus;
    if(this.story.timeCompleted !== null) {
      oldStatus = true;
    } 
    else {
      oldStatus = false;
    }

    this.story.title = this.newTitle;
    this.story.description = this.newDesc;
    this.story.points = this.newPoints;
    if(this.isCompleted == true) {
      this.story.timeCompleted = new Date();
    }
    else {
      this.story.timeCompleted = null;
    }
    console.log(this.story);
    this.sts.updateStory(this.story).subscribe(st => {
      this.historyService.createHistory(" has updated a story from (" + oldTitle + ", " + oldDesc + ", " + oldPoints + ", " + oldStatus
    + " to (" + this.story.title + ", " + this.story.description + ", " + this.story.points + ", " + this.isCompleted + ")").subscribe(hist => {
        //push to history
      });
    });
  }

  moveUp() {
    console.log(this.swimlane);
    var ogOrder = this.story.order;
    this.story.order = ogOrder - 1;
    this.st2 = this.swimlane.stories[this.story.order];
    this.st2.order = ogOrder;
    this.swimlane.stories[ogOrder] = this.st2;
    this.swimlane.stories[ogOrder-1] = this.story;
    this.sts.updateStory(this.story).subscribe(st => {
      this.historyService.createHistory(" has moved story " + st.title + " up and " + this.st2 + " down").subscribe(hist => {
        //push to history
      });
    });
    this.sts.updateStory(this.st2).subscribe();
  }
  
  moveDown() {
    console.log(this.swimlane);
    var ogOrder = this.story.order;
    this.story.order = ogOrder + 1;
    this.st2 = this.swimlane.stories[this.story.order];
    this.st2.order = ogOrder;
    this.swimlane.stories[ogOrder] = this.st2;
    this.swimlane.stories[ogOrder+1] = this.story;
    this.sts.updateStory(this.story).subscribe(st => {
      this.historyService.createHistory(" has moved story " + st.title + " down and " + this.st2 + " up").subscribe(hist => {
        //push to history
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
      this.historyService.createHistory(" has deleted story " + st.title).subscribe(hist => {
        //push to history
      });
    })
  }

  loadOtherSwimlanes(swimlaneId: AwSwimlane) {
    console.log(this.board);
    this.sts.getOtherSwimlanes(this.swimlane.id).subscribe((swimlanes: AwSwimlane[]) => {
      this.otherSwimlanes = swimlanes;
      this.otherSwimlanes.forEach(sl => {
        this.sts.getStories(sl.id).subscribe((stories: AwStory[]) => {
          sl.stories = stories;
        })
      })
    })
    
    console.log(this.otherSwimlanes);
  }

  createTask(task: AwTask) {
    console.log("CREATE TASK");
    
    task.order = (this.story.tasks) ? this.story.tasks.length : 0;
    task.storyId = this.story.id;

    console.log(task);
    this.ts.createTask(task).subscribe((result: AwTask) => {
      this.story.tasks.push(result);
      this.newTask = {
        id: 0,
        storyId: 0,
        name: "",
        completed: false,
        order: 0,
      }
    })
  }
  
  //takes in id of new swimlane, new order aka last in the new swimlane, and the place of the swimlane
  moveTo(swimlaneId: number, newOrder: number, slOrder: number) {
    console.log("MOVE TO: " + swimlaneId);
    
    
    
    this.board.swimlanes[this.swimlane.order].stories = this.board.swimlanes[this.swimlane.order].stories.filter(obj => {
      return obj.id !== this.story.id;
    })
    console.log(this.board.swimlanes[this.swimlane.order].stories);
    console.log("REDUCING");
    console.log("BASE STORY");
    console.log(this.story);
    this.board.swimlanes[this.swimlane.order].stories.forEach(obj => {
      console.log("CURRENT STORY");
      console.log(obj);
      if(obj.order > this.story.order) {
        console.log("REDUCE");
        obj.order -= 1;
        this.sts.updateStory(obj).subscribe();
      }
    });
    this.story.swimlaneId = swimlaneId;
    this.story.order = newOrder;

    this.sts.updateStory(this.story).subscribe();
    this.board.swimlanes[slOrder].stories[newOrder] = this.story;
  }
}
