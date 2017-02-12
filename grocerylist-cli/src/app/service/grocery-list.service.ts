import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Http, Response, Headers, ResponseContentType }  from '@angular/http';

import { Item } from '../model/item'
import { GroceryList } from '../model/grocery-list'

// Import RxJs required methods
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class GroceryListService {

  private allListsURL = `http://localhost:8081/grocerylist-core/groceryList/getAllLists`;
  private getListByIdURL = `http://localhost:8081/grocerylist-core/groceryList/getGroceryListById`;

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
