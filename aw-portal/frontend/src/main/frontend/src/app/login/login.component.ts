import { DataService } from './../data.service';
import { Component, OnInit } from '@angular/core';
import { NgModel } from '@angular/forms';
import { Router } from '@angular/router';

export class AwUser {
  username: string;
  password: string;
}

@Component({
  selector: 'aw-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  bu: AwUser = {
    username: "",
    password: ""
  };
  errorMessage: string = "";
  submitted: boolean = false;


  constructor(private service: DataService,
    private router: Router) { }

  ngOnInit() {
  }

  login(bu: AwUser): void {
    this.service.login(bu).subscribe(result => {
        if (result) {
          this.router.navigate(['/home']);
        }
    });
  }

}
