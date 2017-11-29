import { AwBoard } from './../domain/aw-board';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/retry';

@Injectable()
export class BoardService {
  zuulUrl: string = "http://localhost:8765";

  constructor(private http: HttpClient) {}

  getBoard(id: number): Observable<AwBoard> {
    return this.http.get<AwBoard>(this.zuulUrl + "/aw_boards/getBoard/" + id)
      .retry(5);
  }

}
