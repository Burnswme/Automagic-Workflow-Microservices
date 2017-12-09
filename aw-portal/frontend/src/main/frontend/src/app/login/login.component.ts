import { AwLogin } from './../domain/aw-login';
import { AwUser } from './../domain/aw-user';
import { Http } from '@angular/http';
import { BackendService } from './../backend.service';
import { Component, OnInit } from '@angular/core';
import { NgModel } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'aw-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: AwLogin;
  errorMessage: string = "";
  submitted: boolean = false;

  constructor(private http: Http,
              private router: Router,
              private service: BackendService) { }

  ngOnInit() {
    localStorage.removeItem('currentUser');
    this.user = new AwLogin();
  }

  login(user: AwLogin): void {
    this.service.authenticate(user);
  }

}
