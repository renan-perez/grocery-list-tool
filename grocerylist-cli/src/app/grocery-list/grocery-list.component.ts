import { Component, OnInit }  from '@angular/core';
import { ActivatedRoute }     from '@angular/router';
import { ItemService }        from '../service/item.service';
import { GroceryListService } from '../service/grocery-list.service';
import { Item }               from '../model/item'
import { GroceryList }        from '../model/grocery-list'

@Component({
  selector: 'app-grocery-list',
  templateUrl: './grocery-list.component.html',
  styleUrls: ['./grocery-list.component.css']
})
export class GroceryListComponent implements OnInit {

  groceryLists: GroceryList[];
  groceryListDetailed: GroceryList;
  groceryListId: Number;

  constructor(
    private route: ActivatedRoute,
    private itemService: ItemService,
    private groceryListService: GroceryListService
  ) { }

  ngOnInit() {
    this.route.params.subscribe((param: any) => {
            this.groceryListId = param['groceryListId'];
        });
    
    if (this.groceryListId != undefined) {
      this.detailGroceryList(this.groceryListId);
    } else {
      this.getAllLists();
    }
  }

  getAllLists() {
    this.groceryListService.getAllLists()
        .subscribe(
          (groceryLists: GroceryList[]) => this.groceryLists = groceryLists,
          err => console.log(err)
        );
  }

  detailGroceryList(id: Number) {
    this.groceryListService.getGroceryListById(id)
        .subscribe(
          (groceryList: GroceryList) => this.groceryListDetailed = groceryList,
          err => console.log(err),
          () => console.log(JSON.stringify(this.groceryListDetailed.selectedItems))
        );
  }

}
