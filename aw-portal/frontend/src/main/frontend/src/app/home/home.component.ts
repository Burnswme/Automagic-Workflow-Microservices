import { BackendService } from './../backend.service';
import { AwUser } from './../domain/aw-user';
import { Component, OnInit } from '@angular/core';
// import { AwBoard } from '../board/board.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  user: AwUser;

  constructor(private backend: BackendService) { }

  ngOnInit() {
    this.user = new AwUser("");
    this.setAwUserListener();
  }

  setAwUserListener(): void {
    this.backend.user.subscribe(result => {
      this.user = result;
    });
  }

}
