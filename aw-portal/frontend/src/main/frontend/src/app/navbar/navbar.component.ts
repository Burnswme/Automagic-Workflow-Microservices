import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'aw-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  loggedIn: boolean = true;
  boards = [1, 2, 3];
  user = {username: "smisoka"};

  constructor() { }

  ngOnInit() {
  }

}
