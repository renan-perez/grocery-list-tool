/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { GroceryListService } from './grocery-list.service';

describe('GroceryListService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GroceryListService]
    });
  });

  it('should ...', inject([GroceryListService], (service: GroceryListService) => {
    expect(service).toBeTruthy();
  }));
});
