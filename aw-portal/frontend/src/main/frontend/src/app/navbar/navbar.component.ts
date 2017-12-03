import { AwRole } from './../domain/aw-role';
import { ValidationService } from './../validation.service';
import { BoardService } from './../board/board.service';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { BackendService } from './../backend.service';
import { AwUser } from './../domain/aw-user';
import { Router } from '@angular/router';

import { AwBoard } from './../domain/aw-board';
import { Component, OnInit } from '@angular/core';
import { AwUserToken } from '../domain/aw-usertoken';

@Component({
  selector: 'aw-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  boards: AwBoard[] = [];
  newBoard: AwBoard = new AwBoard();
  user: AwUser = new AwUser("", "");
  newUser: AwUser = new AwUser("", "");
  loggedUser: BehaviorSubject<AwUserToken>;

  constructor(private backend: BackendService, 
              private bds: BoardService,
              private bvs: ValidationService,
              private router: Router) {}

  ngOnInit() {
    this.setAwUserListener();
    if (this.isLoggedIn()) {
      this.backend.updateUserRef();
    }
  }

  loadBoard(id: number): void {
    // localStorage.setItem("currentBoardId", ""+id);
    this.router.navigateByUrl('/board/' + id);
  }

  home(): void {
    this.router.navigateByUrl('/home');
  }

  logout(): void {
    this.backend.clearUser();
    this.router.navigateByUrl('/login');
  }

  isLoggedIn(): boolean {
    return this.backend.isLoggedIn();
  }

  setAwUserListener(): void {
    this.backend.user.subscribe(result => {
      this.user = result;
      if (result.username != "") {
        if (result.admin) {
          this.bds.getAllBoards().subscribe(boards => {
            this.boards = boards;
          });
        } else {
          this.bvs.getRoles(result.id)
          .subscribe(roles => {
            let ids = roles.map(role => role.boardId);
            this.bds.getBoards(ids).subscribe(boards => {
              this.boards = boards;
            });
          })
        }
      }
    });
  }

  createBoard(board: AwBoard): void {
    board.startDate = Date.now();
    this.bds.createBoard(board).subscribe(result => {
      this.boards.push(result);
      console.log(new AwRole(this.user.id, result.id, 2));
      this.bvs.saveRole(new AwRole(this.user.id, result.id, 2)).subscribe();
    });
    this.newBoard = new AwBoard();
  }

  register(): void {
    this.backend.createUser(this.newUser).subscribe(result => {
      this.newUser = new AwUser("", "");
    })
  }

}
