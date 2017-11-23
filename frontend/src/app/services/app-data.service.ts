import {Injectable} from '@angular/core';
import {AuthHttp} from 'angular2-jwt';

@Injectable()
export class AppDataService {
  constructor(private http: AuthHttp) {
  }

  getCities() {
    return this.http.get('/resources/user').map(res => res.json());
  }

  getUsers() {
    return this.http.get('/admin/users').map(res => res.json());
  }
}
