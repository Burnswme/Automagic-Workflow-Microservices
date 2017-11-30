import { AwStory } from './../domain/aw-story';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { AwSwimlane } from './../domain/aw-swimlane';
import 'rxjs/add/operator/retry';

@Injectable()
export class StoryService {
  zuulUrl: string = "http://localhost:8765";

  constructor(private http: HttpClient) {}

  getStories(swimlaneId: number): Observable<AwStory[]> {
    return this.http.get<AwStory[]>(this.zuulUrl + "/aw_story/getStories/" + swimlaneId)
      .retry(5);
  }
  createStory(st: AwStory): Observable<AwStory> {
    return this.http.post<AwStory>(this.zuulUrl + "/aw_story/createStory?boardId="+localStorage.getItem("currentBoardId"), st)
      .retry(5);
  }

  updateStory(st: AwStory): Observable<AwStory> {
    return this.http.post<AwStory>(this.zuulUrl + "/aw_story/updateStory?boardId="+localStorage.getItem("currentBoardId"), st)
      .retry(5);
  }

  deleteStory(st: AwStory): Observable<Boolean> {
    return this.http.post<Boolean>(this.zuulUrl + "/aw_story/deleteStory?boardId="+localStorage.getItem("currentBoardId"), st)
      .retry(5);
  }

  getOtherSwimlanes(swimlaneId: number): Observable<AwSwimlane[]> {
    return this.http.get<AwSwimlane[]>(this.zuulUrl + "/swimlane-service/getOtherSwimlanes/" + localStorage.getItem("currentBoardId") + 
    "/" + swimlaneId).retry(5);
  }
}
