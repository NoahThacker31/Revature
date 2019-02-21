import { TestBed } from '@angular/core/testing';

import { CreateReimbursementService } from './create-reimbursement.service';

describe('CreateReimbursementService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CreateReimbursementService = TestBed.get(CreateReimbursementService);
    expect(service).toBeTruthy();
  });
});
