import { AwSwimlane } from './../domain/aw-swimlane';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import 'rxjs/add/operator/retry';
import { BackendService } from './../backend.service';
import { environment } from '../../environments/environment';

@Injectable()
export class SwimlaneService {
  swimlanesPath: string = environment.swimlanesPath;

  constructor(private http: HttpClient, private backend: BackendService) {}

  getSwimlanes(boardId: number): Observable<AwSwimlane[]> {
    return this.backend.get<AwSwimlane[]>(this.swimlanesPath + "/getSwimlanesByBoardId/" + boardId);
  }

  createSwimlane(sl: AwSwimlane): Observable<AwSwimlane> {
    return this.backend.post<AwSwimlane>(this.swimlanesPath + "/create", sl);
  }
  
  updateSwimlane(sl: AwSwimlane): Observable<AwSwimlane> {
    return this.backend.post<AwSwimlane>(this.swimlanesPath + "/update", sl);
  }
    
  deleteSwimlane(sl: AwSwimlane): Observable<Boolean> {
    return this.backend.post<Boolean>(this.swimlanesPath + "/delete", sl);
  }
}
