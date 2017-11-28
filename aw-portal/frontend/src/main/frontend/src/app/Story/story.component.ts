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

  constructor(private sts: StoryService,
              private ts: TaskService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.ts.getTasks(this.swimlane.id).subscribe((tasks: AwTask[]) => {
      this.story.tasks = tasks;
    });
  }

  moveUp() {}
  
  moveDown() {}

}
