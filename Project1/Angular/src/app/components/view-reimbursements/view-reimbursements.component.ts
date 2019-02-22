import { Component, OnInit } from '@angular/core';
import { Routes, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { ViewReimbursementsService } from 'src/app/services/view-reimbursements.service';

@Component({
  selector: 'app-view-reimbursements',
  templateUrl: './view-reimbursements.component.html',
  styleUrls: ['./view-reimbursements.component.css']
})
export class ViewReimbursementsComponent implements OnInit {
  role = '';
  id: any;
  isManager = false;
  reimbursements: any;

  constructor(private cookieService: CookieService,
              private router: Router,
              private viewReimbursementsService: ViewReimbursementsService) { }

  ngOnInit() {
    this.role = this.cookieService.get('userRole');
    this.id = Number(this.cookieService.get('userid'));
    
    this.viewMyReimbursements();
    if (this.role === 'FINANCE_MANAGER') {
      this.isManager = true;
    }
  }

  viewAllReimbursements() {
    const credentials = {
      id: this.id
    };
    this.viewReimbursementsService.viewAllReimb(credentials).subscribe( (payload) =>{
      for (const key in payload) {
        if (payload.hasOwnProperty(key)) {
          this.reimbursements = payload;
        }
      }
    }, (err) => console.log(Error));
  }

  viewMyReimbursements() {
    const credentials = {
      id: this.id
    };

    this.viewReimbursementsService.viewMyReimb(credentials).subscribe( (payload) =>{
      for (const key in payload) {
        if (payload.hasOwnProperty(key)) {
          this.reimbursements = payload;
        }
      }
    }, (err) => console.log(Error));
  }

}
