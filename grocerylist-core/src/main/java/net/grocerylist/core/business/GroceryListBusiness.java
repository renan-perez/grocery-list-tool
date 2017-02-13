package net.grocerylist.core.business;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.grocerylist.core.dao.GroceryListDAO;
import net.grocerylist.core.dao.SelectedItemDAO;
import net.grocerylist.core.exception.SystemException;
import net.grocerylist.core.model.GroceryList;
import net.grocerylist.core.model.SelectedItem;
import net.grocerylist.core.model.SelectedItemId;

@Service
public class GroceryListBusiness {
	
	@Autowired private GroceryListDAO groceryListDAO;
	@Autowired private SelectedItemDAO selectedItemDAO;
	
	public List<GroceryList> listGroceryList() throws SystemException {
		return groceryListDAO.list();
	}
	
	public GroceryList getGroceryListById(final Long id) throws SystemException {
		GroceryList groceryList = groceryListDAO.get(id);
		List<SelectedItem> selectedItems = selectedItemDAO.listByGroceryList(id);
		groceryList.setSelectedItems(selectedItems);
		return groceryList;
	}
	
	public GroceryList saveGroceryList(final GroceryList groceryList) throws SystemException {
		GroceryList groceryListSaved = groceryListDAO.saveOrUpdate(groceryList);
		if (Objects.nonNull(groceryList.getSelectedItems())) {
			groceryList.getSelectedItems().forEach(si -> si.getId().setGroceryList(groceryListSaved));
			selectedItemDAO.deleteByGroceryListId(groceryListSaved.getId());
			selectedItemDAO.saveAll(groceryList.getSelectedItems());
		}
		return groceryListSaved;
	}
	
	public void removeItemFromList(final SelectedItemId selectedItemId) throws SystemException {
		selectedItemDAO.deleteById(selectedItemId);
	}
	
	public void addItemsToList(List<SelectedItem> listSelectedItem) throws SystemException {
		selectedItemDAO.saveAll(listSelectedItem);
	}
	
	public void updatePurchaseStatus(SelectedItem selectedItem) throws SystemException {
		selectedItem.setPurchase(selectedItem.getPurchase() == false ? null : true);
		selectedItemDAO.updatePurchaseStatus(selectedItem);
	}
	
	@Transactional
	public void deleteGroceryList(final Long groceryListId) throws SystemException {
		selectedItemDAO.deleteByGroceryListId(groceryListId);
		groceryListDAO.delete(groceryListId);
	}
	
}
