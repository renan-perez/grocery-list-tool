package net.grocerylist.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.grocerylist.core.exception.SystemException;
import net.grocerylist.core.model.GroceryList;

@Repository
public class GroceryListDAO extends GenericDAO<GroceryList, Long> {
	
	public GroceryList saveOrUpdate(final GroceryList groceryList) throws SystemException {
		return groceryList.getId() == null ? super.save(groceryList) : super.update(groceryList);
	}
	
	public GroceryList get(final Long id) throws SystemException {
		return super.get(GroceryList.class, id);
	}
	
	public List<GroceryList> list() throws SystemException {
		return (List<GroceryList>) super.list(GroceryList.class);
	}
	
}
