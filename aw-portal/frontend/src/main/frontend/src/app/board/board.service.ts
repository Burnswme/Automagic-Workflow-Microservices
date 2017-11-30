import { BackendService } from './../backend.service';
import { AwBoard } from './../domain/aw-board';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class BoardService {

  constructor(private backend: BackendService) {}

  getBoard(id: number): Observable<AwBoard> {
    return this.backend.get<AwBoard>("/aw_boards/getBoard/" + id);
  }

}
