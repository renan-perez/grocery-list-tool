package net.grocerylist.core.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class SelectedItemId implements Serializable {

	private static final long serialVersionUID = 1L;
	private GroceryList groceryList;
	private Item item;
	
	@ManyToOne
    @JoinColumn(name = "grocery_list_id", referencedColumnName = "id", insertable = false, updatable = false)
	public GroceryList getGroceryList() {
		return groceryList;
	}

	public void setGroceryList(GroceryList groceryList) {
		this.groceryList = groceryList;
	}
	
	@ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
