import { AwUser } from './../domain/aw-user';
import { DataService } from './../data.service';
import { Component, OnInit } from '@angular/core';
// import { AwBoard } from '../board/board.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  user: AwUser = {
    id: 0,
    email: "steve@revature.com",
    fn: "Steve",
    ln: "Stevie",
    username: "steve",
    password: "123"
  };
  
//   bu: AwBoard = {
//     id: 0,
//     name: "",
//     timeCompleted: "",
//     duration: 0
// };

  constructor(private service: DataService) { }

  ngOnInit() {
    this.getLoggedUser();
  }

  getLoggedUser(): void {
    this.user = this.service.loggedUser;
  }

}
