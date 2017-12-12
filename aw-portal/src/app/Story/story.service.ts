import { AwStory } from './../domain/aw-story';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/retry';
import { AwSwimlane } from '../domain/aw-swimlane';
import { BackendService } from './../backend.service';
import { environment } from '../../environments/environment';

@Injectable()
export class StoryService {
  storiesPath: string = environment.storiesPath;

  constructor(private http: HttpClient, private backend: BackendService) {}

  getStories(swimlaneId: number): Observable<AwStory[]> {
    return this.backend.get<AwStory[]>(this.storiesPath + "/getStories/" + swimlaneId);
  }
  createStory(st: AwStory): Observable<AwStory> {
    return this.backend.post<AwStory>(this.storiesPath + "/createStory", st);
  }
  
  updateStory(st: AwStory): Observable<AwStory> {
    return this.backend.post<AwStory>(this.storiesPath + "/updateStory", st);
  }

  deleteStory(st: AwStory): Observable<Boolean> {
    return this.backend.post<Boolean>(this.storiesPath + "/deleteStory", st);
  }
}
