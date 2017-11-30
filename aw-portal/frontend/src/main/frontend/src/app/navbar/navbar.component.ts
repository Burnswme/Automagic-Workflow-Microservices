import { AwUser } from './../domain/aw-user';
import { Router } from '@angular/router';

import { AwBoard } from './../domain/aw-board';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'aw-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  loggedIn: boolean = true;
  boards: AwBoard[] = [
    {
      id: 99,
      name: "test",
      startDate: 1511276204213,
      duration: 2,
      swimlanes: null
    }
  ];
  user: AwUser = {
    id: 0,
    email: "steve@revature.com",
    fn: "Steve",
    ln: "Stevie",
    username: "steve",
    password: "123"
  };

  constructor(private router: Router) {}

  ngOnInit() {}

  loadBoard(id: number): void {
    localStorage.setItem("currentBoardId", ""+id);
    this.router.navigate(['/board', id]);
  }

}
