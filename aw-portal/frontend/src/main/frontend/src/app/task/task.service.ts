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

}
