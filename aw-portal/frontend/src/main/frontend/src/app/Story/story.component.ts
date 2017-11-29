import { AwTask } from './../domain/aw-task';
import { TaskService } from './../task/task.service';
import { AwStory } from './../domain/aw-story';
import { Component, OnInit, Input } from '@angular/core';
import { StoryService } from './story.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AwSwimlane } from '../domain/aw-swimlane';

@Component({
  selector: 'aw-story',
  templateUrl: './story.component.html',
  styleUrls: ['./story.component.css']
})
export class StoryComponent implements OnInit {
  @Input() story: AwStory;
  @Input() swimlane: AwSwimlane;
  
  //variables used for updating
  //only ones that can be edited will be used(i.e. not all fields will be listed)
  newTitle: string;
  newDesc: string;
  newPoints: number;
  isCompleted: boolean;

  st2: AwStory; //story that is displaced(i.e. not the one being moved) when you move a story

  constructor(private sts: StoryService,
              private ts: TaskService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.ts.getTasks(this.swimlane.id).subscribe((tasks: AwTask[]) => {
      this.story.tasks = tasks;
    });
    //set them to the actual values by default so they'll show up with 'default' values in the edit screen
    this.newTitle = this.story.title;
    this.newDesc = this.story.description;
    this.newPoints = this.story.points;
    this.isCompleted = false;
  }

  updateStory() {
    console.log("UPDATE STORY");
    console.log(this.newTitle);
    console.log(this.newDesc);
    console.log(this.newPoints);
    console.log(this.isCompleted);
    this.story.title = this.newTitle;
    this.story.description = this.newDesc;
    this.story.points = this.newPoints;
    if(this.isCompleted == true) {
      this.story.timeCompleted = new Date().getDate();
    }
    this.sts.updateStory(this.story).subscribe();
  }

  moveUp() {
    console.log(this.swimlane);
    var ogOrder = this.story.order;
    this.story.order = ogOrder - 1;
    this.st2 = this.swimlane.stories[this.story.order];
    this.st2.order = ogOrder;
    this.swimlane.stories[ogOrder] = this.st2;
    this.swimlane.stories[ogOrder-1] = this.story;
    this.sts.updateStory(this.story).subscribe();
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
    this.sts.updateStory(this.story).subscribe();
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
      })
    })
  }
}
