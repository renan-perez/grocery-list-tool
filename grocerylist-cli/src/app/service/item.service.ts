import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Http, Response, Headers, ResponseContentType }  from '@angular/http';

import { Item } from '../model/item'
import { UrlUtil } from '../util/url-util';

// Import RxJs required methods
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class ItemService {

  private listItemsURL = `${UrlUtil.serverURL}/grocerylist-core/items/getAllItems`;
  private listAvailableItemsURL = `${UrlUtil.serverURL}/grocerylist-core/items/listAvailableItems`;
  private getItemByIdURL = `${UrlUtil.serverURL}/grocerylist-core/items/getItemDetails`;
  private saveOrUpdateItemURL = `${UrlUtil.serverURL}/grocerylist-core/items/saveOrUpdateItem`;
  private deleteItemURL = `${UrlUtil.serverURL}/grocerylist-core/items/deleteItem`;

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

  saveOrupdateItem(item: Item) {
    return this.http
               .get(this.saveOrUpdateItemURL, { 
                 headers: this.getHeadersPost(), 
                 body: JSON.stringify(item),
                 method: 'POST'
                })
               .map(response => <Boolean>response.json())
               .catch(this.handleError);
  }

  deleteItem(itemId: Number) {
    const url = `${this.deleteItemURL}/${itemId}`;
    return this.http
               .get(url, { 
                 headers: this.getHeadersPost(), 
                 body: JSON.stringify(itemId),
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
