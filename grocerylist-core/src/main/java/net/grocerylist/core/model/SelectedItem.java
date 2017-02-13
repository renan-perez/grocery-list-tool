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
		            "WHERE	si.id.groceryList.id = :groceryListId " +
		            "ORDER BY si.id.item.name"
    ),
    @NamedQuery(
		    name = "SelectedItem.deleteByGroceryListId",
		    query = "DELETE SelectedItem	si " +
		            "WHERE	si.id.groceryList.id = :groceryListId"
    ),
    @NamedQuery(
		    name = "SelectedItem.listAvailableItems",
		    query = "SELECT	i " +
		    		"FROM 	Item i " +
		    		"WHERE 	i.id " +
		    		"NOT IN (	SELECT 		si.id.item.id " +
		            "			FROM   		SelectedItem si " +
		    		"			INNER JOIN	si.id.item " +
		            "			WHERE		si.id.groceryList.id = :groceryListId)"	
    ),
    @NamedQuery(
		    name = "SelectedItem.updatePurchaseStatus",
		    query = "UPDATE SelectedItem	si " +
		    		"SET	si.purchase				= :purchase " +
		            "WHERE	si.id.groceryList.id 	= :groceryListId " +
		    		"AND	si.id.item.id 			= :itemId"	
    ),
})

@Entity
@Table(schema = "renanpe_grocerylist", name = "selected_item")
public class SelectedItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private SelectedItemId id;
	private Boolean purchase;
	
	public SelectedItem() {
	}
	
	public SelectedItem(SelectedItemId id) {
		this.id = id;
	}
	
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
