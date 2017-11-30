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
    return this.http.post<AwSwimlane>(this.zuulUrl + "/swimlane-service/create", sl)
      .retry(5);
  }
  
  updateSwimlane(sl: AwSwimlane): Observable<AwSwimlane> {
    return this.http.post<AwSwimlane>(this.zuulUrl + "/swimlane-service/update", sl)
      .retry(5);
  }
    
  deleteSwimlane(sl: AwSwimlane): Observable<Boolean> {
    return this.http.post<Boolean>(this.zuulUrl + "/swimlane-service/delete", sl)
      .retry(5);
  }
}
