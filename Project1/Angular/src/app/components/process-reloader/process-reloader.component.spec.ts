import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessReloaderComponent } from './process-reloader.component';

describe('ProcessReloaderComponent', () => {
  let component: ProcessReloaderComponent;
  let fixture: ComponentFixture<ProcessReloaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProcessReloaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessReloaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
