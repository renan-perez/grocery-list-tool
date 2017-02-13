import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Http, Response, Headers, ResponseContentType }  from '@angular/http';

import { Item } from '../model/item'

// Import RxJs required methods
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class ItemService {

  private listItemsURL = `http://localhost:8081/grocerylist-core/items/getAllItems`;
  private listAvailableItemsURL = `http://localhost:8081/grocerylist-core/items/listAvailableItems`;
  private getItemByIdURL = `http://localhost:8081/grocerylist-core/items/getItemDetails`;
  private updateItemsURL = `http://localhost:8081/grocerylist-core/items/updateItem`;

  constructor(private http: Http) {}

  listItems() {
    return this.http
               .get(this.listItemsURL, { headers: this.getHeaders() })
               .map(response => <Item[]>response.json())
               .catch(this.handleError);
  }

  listAvailableItems(groceryListId: Number) {
    const url = `${this.listAvailableItemsURL}/${groceryListId}`;
    return this.http
               .get(url, { headers: this.getHeaders() })
               .map(response => <Item[]>response.json())
               .catch(this.handleError);
  }

  getItemById(id: Number) {
    const url = `${this.getItemByIdURL}/${id}`;
    return this.http
               .get(url, { headers: this.getHeaders() })
               .map(response => <Item>response.json())
               .catch(this.handleError);
  }

  updateItem(item: Item) {
    return this.http
               .get(this.updateItemsURL, { 
                 headers: this.getHeadersPost(), 
                 body: JSON.stringify(item),
                 method: 'POST'
                })
               .map(response => <Boolean>response.json())
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
