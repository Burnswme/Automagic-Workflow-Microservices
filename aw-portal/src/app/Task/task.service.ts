import { AwTask } from './../domain/aw-task';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/retry';
import { BackendService } from '../backend.service';
import { environment } from '../../environments/environment';

@Injectable()
export class TaskService {
    tasksPath: string = environment.tasksPath;
  
    constructor(private http: HttpClient, private backend: BackendService) {}
  
    getTasks(storyId: number): Observable<AwTask[]> {
      return this.backend.get<AwTask[]>(this.tasksPath + "/getTasksByStoryId/" + storyId);
    }

    createTask(task: AwTask): Observable<AwTask> {
        return this.backend.post<AwTask>(this.tasksPath + "/saveTask", task);
    }

    updateTask(task: AwTask): Observable<AwTask> {
        return this.backend.post<AwTask>(this.tasksPath + "/updateTask", task);
    }

    deleteTask(task: AwTask): Observable<Boolean> {
        return this.backend.post<Boolean>(this.tasksPath + "/deleteTask", task);
    }
}
