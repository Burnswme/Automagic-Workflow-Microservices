import { AwUser } from './domain/aw-user';
import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Headers, Http, RequestOptions } from '@angular/http';
import { Base64 } from 'js-base64';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { OnInit } from '@angular/core/src/metadata/lifecycle_hooks';



@Injectable()
export class BackendService implements OnInit {
  user: BehaviorSubject<AwUser> = new BehaviorSubject<AwUser>(new AwUser("", ""));

  constructor(private http: Http, private router: Router) { }

  url: string;
  headers: Headers;
  options: RequestOptions;
  creds: String;
  updatedUser: string;

  ngOnInit() {
    // if (this.isLoggedIn()) {
    //   let awuser = new AwUser(JSON.parse(localStorage.getItem('currentUser')).userName, "");
    //   this.getUpdatedUser(awuser).subscribe((result: AwUser) => {
    //     this.user.next(result);
    //   });
    // }
  }

  authenticate(user: AwUser) {
    this.url = "http://localhost:8765/users/auth/oauth/token";
    this.headers = new Headers({
      "Content-Type": "application/x-www-form-urlencoded",
      "Authorization": "Basic " + Base64.encode('automagic:firaga')
    });
    this.options = new RequestOptions({ headers: this.headers });
    this.creds = 'grant_type=password';
    this.creds += '&username=' + user.username;
    this.creds += '&password=' + user.password;
    // this.creds = 'grant_type=authorization_code';
    this.http.post(this.url, this.creds, this.options)
      .retry(5)
      .map(res => res.json())
      .subscribe(response => {
        localStorage.setItem('currentUser',
          JSON.stringify({userName:user.username, token: response.access_token }));
        this.updateUser();
        this.router.navigateByUrl("/home");
      }, (error) => {
        console.log('error in', error);
      });
  }

  getUpdatedUser(user: AwUser): Observable<AwUser> {
    console.log('getUpdatedUser() backend-service.ts');
    this.url = "http://localhost:8765/users/auth/getUser";
    this.headers = new Headers({ 
      "Content-Type": "application/json",
      'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('currentUser')).token 
    });
    this.options = new RequestOptions({ headers: this.headers });
    return this.http.post(this.url, user, this.options)
      .map(res => res.json());
  }

  updateUser(): void {
    console.log('updateUser() backend-service.ts');
    this.url = "http://localhost:8765/users/auth/getUser";
    this.headers = new Headers({ 
      "Content-Type": "application/json",
      'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('currentUser')).token 
    });
    this.options = new RequestOptions({ headers: this.headers });
    let body = new AwUser(JSON.parse(localStorage.getItem('currentUser')).userName, "");
    this.http.post(this.url, body, this.options)
      .retry(5)
      .map(res => res.json())
      .subscribe(result => {
        this.user.next(result);
      }, (error) => {
        if (error.status == 401)
          this.router.navigateByUrl('/login');
      });
  }

  isLoggedIn(): boolean {
    return localStorage.getItem('currentUser') !== null;
  }

  clearUser(): void {
    localStorage.removeItem('currentUser');
    this.user.next(new AwUser("", ""));
  }
}