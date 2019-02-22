import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ViewReimbursementsService {

  constructor(private httpClient: HttpClient) { }

  viewAllReimb(credentials: any): Observable<any> {
    const url = `${environment.apiUrl}/viewallreimb`;
    return this.httpClient.post(url, credentials);
  }

  viewPendingReimb(credentials: any): Observable<any> {
    const url = `${environment.apiUrl}/viewpendingreimb`;
    return this.httpClient.post(url, credentials);
  }

  viewMyReimb(credentials: any): Observable<any> {
    const url = `${environment.apiUrl}/viewmyreimb`;
    return this.httpClient.post(url, credentials);
  }
}
