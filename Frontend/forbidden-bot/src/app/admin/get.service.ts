import { Admin } from '../models/Admin';
import { take, catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { throwError, Observable, empty, of } from 'rxjs';
import { ExodiaPart } from '../models/exodiapart';

@Injectable({
  providedIn: 'root'
})
export class GetService {

  private readonly API = environment.API;

  constructor(private http: HttpClient) {}

  getUnverifiedArms(page: number, linesPerPage: number = 24, orderBy:string = "uploadDate", direction: string = "DESC"): Observable<any> {
    let authHeader = new HttpHeaders({
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")});

    return this.http.get(`${this.API}exodiaparts/unverified/arms?page=${page}&linesPerPage=${linesPerPage}&orderBy=${orderBy}&direction=${direction}`,{
      'headers': authHeader})
    .pipe(take(1),catchError(this.handleError));
  }

  getUnverifiedLegs(page: number = 0, linesPerPage: number = 24, orderBy:string = "uploadDate", direction: string = "DESC"): Observable<any> {

    let authHeader = new HttpHeaders({
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")});

    return this.http.get(`${this.API}exodiaparts/unverified/legs?page=${page}&linesPerPage=${linesPerPage}&orderBy=${orderBy}&direction=${direction}`,{
      'headers': authHeader})
    .pipe(take(1),catchError(this.handleError));
  }

  getUnverifiedHeads(page: number = 0, linesPerPage: number = 24, orderBy:string = "uploadDate", direction: string = "DESC"): Observable<any> {

    let authHeader = new HttpHeaders({
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")});

    return this.http.get(`${this.API}exodiaparts/unverified/heads?page=${page}&linesPerPage=${linesPerPage}&orderBy=${orderBy}&direction=${direction}`,{
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

  verify(idList: Array<number>): Observable<any> {
    let authHeader = new HttpHeaders({
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")});

      return this.http.put(`${this.API}exodiaparts/verify`, idList,{
        'headers': authHeader})
      .pipe(take(1),catchError(this.handleError));
  }

  delete(idList: Array<number>): Observable<any> {
    let authHeader = new HttpHeaders({
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")});

      return this.http.post(`${this.API}exodiaparts/delete`, idList,{
        'headers': authHeader})
      .pipe(take(1),catchError(this.handleError));
  }

  insertNewAdm(admin: Admin): Observable<any> {
    let authHeader = new HttpHeaders({
      'Authorization': 'Bearer ' + sessionStorage.getItem("token")});

      return this.http.post(`${this.API}admins`, admin,{
        'headers': authHeader})
      .pipe(take(1),catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
      console.error(error.message);
      return throwError(
        'Something bad happened; please try again later.');
      
  }

}
