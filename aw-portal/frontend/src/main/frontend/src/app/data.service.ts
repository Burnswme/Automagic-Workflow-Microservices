import { AwUser } from './domain/aw-user';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

@Injectable()
export class DataService {
  loggedUser: AwUser = {
    id: 0,
    email: "steve@revature.com",
    fn: "Steve",
    ln: "Stevie",
    username: "steve",
    password: "123"
  };

  constructor() {}

  login(bu: AwUser): Observable<boolean> {
    return of(true);
  }

}
