import { TestBed } from '@angular/core/testing';

import { ViewReimbursementsService } from './view-reimbursements.service';

describe('ViewReimbursementsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ViewReimbursementsService = TestBed.get(ViewReimbursementsService);
    expect(service).toBeTruthy();
  });
});
