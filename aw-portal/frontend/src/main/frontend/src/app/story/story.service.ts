import { AwStory } from './../domain/aw-story';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/retry';

@Injectable()
export class StoryService {
  zuulUrl: string = "http://localhost:8765";

  constructor(private http: HttpClient) {}

  getStories(swimlaneId: number): Observable<AwStory[]> {
    return this.http.get<AwStory[]>(this.zuulUrl + "/aw_story/getStories/" + swimlaneId)
      .retry(5);
  }

}
