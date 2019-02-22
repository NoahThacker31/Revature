import { Routes, Router } from '@angular/router';
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
  role = '';
  isManager = false;
  Router

  constructor(private cookieService: CookieService,
              private router: Router) { }

  ngOnInit() {
    this.firstname = this.cookieService.get('firstName');
    this.lastname = this.cookieService.get('lastName');
    this.role = this.cookieService.get('userRole');

    if (this.role === 'FINANCE_MANAGER') {
      this.isManager = true;
    }
  }

  logout() {
    this.cookieService.delete('userid');
    this.cookieService.delete('username');
    this.cookieService.delete('userRole');
    this.cookieService.delete('firstName');
    this.cookieService.delete('lastName');
    this.router.navigateByUrl('/login');
  }

}