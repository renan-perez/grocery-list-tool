import { BrowserModule }  from '@angular/platform-browser';
import { NgModule }       from '@angular/core';
import { FormsModule }    from '@angular/forms';
import { HttpModule }     from '@angular/http';

import { AppComponent }   from './app.component';
import { ItemsComponent } from './items/items.component';

import { ItemService }        from './service/item.service';
import { GroceryListService } from './service/grocery-list.service';

import { AppRoutingModule }     from './app-routing.module';
import { GroceryListComponent } from './grocery-list/grocery-list.component';

@NgModule({
  declarations: [
    AppComponent,
    ItemsComponent,
    GroceryListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [
    ItemService,
    GroceryListService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
