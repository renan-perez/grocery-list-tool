import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Http, Response, Headers, ResponseContentType }  from '@angular/http';

import { Item } from '../model/item';
import { GroceryList } from '../model/grocery-list';
import { SelectedItem } from '../model/selected-item';
import { SelectedItemId } from '../model/selected-item-id';

// Import RxJs required methods
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class GroceryListService {

  private allListsURL = `http://localhost:8081/grocerylist-core/groceryList/getAllLists`;
  private getListByIdURL = `http://localhost:8081/grocerylist-core/groceryList/getGroceryListById`;
  private removeItemFromListURL = `http://localhost:8081/grocerylist-core/groceryList/removeItemFromList`;
  private addItemsToListURL = `http://localhost:8081/grocerylist-core/groceryList/addItemsToList`;
  private updatePurchaseStatusURL = `http://localhost:8081/grocerylist-core/groceryList/updatePurchaseStatus`;
  private newGroceryListURL = `http://localhost:8081/grocerylist-core/groceryList/newGroceryList`;
  private deleteGroceryListURL = `http://localhost:8081/grocerylist-core/groceryList/deleteGroceryList`;

  constructor(private http: Http) {}

  getAllLists() {
    return this.http
               .get(this.allListsURL, { headers: this.getHeaders() })
               .map(response => <GroceryList[]>response.json())
               .catch(this.handleError);
  }

  getGroceryListById(id: Number) {
    const url = `${this.getListByIdURL}/${id}`;
    return this.http
               .get(url, { headers: this.getHeaders() })
               .map(response => <GroceryList>response.json())
               .catch(this.handleError);
  }

  removeItemFromList(selectedItemId: SelectedItemId) {
    return this.http
              .get(this.removeItemFromListURL, { 
                    headers: this.getHeadersPost(), 
                    body: JSON.stringify(selectedItemId),
                    method: 'POST'
                  })
               .map(response => <GroceryList>response.json())
               .catch(this.handleError);
  }

  addItemsToList(selectedItems: SelectedItem[]) {
    return this.http
              .get(this.addItemsToListURL, { 
                    headers: this.getHeadersPost(), 
                    body: JSON.stringify(selectedItems),
                    method: 'POST'
                  })
               .map(response => <GroceryList>response.json())
               .catch(this.handleError);
  }

  updatePurchaseStatus(selectedItem: SelectedItem) {
    return this.http
              .get(this.updatePurchaseStatusURL, { 
                    headers: this.getHeadersPost(), 
                    body: JSON.stringify(selectedItem),
                    method: 'POST'
                  })
               .map(response => <GroceryList>response.json())
               .catch(this.handleError);
  }

  newGroceryList() {
    return this.http
              .get(this.newGroceryListURL, { 
                    headers: this.getHeaders(),
                    method: 'GET'
                  })
               .map(response => <GroceryList>response.json())
               .catch(this.handleError);
  }

  deleteGroceryList(groceryListId: Number) {
    const url = `${this.deleteGroceryListURL}/${groceryListId}`;
    return this.http
              .get(url, { 
                    headers: this.getHeadersPost(),
                    method: 'POST'
                  })
               .map(response => <GroceryList>response.json())
               .catch(this.handleError);
  }

  protected getHeaders(): Headers {
    let headers = new Headers();
    headers.append('Accept', 'application/json');
    return headers;
  }

  protected getHeadersPost(): Headers {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return headers;
  }

  protected handleError(error: any): Promise<any> {
    console.log('An error occurred', error);
    return Promise.reject(error.message || error);
  }

}
