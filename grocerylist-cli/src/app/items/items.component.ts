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
  updateActive: Boolean;
  itemId: Number;
  groceryListId: Number;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private itemService: ItemService
  ) { }

  ngOnInit() {
    this.itemDetailed = null;

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

  updateItem(item: Item) {
    this.itemService.updateItem(item)
        .subscribe(
          (success: Boolean) => this.updateActive = !success,
          err => console.log(err),
          () => this.posUpdate(item)
        );
  }

  cancelUpdate() {
    if (this.groceryListId != undefined) {
      this.router.navigate([`/grocerylist/${this.groceryListId}`], { skipLocationChange: true });
    }
    this.itemDetailed = null;
  }

  posUpdate(item: Item) {
    if (!this.updateActive) {
      this.listItems();
      this.itemDetailed = item;
    }
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

}
