import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreateReimbursementService {

  constructor(private httpClient: HttpClient) { }

  createReimb(credentials: any): Observable<any> {
    const url = `${environment.apiUrl}/createreimb`;
    return this.httpClient.post(url, credentials);
  }
}
