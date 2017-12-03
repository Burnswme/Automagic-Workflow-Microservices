import { AwStory } from './../domain/aw-story';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/retry';
import { AwSwimlane } from '../domain/aw-swimlane';
import { BackendService } from './../backend.service';

@Injectable()
export class StoryService {
  zuulUrl: string = "http://localhost:8765";

  constructor(private http: HttpClient, private backend: BackendService) {}

  getStories(swimlaneId: number): Observable<AwStory[]> {
    return this.backend.get<AwStory[]>("/aw_story/getStories/" + swimlaneId);
  }
  createStory(st: AwStory): Observable<AwStory> {
    return this.backend.post<AwStory>("/aw_story/createStory", st);
  }
  
  updateStory(st: AwStory): Observable<AwStory> {
    return this.backend.post<AwStory>("/aw_story/updateStory", st);
  }

  deleteStory(st: AwStory): Observable<Boolean> {
    return this.backend.post<Boolean>("/aw_story/deleteStory", st);
  }

  getOtherSwimlanes(swimlaneId: number): Observable<AwSwimlane[]> {
    return this.backend.get<AwSwimlane[]>("/swimlane-service/getOtherSwimlanes/" + localStorage.getItem("currentBoardId") + 
    "/" + swimlaneId);
  }
}
