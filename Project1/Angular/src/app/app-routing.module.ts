import { ViewReimbursementsComponent } from './components/view-reimbursements/view-reimbursements.component';
import { ProcessReimbursementsComponent } from './components/process-reimbursements/process-reimbursements.component';
import { CreateReimbursementComponent } from './components/create-reimbursement/create-reimbursement.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { MainComponent } from './components/main/main.component';
import { ProcessReloaderComponent } from './components/process-reloader/process-reloader.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'prefix'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'main',
    component: MainComponent,
    children: [
      {
        path: 'create-reimbursement',
        component: CreateReimbursementComponent
      },
      {
        path: 'view-reimbursements',
        component: ViewReimbursementsComponent
      },
      {
        path: 'process-reimbursements',
        component: ProcessReimbursementsComponent
      },
      {
        path: 'process-reloader',
        component: ProcessReloaderComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }