package net.grocerylist.core.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(
		    name = "Item.list",
		    query = "SELECT i " +
		            "FROM   Item i"
    ),
})

@Entity
@Table(schema = "renanpe_grocerylist", name = "item")
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String notes;
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(length = 50, nullable =  false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(length = 200, nullable =  false)
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}