import { AwUser } from './domain/aw-user';
import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Headers, Http, RequestOptions } from '@angular/http';
import { Base64 } from 'js-base64';
import {Observable} from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { of } from 'rxjs/observable/of';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/mergeMap';
import 'rxjs/add/operator/take';
import 'rxjs/add/operator/retryWhen';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class BackendService {
  user: BehaviorSubject<AwUser> = new BehaviorSubject<AwUser>(new AwUser("", ""));

  constructor(private http: Http, private router: Router) {}

  zuul: string = "http://localhost:8765";

  url: string;
  headers: Headers;
  options: RequestOptions;
  creds: String;
  updatedUser: string;

  authenticate(user: AwUser) {
    this.url = this.zuul + "/users/auth/oauth/token";
    this.headers = new Headers({
      "Content-Type": "application/x-www-form-urlencoded",
      "Authorization": "Basic " + Base64.encode('automagic:firaga')
    });
    this.options = new RequestOptions({ headers: this.headers });
    this.creds = 'grant_type=password';
    this.creds += '&username=' + user.username;
    this.creds += '&password=' + user.password;
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

  get<T>(endpoint: string): Observable<T> {
    this.url = this.zuul + endpoint
      + "?access_token=" + JSON.parse(localStorage.getItem('currentUser')).token;
    return this.http.get(this.url)
      .retryWhen(attempts => attempts
        .mergeMap((error) => {
          if (error.status === 401) {
            this.router.navigateByUrl('/login');
            return Observable.throw(error);
          } else return of(error);})
        .take(5))
      .map(res => res.json());
  }

  post<T>(endpoint: string, body: Object): Observable<T> {
    this.url = this.zuul + endpoint
      + "?access_token=" + JSON.parse(localStorage.getItem('currentUser')).token;
      this.headers = new Headers({ 
        "Content-Type": "application/json"
      });
      this.options = new RequestOptions({ headers: this.headers });
    return this.http.post(this.url, body, this.options)
      .retryWhen(attempts => attempts
        .mergeMap((error) => {
          if (error.status === 401) {
            this.router.navigateByUrl('/login');
            return Observable.throw(error);
          } else return of(error);})
        .take(5))
      .map(res => res.json());
  }

  updateUser(): void {
    console.log('updateUser() backend-service.ts');
    this.url = this.zuul + "/users/auth/getUser";
    this.headers = new Headers({ 
      "Content-Type": "application/json",
      'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('currentUser')).token 
    });
    this.options = new RequestOptions({ headers: this.headers });
    let body = new AwUser(JSON.parse(localStorage.getItem('currentUser')).userName, "");
    this.http.post(this.url, body, this.options)
      .retryWhen(attempts => attempts
        .mergeMap((error) => {
          if (error.status === 401) {
            this.router.navigateByUrl('/login');
            return Observable.throw(error);
          } else return of(error);})
        .take(5))
      .map(res => res.json())
      .subscribe(result => {
        this.user.next(result);
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