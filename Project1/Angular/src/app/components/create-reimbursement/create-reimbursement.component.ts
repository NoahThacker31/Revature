import { CreateReimbursementService } from './../../services/create-reimbursement.service';
import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';
import { HttpXsrfCookieExtractor } from '@angular/common/http/src/xsrf';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-create-reimbursement',
  templateUrl: './create-reimbursement.component.html',
  styleUrls: ['./create-reimbursement.component.css']
})
export class CreateReimbursementComponent implements OnInit {
  amount: number;
  description = '';
  type = 4;

  author = this.cookieService.get('userid');

  constructor(private createReimbursementService: CreateReimbursementService,
              private router: Router,
              private cookieService: CookieService) { }

  ngOnInit() {
  }

  submitReimb(){
    const credentials = {
      amount: this.amount,
      description: this.description,
      type: this.type,
      author: this.author
    };
  
    this.createReimbursementService.createReimb(credentials).subscribe( (payload) => {
      console.log(payload);
      //this.cookieService.set('userid', payload.id);
      //console.log(this.cookieService.get('userid'));
      this.router.navigateByUrl('/main');
    }, (err) => {
      console.log(err);
    });
  }
}
