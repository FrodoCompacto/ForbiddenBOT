import { take, catchError } from 'rxjs/operators';
import { environment } from './../../environments/environment';
import { Observable, throwError } from 'rxjs';
import { NewExodiaPart } from './../models/newexodiapart';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private readonly API = environment.API;

  constructor(private http: HttpClient) { }

  send(part: NewExodiaPart): Observable<any> {

    return this.http.post(`${this.API}exodiaparts`, part).pipe(take(1),catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status == 401) {
        return throwError(
          'Bad username or password');
      } else {
        console.error(error.message);
      return throwError(
        'Something bad happened; please try again later.');
      }
  }

  public getIp(): Observable<any>{
    return this.http.get<{ip:string}>('http://api.ipify.org/?format=json');
  }
}
