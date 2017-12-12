import { environment } from './../environments/environment';
import { AwBoard } from './domain/aw-board';
import { Observable } from 'rxjs/Observable';
import { BackendService } from './backend.service';
import { Injectable } from '@angular/core';
import { AwRole } from './domain/aw-role';
import { AwUser } from './domain/aw-user';
import { of } from 'rxjs/observable/of';

@Injectable()
export class ValidationService {
  validatePath: string = environment.validatePath;

  constructor(private backend: BackendService) {}

  getRoles(id: number): Observable<AwRole[]> {
    return this.backend.get<AwRole[]>(this.validatePath + "/getUserBoards/" + id);
  }
  
  saveRole(role: AwRole): Observable<AwRole> {
    return this.backend.post<AwRole>(this.validatePath + "/createUserBoardRole", role);
  }

  isAuthorized(user: AwUser, boardId: number): Observable<Boolean> {
    return (user.admin) ? of(true) : this.backend.get<Boolean>(this.validatePath + "/fetchUserPrivileges/" + user.id + "/" + boardId);
  }

}
