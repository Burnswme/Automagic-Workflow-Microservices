import { BackendService } from './../backend.service';
import { AwBoard } from './../domain/aw-board';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

import { AwHistory } from '../domain/aw-history';

@Injectable()
export class BoardService {

  constructor(private backend: BackendService) {}

  getBoard(id: number): Observable<AwBoard> {
    return this.backend.get<AwBoard>("/aw_boards/getBoard/" + id);
  }

  getBoards(ids: number[]): void {
    this.backend.post<AwBoard[]>("/aw_boards/getBoards", ids).subscribe(boards => {
      this.backend.setBoards(boards);
    });
  }

  getAllBoards(): void {
    this.backend.get<AwBoard[]>("/aw_boards/getAllBoards").subscribe(boards => {
      this.backend.setBoards(boards);
    });
  }

  createBoard(board: AwBoard): Observable<AwBoard> {
    return this.backend.post<AwBoard>("/aw_boards/createBoard", board);
  }

  getHistory(boardId: number): Observable<AwHistory[]> {
    return this.backend.get<AwHistory[]>("/aw_boards/getHistory/" + boardId);
  }

  updateBoard(board: AwBoard): Observable<AwBoard> {
    return this.backend.post<AwBoard>("/aw_boards/updateBoard", board);
  }

  deleteBoard(board: AwBoard): Observable<Boolean> {
    return this.backend.post<Boolean>("/aw_boards/deleteBoard", board);
  }

  addBoardToList(board: AwBoard): void {
    let boards = this.backend.boards.value;
    boards.push(board);
    this.backend.setBoards(boards);
  }

  removeBoardFromList(board: AwBoard): void {
    this.backend.setBoards(this.backend.boards.value.filter(obj => {
      return obj.id !== board.id;
    }));
  }
}
