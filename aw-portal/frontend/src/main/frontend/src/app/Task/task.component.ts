import { AwTask } from './../domain/aw-task';
import { AwStory } from './../domain/aw-story';
import { Component, OnInit, Input } from '@angular/core';
import { TaskService } from './task.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AwBoard } from './../domain/aw-board';
import { HistoryService } from './../history.service';

@Component({
  selector: 'aw-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
  @Input() task: AwTask;
  @Input() story: AwStory;
  @Input() board: AwBoard;
  tsk2: AwTask; //task to be displaced when moving up/down

  newTask: AwTask = {
    id: 0,
    name: "",
    completed: false,
    order: 0,
    storyId: 0
  }
  constructor(private ts: TaskService,
              private historyService: HistoryService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.newTask.name = this.task.name;
    this.newTask.completed = this.task.completed;
  }

  moveUp() {
    var ogOrder = this.task.order;
    this.task.order = ogOrder-1;
    this.tsk2 = this.story.tasks[ogOrder-1];
    this.tsk2.order = ogOrder;
    this.story.tasks[ogOrder] = this.tsk2;
    this.story.tasks[ogOrder-1] = this.task;
    this.ts.updateTask(this.task).subscribe(tsk => {
      this.historyService.createHistory(" has moved task [" + tsk.name + "] up and [" + this.tsk2.name + "] down").subscribe(hist => {
        this.board.history.unshift(hist);
      })
    });
    this.ts.updateTask(this.tsk2).subscribe();
  }
  
  moveDown() {
    var ogOrder = this.task.order;
    this.task.order = ogOrder+1;
    this.tsk2 = this.story.tasks[ogOrder+1];
    this.tsk2.order = ogOrder;
    this.story.tasks[ogOrder] = this.tsk2;
    this.story.tasks[ogOrder+1] = this.task;
    this.ts.updateTask(this.task).subscribe(tsk => {
      this.historyService.createHistory(" has moved task [" + tsk.name + "] down and [" + this.tsk2.name + "] up").subscribe(hist => {
        this.board.history.unshift(hist);
      })
    });
    this.ts.updateTask(this.tsk2).subscribe();
  }

  deleteTask(task: AwTask) {
    this.ts.deleteTask(task).subscribe((b: Boolean) => {
      this.story.tasks = this.story.tasks.filter(obj => {
        return obj.id !== task.id;
      });
      this.story.tasks.forEach(obj => {
        if(obj.order > task.order) {
          obj.order -= 1;
          this.ts.updateTask(obj).subscribe();
        }
      });
      this.historyService.createHistory(" has deleted task [" + this.task.name + "]").subscribe(hist => {
        this.board.history.unshift(hist);
      });
    })
  }

  save() {
    var oldName = this.task.name;
    var oldCompleted = this.task.completed;
    this.task.name = this.newTask.name;
    this.task.completed = this.newTask.completed;
    this.ts.updateTask(this.task).subscribe(tsk => {
      this.historyService.createHistory(" has updated a task from (" + oldName + ", " + oldCompleted + ") to (" +
      tsk.name + ", " + tsk.completed + ")").subscribe(hist => {
        this.board.history.unshift(hist);
      });
    });
  }
}
