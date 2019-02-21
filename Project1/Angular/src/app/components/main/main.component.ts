import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  firstname = '';
  lastname = '';

  constructor(private cookieService: CookieService) { }

  ngOnInit() {
    this.firstname = this.cookieService.get('firstName');
    this.lastname = this.cookieService.get('lastName');
  }
  logout() {
  }

}
