package net.grocerylist.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import net.grocerylist.core.exception.SystemException;
import net.grocerylist.core.model.SelectedItem;
import net.grocerylist.core.model.SelectedItemId;

@Repository
public class SelectedItemDAO extends GenericDAO<SelectedItem, SelectedItemId> {
	
	public void saveAll(final List<SelectedItem> selectedItems) throws SystemException {
		super.saveAll(selectedItems);
	}
	
	public void deleteByGroceryListId(final Long groceryListId) throws SystemException {
		Map<String, Object> paramValueMap = new HashMap<>();
		paramValueMap.put("groceryListId", groceryListId);
		super.executeNamedQuery("SelectedItem.deleteByGroceryListId", paramValueMap, SelectedItem.class);
	}
	
	public List<SelectedItem> listByGroceryList(final Long groceryListId) throws SystemException {
		Map<String, Object> paramValueMap = new HashMap<>();
		paramValueMap.put("groceryListId", groceryListId);
		return (List<SelectedItem>) super.listByNamedQuery("SelectedItem.listByGroceryListId", paramValueMap, SelectedItem.class);
	}
	
}
