package net.grocerylist.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.grocerylist.core.exception.SystemException;
import net.grocerylist.core.model.SelectedItem;
import net.grocerylist.core.model.SelectedItemId;

@Repository
public class SelectedItemDAO extends GenericDAO<SelectedItem, SelectedItemId> {
	
	@Transactional
	public void saveAll(final List<SelectedItem> selectedItems) throws SystemException {
		super.saveAll(selectedItems);
	}
	
	@Transactional
	public void deleteById(final SelectedItemId id) throws SystemException {
		super.delete(SelectedItem.class, id);
	}
	
	@Transactional
	public void deleteByGroceryListId(final Long groceryListId) throws SystemException {
		Map<String, Object> paramValueMap = new HashMap<>();
		paramValueMap.put("groceryListId", groceryListId);
		super.executeNamedQuery("SelectedItem.deleteByGroceryListId", paramValueMap, null);
	}
	
	public List<SelectedItem> listByGroceryList(final Long groceryListId) throws SystemException {
		Map<String, Object> paramValueMap = new HashMap<>();
		paramValueMap.put("groceryListId", groceryListId);
		return (List<SelectedItem>) super.listByNamedQuery("SelectedItem.listByGroceryListId", paramValueMap, SelectedItem.class);
	}
	
	@Modifying
	@Transactional
	public void updatePurchaseStatus(final SelectedItem selectedItem) throws SystemException {
		Map<String, Object> paramValueMap = new HashMap<>();
		paramValueMap.put("purchase", selectedItem.getPurchase());
		paramValueMap.put("groceryListId", selectedItem.getId().getGroceryList().getId());
		paramValueMap.put("itemId", selectedItem.getId().getItem().getId());
		super.executeNamedQuery("SelectedItem.updatePurchaseStatus", paramValueMap, null);
	}
	
}
