import { AwUser } from './../login/login.component';
import { DataService } from './../data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  user: AwUser = {username: "", password: ""};

  constructor(private service: DataService) { }

  ngOnInit() {
    this.getLoggedUser();
  }

  getLoggedUser(): void {
    this.user = this.service.loggedUser;
  }

}
