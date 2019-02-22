import { CookieService } from 'ngx-cookie-service';
import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';
import { HttpXsrfCookieExtractor } from '@angular/common/http/src/xsrf';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email = '';
  password = '';
  loginFailure = false;

  constructor(private loginService: LoginService,
              private router: Router,
              private cookieService: CookieService) { }

  ngOnInit() {
  }

  login() {
    const credentials = {
      email: this.email,
      password: this.password
    };

    this.loginService.login(credentials).subscribe( (payload) => {
      console.log(payload);
      this.cookieService.set('userid', payload.id);
      this.cookieService.set('username', payload.username);
      this.cookieService.set('firstName', payload.firstName);
      this.cookieService.set('lastName', payload.lastName);
      this.cookieService.set('userRole', payload.role);
      console.log(this.cookieService.get('userid'));
      console.log(this.cookieService.get('username'));
      console.log(this.cookieService.get('firstName'));
      console.log(this.cookieService.get('lastName'));
      console.log(this.cookieService.get('userRole'));
      this.router.navigateByUrl('/main');
    }, (err) => {
      this.loginFailure = true;
      console.log(err);
    });
  }
}