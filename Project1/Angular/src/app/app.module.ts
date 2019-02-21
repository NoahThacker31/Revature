import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { LoginService } from './services/login.service';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MainComponent } from './components/main/main.component';
import { CreateReimbursementComponent } from './components/create-reimbursement/create-reimbursement.component';
import { ViewReimbursementsComponent } from './components/view-reimbursements/view-reimbursements.component';
import { ProcessReimbursementsComponent } from './components/process-reimbursements/process-reimbursements.component';

import { CookieService } from 'ngx-cookie-service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MainComponent,
    CreateReimbursementComponent,
    ViewReimbursementsComponent,
    ProcessReimbursementsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [ LoginService, CookieService ],
  bootstrap: [AppComponent]
})
export class AppModule { }