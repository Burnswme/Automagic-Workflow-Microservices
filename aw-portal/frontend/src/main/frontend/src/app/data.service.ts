import { AwUser } from './domain/aw-user';
import { Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

@Injectable()
export class DataService implements OnInit {
  loggedUser: AwUser;

  constructor() {}

  ngOnInit() {
    this.loggedUser = new AwUser("","");
  }

}
