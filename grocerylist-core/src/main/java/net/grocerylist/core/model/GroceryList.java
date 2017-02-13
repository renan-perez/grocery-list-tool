package net.grocerylist.core.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
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
	private String name;
	private List<SelectedItem> selectedItems;
	
	public GroceryList() {
	}
	
	public GroceryList(String name) {
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Transient
	public List<SelectedItem> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<SelectedItem> selectedItems) {
		this.selectedItems = selectedItems;
	}

}
