import { AwTask } from './../domain/aw-task';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/retry';

@Injectable()
export class TaskService {
    zuulUrl: string = "http://localhost:8765";
  
    constructor(private http: HttpClient) {}
  
    getTasks(storyId: number): Observable<AwTask[]> {
      return this.http.get<AwTask[]>(this.zuulUrl + "/tasks/getTasksByStoryId/" + storyId)
        .retry(5);
    }

    createTask(task: AwTask): Observable<AwTask> {
        return this.http.post<AwTask>(this.zuulUrl + "/tasks/saveTask?boardId="+localStorage.getItem("currentBoardId"), task)
            .retry(5);
    }

    updateTask(task: AwTask): Observable<AwTask> {
        return this.http.post<AwTask>(this.zuulUrl + "/tasks/updateTask?boardId="+localStorage.getItem("currentBoardId"), task)
            .retry(5);
    }

    deleteTask(task: AwTask): Observable<Boolean> {
        console.log("DELETING");
        console.log(task);
        return this.http.post<Boolean>(this.zuulUrl + "/tasks/deleteTask?boardId="+localStorage.getItem("currentBoardId"), task)
            .retry(5);
    }
}
