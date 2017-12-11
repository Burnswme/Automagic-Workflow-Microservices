import { AwBoard } from './domain/aw-board';
import { Observable } from 'rxjs/Observable';
import { BackendService } from './backend.service';
import { Injectable } from '@angular/core';
import { AwRole } from './domain/aw-role';
import { AwUser } from './domain/aw-user';
import { of } from 'rxjs/observable/of';

@Injectable()
export class ValidationService {

  constructor(private backend: BackendService) {}

  getRoles(id: number): Observable<AwRole[]> {
    console.log(localStorage.getItem('currentUser'));
    return this.backend.get<AwRole[]>("/aw_boardvalidation/getUserBoards/" + id);
  }
  
  saveRole(role: AwRole): Observable<AwRole> {
    return this.backend.post<AwRole>("/aw_boardvalidation/createUserBoardRole", role);
  }

  isAuthorized(user: AwUser, boardId: number): Observable<Boolean> {
    return (user.admin) ? of(true) : this.backend.get<Boolean>("/aw_boardvalidation/fetchUserPrivileges/" + user.id + "/" + boardId);
  }

}
