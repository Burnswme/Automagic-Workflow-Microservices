import { AwTask } from './../domain/aw-task';
import { AwStory } from './../domain/aw-story';
import { Component, OnInit, Input } from '@angular/core';
import { TaskService } from './task.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'aw-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
  @Input() task: AwTask;
  @Input() story: AwStory;

  constructor(private ts: TaskService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {}

  moveUp() {}
  
  moveDown() {}

}
