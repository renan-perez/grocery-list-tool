package net.grocerylist.core.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@NamedQueries({
    @NamedQuery(
		    name = "GroceryList.list",
		    query = "SELECT l " +
		            "FROM   GroceryList l"
    ),
})

@Entity
@Table(schema = "renanpe_grocerylist", name = "grocery_list")
public class GroceryList implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private List<SelectedItem> selectedItems;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Transient
	public List<SelectedItem> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<SelectedItem> selectedItems) {
		this.selectedItems = selectedItems;
	}

}
