import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-process-reloader',
  templateUrl: './process-reloader.component.html',
  styleUrls: ['./process-reloader.component.css']
})
export class ProcessReloaderComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
    this.router.navigateByUrl('/main/process-reimbursements');
  }

}
