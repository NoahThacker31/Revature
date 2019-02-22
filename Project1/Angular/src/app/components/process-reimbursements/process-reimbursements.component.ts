import { Component, OnInit } from '@angular/core';
import { ProcessReimbursementsService } from './../../services/process-reimbursements.service';
import { ViewReimbursementsService } from 'src/app/services/view-reimbursements.service';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { HttpXsrfCookieExtractor } from '@angular/common/http/src/xsrf';

@Component({
  selector: 'app-process-reimbursements',
  templateUrl: './process-reimbursements.component.html',
  styleUrls: ['./process-reimbursements.component.css']
})
export class ProcessReimbursementsComponent implements OnInit {
  resolver = 0;
  status = 1;
  reimbursements: any;

  constructor(private processReimbursementsService: ProcessReimbursementsService,
              private viewReimbursementsService: ViewReimbursementsService,
              private router: Router,
              private cookieService: CookieService) { }

  ngOnInit() {
    this.resolver = Number(this.cookieService.get('userid'));
    this.viewPendingReimbursements();
  }

  viewPendingReimbursements() {
    const credentials = {
      id: this.resolver
    };
    this.viewReimbursementsService.viewPendingReimb(credentials).subscribe( (payload) =>{
      for (const key in payload) {
        if (payload.hasOwnProperty(key)) {
          this.reimbursements = payload;
        }
      }
    }, (err) => console.log(Error));
  }

  processReimb(i: number, status: number){
    this.status = status;
    const credentials = {
      resolverId: this.resolver,
      reimbId: this.reimbursements[i].id,
      status: this.status
    };
    this.processReimbursementsService.processReimb(credentials).subscribe( (payload) => {
      console.log(payload);
      this.router.navigateByUrl('/main/process-reloader');
    }, (err) => {
      console.log(err);
    });
  }
}
