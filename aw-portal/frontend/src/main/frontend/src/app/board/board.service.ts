import { AwBoard } from './../domain/aw-board';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import 'rxjs/add/operator/retry';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class BoardService {
  zuulUrl: string = "http://localhost:8765";
  board: BehaviorSubject<AwBoard> = new BehaviorSubject<AwBoard>({
    id: 0,
    name: "",
    startDate: 0,
    duration: 0,
    swimlanes: null
  });

  constructor(private http: HttpClient) {}

  loadFullBoard(id: number): Observable<AwBoard> {
    return this.http.get<AwBoard>(this.zuulUrl + "/aw_boards/getBoard/" + id)
      .retry(5);
      // .subscribe(board => {
      //   console.log(board);
      //   this.board.next(board);
      //   return board;
      // });
  }

}
