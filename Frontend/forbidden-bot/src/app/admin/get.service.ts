import { take, catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { throwError, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GetService {

  private readonly API = environment.API;

  constructor(private http: HttpClient) {}

  getUnverifiedParts(page: number = 0, linesPerPage: number = 24, orderBy:string = "uploadDate", direction: string = "DESC"): Observable<any> {

    let authHeader = new HttpHeaders({
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")});

    return this.http.get(`${this.API}exodiaparts/unverified?page=${page}&linesPerPage=${linesPerPage}&orderBy=${orderBy}&direction=${direction}`,{
      'headers': authHeader})
    .pipe(take(1),catchError(this.handleError));
  }

  getVerifiedParts(page: number = 0, linesPerPage: number = 24, orderBy:string = "uploadDate", direction: string = "DESC"): Observable<any> {

    let authHeader = new HttpHeaders({
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")});

    return this.http.get(`${this.API}exodiaparts/verified?page=${page}&linesPerPage=${linesPerPage}&orderBy=${orderBy}&direction=${direction}`,{
      'headers': authHeader})
    .pipe(take(1),catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
      console.error(error.message);
      return throwError(
        'Something bad happened; please try again later.');
      
  }

}
