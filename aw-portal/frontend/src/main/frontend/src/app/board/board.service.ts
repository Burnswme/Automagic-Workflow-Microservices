import { BackendService } from './../backend.service';
import { AwBoard } from './../domain/aw-board';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/retry';
import { AwHistory } from '../domain/aw-history';
import { of } from 'rxjs/observable/of';
import { Headers, Http, RequestOptions } from '@angular/http';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Base64 } from 'js-base64';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/mergeMap';
import 'rxjs/add/operator/take';
import 'rxjs/add/operator/retryWhen';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class BoardService {
  url: string;
  headers: Headers;
  options: RequestOptions;
  creds: String;
  updatedUser: string;
  zuulUrl: string = "http://localhost:8765";

  constructor(private http: Http, private router: Router, private backend: BackendService) {}

  getBoard(id: number): Observable<AwBoard> {
    return this.backend.get<AwBoard>("/aw_boards/getBoard/" + id);
  }

  getHistory(boardId: number): Observable<AwHistory[]> {
    return this.backend.get<AwHistory[]>("/aw_boards/getHistory/" + boardId);
  }
  // updateBoard(board: Board): Observable<AwBoard> {

  // }
}
