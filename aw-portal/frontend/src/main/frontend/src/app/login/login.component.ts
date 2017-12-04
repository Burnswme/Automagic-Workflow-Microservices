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
  bu: AwUser;
  newUser: AwUser = new AwUser("","");
  errorMessage: string = "";
  submitted: boolean = false;


  constructor(private http: Http,
    private router: Router,
    private service: BackendService) { }

  ngOnInit() {
    localStorage.removeItem('currentUser');
    this.bu = new AwUser("", "");
  }

  login(bu: AwUser): void {
    this.service.authenticate(bu, function(errmsg: string) {
      this.errorMessage = errmsg;
    });
  }

  // register(newUser: AwUser): void {
  //   this.service.createUser(newUser);
  //   this.newUser = new AwUser("", "");
  // }

}
