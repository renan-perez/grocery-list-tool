package net.grocerylist.core.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(
		    name = "SelectedItem.listByGroceryListId",
		    query = "SELECT si " +
		            "FROM   SelectedItem si " +
		            "WHERE	si.id.groceryList.id = :groceryListId"
    ),
    
    @NamedQuery(
		    name = "SelectedItem.deleteByGroceryListId",
		    query = "DELETE SelectedItem	si " +
		            "WHERE	si.id.groceryList.id = :groceryListId"
    ),
})

@Entity
@Table(schema = "renanpe_grocerylist", name = "selected_item")
public class SelectedItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private SelectedItemId id;
	private Boolean purchase;
	
	@EmbeddedId
	public SelectedItemId getId() {
		return id;
	}

	public void setId(SelectedItemId id) {
		this.id = id;
	}

	public Boolean getPurchase() {
		return purchase;
	}

	public void setPurchase(Boolean purchase) {
		this.purchase = purchase;
	}

}
