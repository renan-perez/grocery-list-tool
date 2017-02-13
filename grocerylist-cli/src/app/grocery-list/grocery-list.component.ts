import { Component, OnInit }  from '@angular/core';
import { ActivatedRoute }     from '@angular/router';

import { ItemService }        from '../service/item.service';
import { GroceryListService } from '../service/grocery-list.service';

import { Item }               from '../model/item'
import { GroceryList }        from '../model/grocery-list'
import { SelectedItem }       from '../model/selected-item';
import { SelectedItemId }     from '../model/selected-item-id';

@Component({
  selector: 'app-grocery-list',
  templateUrl: './grocery-list.component.html',
  styleUrls: ['./grocery-list.component.css']
})
export class GroceryListComponent implements OnInit {

  groceryLists: GroceryList[];
  groceryListDetailed: GroceryList;
  groceryListId: Number;
  success: Boolean;
  selectedItemIdToRemove: SelectedItemId;
  availableItems: Item[];
  updateStatus: Boolean;
  selectedListToRemove: GroceryList;

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
        );
  }

  selectItemToRemove(selectedItemId: SelectedItemId) {
    this.selectedItemIdToRemove = selectedItemId;
  }


  removeItemFromList() {
    this.groceryListService.removeItemFromList(this.selectedItemIdToRemove)
        .subscribe(
          (success: Boolean) => this.posDeleteItemFromList(success),
          err => console.log(err)
        );
  }

  posDeleteItemFromList(success: Boolean) {
    if (success) {
      this.detailGroceryList(this.groceryListDetailed.id);
    }
  }

  listAvailableItems() {
    this.availableItems = [];
    this.itemService.listAvailableItems(this.groceryListDetailed.id)
        .subscribe(
          (availableItems: Item[]) => this.availableItems = availableItems,
          err => console.log(err),
          () => this.availableItems = this.availableItems.length == 0? null : this.availableItems
        );
  }

  addItemsToList() {
    const selectedItems: SelectedItem[] = [];

    this.availableItems.forEach(item => {
      if (item.selected == true) {
        let selectedItemId: SelectedItemId = new SelectedItemId();
        selectedItemId.groceryList = this.groceryListDetailed;
        selectedItemId.item = item;
        let selectecItem: SelectedItem = new SelectedItem();
        selectecItem.id = selectedItemId;
        selectecItem.purchase = null;
        selectedItems.push(selectecItem);
      }
    });

    if (selectedItems.length == 0) {
      return;
    }

    this.groceryListService.addItemsToList(selectedItems)
        .subscribe(
          (success: Boolean) => this.detailGroceryList(this.groceryListDetailed.id),
          err => console.log(err)
        );
  }

    updatePurchaseStatus(selectedItem: SelectedItem) {
      selectedItem.purchase = !selectedItem.purchase;
      this.groceryListService.updatePurchaseStatus(selectedItem)
        .subscribe(
          (success: Boolean) => success ? selectedItem.purchase = selectedItem.purchase : !selectedItem.purchase,
          err => selectedItem.purchase = !selectedItem.purchase
        );
    }

    newGroceryList() {
      this.groceryListService.newGroceryList()
        .subscribe(
          (groceryList: GroceryList) => this.groceryListDetailed = groceryList,
          err => console.log(err),
        );
    }

    backToLists() {
      this.groceryListDetailed = null;
      this.getAllLists();
    }

    selectGroceryListToRemove(selectedListToRemove: GroceryList) {
      this.selectedListToRemove = selectedListToRemove;
    }

    deleteGroceryList() {
      this.groceryListService.deleteGroceryList(this.selectedListToRemove.id)
        .subscribe(
          (success: Boolean) => null,
          err => console.log(err),
          () => this.getAllLists()
        );
    }

    updateListName() {
      this.groceryListService.saveGroceryList(this.groceryListDetailed)
        .subscribe(
          (success: Boolean) => null,
          err => console.log(err)
        );
      this.changeUpdateStatus(false);
    }

    changeUpdateStatus(status: Boolean) {
      this.updateStatus = status;
    }

}
