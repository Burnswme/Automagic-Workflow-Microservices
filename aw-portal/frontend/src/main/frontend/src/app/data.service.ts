import { AwUser } from './login/login.component';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

@Injectable()
export class DataService {
  loggedUser: AwUser = {
    username: "steve",
    password: "123"
  };

  constructor() {}

  login(bu: AwUser): Observable<boolean> {
    return of(true);
  }

}
