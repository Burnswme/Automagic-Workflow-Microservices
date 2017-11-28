import { AwSwimlane } from './../domain/aw-swimlane';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/retry';

@Injectable()
export class SwimlaneService {

  zuulUrl: string = "http://localhost:8765";

  constructor(private http: HttpClient) {}

  getSwimlanes(boardId: number): Observable<AwSwimlane[]> {
    return this.http.get<AwSwimlane[]>(this.zuulUrl + "/swimlane-service/getSwimlanesByBoardId/" + boardId)
      .retry(5);
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
