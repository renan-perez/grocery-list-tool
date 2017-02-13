import { Component, OnInit }      from '@angular/core';
import { Router }                 from '@angular/router';
import { ActivatedRoute }         from '@angular/router';
import { ItemService }            from '../service/item.service';
import { Item }                   from '../model/item'

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.css']
})
export class ItemsComponent implements OnInit {

  items: Item[];
  itemDetailed: Item;
  itemDetailedBackup: Item;
  itemSelectedToDelete: Item;
  updateActive: Boolean;
  itemId: Number;
  groceryListId: Number;
  newItemStatus: Boolean;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private itemService: ItemService
  ) { }

  ngOnInit() {
    this.itemDetailed = null;
    this.newItemStatus = false;

    this.route.params.subscribe((param: any) => {
            this.itemId = param['itemId'];
            this.groceryListId = param['groceryListId'];
        });
    
    if (this.itemId == undefined) {
      this.listItems();
    } else {
      this.getItemById(this.itemId);
    }
    
    this.updateActive = false;
  }

  listItems() {
    this.itemService.listItems()
        .subscribe(
          (items: Item[]) => this.items = items,
          err => console.log(err)
        );
  }

  getItemById(id: Number) {
    this.itemService.getItemById(id)
        .subscribe(
          (item: Item) => this.itemDetailed = item,
          err => console.log(err)
        );
  }

  saveOrupdateItem(item: Item) {
    this.itemService.saveOrupdateItem(item)
        .subscribe(
          (success: Boolean) => null,
          err => console.log(err),
          () => this.posSaveOrUpdate(item)
        );
  }

  cancelSaveOrUpdate() {
    if (this.groceryListId != undefined) {
      this.router.navigate([`/grocerylist/${this.groceryListId}`], { skipLocationChange: true });
    }
    this.itemDetailed = null;
    this.newItemStatus = false;
    this.updateActive = false;
  }

  posSaveOrUpdate(item: Item) {
      this.updateActive = false;
      this.newItemStatus = false;
      this.itemDetailed = item;
      this.listItems();
  }

  detailItem(item: Item) {
    this.itemDetailed = new Item(item);
    this.itemDetailedBackup = new Item(item);
    this.updateActive = false;
  }

  updateStatus(updateStatus: Boolean) {
    this.updateActive = updateStatus;

    if (updateStatus == false && this.itemDetailed != null) {
      this.itemDetailed.name = this.itemDetailedBackup.name;
      this.itemDetailed.notes = this.itemDetailedBackup.notes;
    } 
  }

  newItemStatusUpdate(status: Boolean) {
    this.newItemStatus = status;
    this.itemDetailed = new Item(null);
  }

  selectItemToDelete(item: Item) {
    this.itemSelectedToDelete = item;
  }

  deleteItem() {
    this.itemService.deleteItem(this.itemSelectedToDelete.id)
      .subscribe(
        (success: Boolean) => null,
        err => console.log(err),
        () => this.listItems()
      );
  }

}
