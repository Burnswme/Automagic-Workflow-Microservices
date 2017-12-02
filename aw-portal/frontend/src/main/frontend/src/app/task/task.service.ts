import { AwTask } from './../domain/aw-task';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/retry';
import { BackendService } from '../backend.service';

@Injectable()
export class TaskService {
    zuulUrl: string = "http://localhost:8765";
  
    constructor(private http: HttpClient, private backend: BackendService) {}
  
    getTasks(storyId: number): Observable<AwTask[]> {
      return this.backend.get<AwTask[]>("/tasks/getTasksByStoryId/" + storyId);
    }

    createTask(task: AwTask): Observable<AwTask> {
        return this.backend.post<AwTask>("/tasks/saveTask", task);
    }

    updateTask(task: AwTask): Observable<AwTask> {
        return this.backend.post<AwTask>("/tasks/updateTask", task);
    }

    deleteTask(task: AwTask): Observable<Boolean> {
        return this.backend.post<Boolean>("/tasks/deleteTask", task);
    }
}
