import { TestBed } from '@angular/core/testing';

import { ProcessReimbursementsService } from './process-reimbursements.service';

describe('ProcessReimbursementsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProcessReimbursementsService = TestBed.get(ProcessReimbursementsService);
    expect(service).toBeTruthy();
  });
});
