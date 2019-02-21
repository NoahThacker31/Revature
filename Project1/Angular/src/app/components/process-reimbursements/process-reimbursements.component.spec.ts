import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessReimbursementsComponent } from './process-reimbursements.component';

describe('ProcessReimbursementsComponent', () => {
  let component: ProcessReimbursementsComponent;
  let fixture: ComponentFixture<ProcessReimbursementsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProcessReimbursementsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessReimbursementsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
