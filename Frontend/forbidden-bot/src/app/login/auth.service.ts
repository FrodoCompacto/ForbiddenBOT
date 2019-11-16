import { User } from "./../models/user";
import { take } from 'rxjs/operators';
import { Injectable, EventEmitter } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: "root"
})
export class AuthService {

  private readonly API = `${environment.API}login`;
  userAuthenticated = new EventEmitter<boolean>();
  

  constructor(private http: HttpClient, private router: Router) {}

  authUser(user: User): Observable<any> {
    return this.http.post(this.API, user,
      {
        observe: 'response',
        responseType: 'text'
      }
      ).pipe(take(1));
  }

  successfulLogin(authorizationValue: string){
    let token = authorizationValue.substring(7);
    sessionStorage.setItem("token",token);
    this.userAuthenticated.emit(true);
    this.router.navigate(['/upload']);
  }

  logout(){
    sessionStorage.removeItem("token");
    this.userAuthenticated.emit(false);
  }
}
