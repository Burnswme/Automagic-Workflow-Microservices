import { AwSwimlane } from './../domain/aw-swimlane';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/retry';
import { BackendService } from './../backend.service';

@Injectable()
export class SwimlaneService {

  zuulUrl: string = "http://localhost:8765";

  constructor(private http: HttpClient, private backend: BackendService) {}

  getSwimlanes(boardId: number): Observable<AwSwimlane[]> {
    return this.backend.get<AwSwimlane[]>("/swimlane-service/getSwimlanesByBoardId/" + boardId);
  }

  createSwimlane(sl: AwSwimlane): Observable<AwSwimlane> {
    console.log(sl);
    return this.backend.post<AwSwimlane>("/swimlane-service/create", sl);
  }
  
  updateSwimlane(sl: AwSwimlane): Observable<AwSwimlane> {
    return this.backend.post<AwSwimlane>("/swimlane-service/update", sl);
  }
    
  deleteSwimlane(sl: AwSwimlane): Observable<Boolean> {
    return this.backend.post<Boolean>("/swimlane-service/delete", sl);
  }
}
