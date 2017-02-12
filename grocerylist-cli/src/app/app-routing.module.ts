import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ItemsComponent }       from './items/items.component';
import { GroceryListComponent } from './grocery-list/grocery-list.component';

const routes: Routes = [
    { path: '',             redirectTo: '/', pathMatch: 'full' },
    { path: 'items',                        component: ItemsComponent },
    { path: 'items/:itemId/:groceryListId', component: ItemsComponent },
    { path: 'grocerylist',                  component: GroceryListComponent },
    { path: 'grocerylist/:groceryListId',   component: GroceryListComponent },
    { path: '**',           redirectTo: '/', pathMatch: 'full' },
];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}