import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProcessReimbursementsService {

  constructor(private httpClient: HttpClient) { }

  processReimb(credentials: any): Observable<any> {
    const url = `${environment.apiUrl}/processreimb`;
    return this.httpClient.post(url, credentials);
  }
}
