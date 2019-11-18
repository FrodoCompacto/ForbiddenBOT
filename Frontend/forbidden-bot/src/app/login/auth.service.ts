import { User } from "./../models/user";
import { take, catchError } from 'rxjs/operators';
import { Injectable, EventEmitter } from "@angular/core";
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: "root"
})
export class AuthService {

  private readonly API = environment.API;

  userAuthenticatedEmitter = new EventEmitter<boolean>();
  public userAuthenticated: boolean = false;
  

  constructor(private http: HttpClient) {}

  authUser(user: User): Observable<any> {
    return this.http.post(`${this.API}login`, user,
      {
        observe: 'response',
        responseType: 'text'
      }
      ).pipe(take(1),catchError(this.handleError));
  }

  successfulLogin(authorizationValue: string){
    let token = authorizationValue.substring(7);
    sessionStorage.setItem("token",token);
    this.userAuthenticatedEmitter.emit(true);
    this.userAuthenticated = true;
  }

  logout(){
    sessionStorage.removeItem("token");
    this.userAuthenticatedEmitter.emit(false);
    this.userAuthenticated = false;
  }

  refresh(token:string):Observable<any>{
    let authHeader = new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'Bearer ' + token});

    return this.http.post(`${this.API}auth/refresh_token`, {}
    ,{
      'headers': authHeader, observe: "response"}
    ).pipe(take(1));
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
}
