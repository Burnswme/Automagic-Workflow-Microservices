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
  user: AwUser;
  loggedUser: BehaviorSubject<AwUserToken>;

  constructor(private backend: BackendService, private router: Router) {}

  ngOnInit() {
    this.user = new AwUser("", "");
    this.setAwUserListener();
    if (this.isLoggedIn()) {
      this.backend.updateUser();
    }
  }

  loadBoard(id: number): void {
    localStorage.setItem("currentBoardId", ""+id);
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
    });
  }

}
