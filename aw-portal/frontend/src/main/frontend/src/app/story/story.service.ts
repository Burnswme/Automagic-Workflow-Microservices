import { AwStory } from './../domain/aw-story';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { AwSwimlane } from './../domain/aw-swimlane';
import 'rxjs/add/operator/retry';
import { BackendService } from './../backend.service';

@Injectable()
export class StoryService {
  zuulUrl: string = "http://localhost:8765";

  constructor(private http: HttpClient, private backend: BackendService) {}

  getStories(swimlaneId: number): Observable<AwStory[]> {
    return this.backend.get<AwStory[]>("/aw_story/getStories/" + swimlaneId);
  }
  createStory(st: AwStory): Observable<AwStory> {
    return this.http.post<AwStory>("/aw_story/createStory?boardId="+localStorage.getItem("currentBoardId"), st)
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
