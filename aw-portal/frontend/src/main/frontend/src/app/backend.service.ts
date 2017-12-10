import { AwLogin } from './domain/aw-login';
import { AwBoard } from './domain/aw-board';
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
  user: BehaviorSubject<AwUser> = new BehaviorSubject<AwUser>(new AwUser(""));
  boards: BehaviorSubject<AwBoard[]> = new BehaviorSubject<AwBoard[]>([]);

  constructor(private http: Http, private router: Router) {}

  zuul: string = "http://localhost:8765";

  url: string;
  headers: Headers;
  options: RequestOptions;
  creds: String;
  updatedUser: string;

  authenticate(user: AwLogin) {
    this.url = this.zuul + "/auth/oauth/token";
    this.headers = new Headers({
      "Content-Type": "application/x-www-form-urlencoded",
      "Authorization": "Basic " + Base64.encode('automagic:firaga')
    });
    this.options = new RequestOptions({ headers: this.headers });
    this.creds = 'grant_type=password';
    this.creds += '&username=' + user.username;
    this.creds += '&password=' + Base64.encode(user.password);
    this.http.post(this.url, this.creds, this.options)
      .retryWhen(attempts => attempts
        .mergeMap((error) => {
          if (error.status === 400) {
            return Observable.throw(error);
          } else return of(error);})
        .take(5))
      .map(res => res.json())
      .subscribe(response => {
        localStorage.setItem('currentUser',
          JSON.stringify({userName:user.username, token: response.access_token }));

        localStorage.setItem("currentUserId", "" + this.user.value.id);

        localStorage.setItem("currentUserUsername", user.username);

        this.updateUserRef();
        this.router.navigateByUrl("/home");
      });
  }

  get<T>(endpoint: string): Observable<T> {
    this.url = this.zuul + endpoint;
    this.headers = new Headers({ 
      "Authorization": "Bearer " + JSON.parse(localStorage.getItem('currentUser')).token
    });
    this.options = new RequestOptions({ headers: this.headers });
    return this.http.get(this.url, this.options)
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
    this.url = this.zuul + endpoint; 
      this.headers = new Headers({ 
        "Content-Type": "application/json",
        "Authorization": "Bearer " + JSON.parse(localStorage.getItem('currentUser')).token
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

  updateUserRef(): void {
    let body = new AwUser(JSON.parse(localStorage.getItem('currentUser')).userName);
    this.post<AwUser>("/users/getUser", body)
      .subscribe(result => {
        this.user.next(result);
      });
  }

  // updateBoardsRef(): void {
  //   let body = new AwUser(JSON.parse(localStorage.getItem('currentUser')).userName, "");
  //   this.post<AwUser>("/users/getUser", body)
  //     .subscribe(result => {
  //       this.user.next(result);
  //     });
  // }

  setBoards(boards: AwBoard[]): void {
    this.boards.next(boards);
  }

  isLoggedIn(): boolean {
    return localStorage.getItem('currentUser') !== null;
  }

  clearUser(): void {
    localStorage.removeItem('currentUser');
    this.user.next(new AwUser(""));
    this.boards.next([]);
  }

  createUser(user: AwUser): Observable<AwUser> {
    return this.post<AwUser>("/users/saveUser", user);
  }
}