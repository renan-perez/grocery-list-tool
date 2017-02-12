package net.grocerylist.core.business;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.grocerylist.core.dao.GroceryListDAO;
import net.grocerylist.core.dao.SelectedItemDAO;
import net.grocerylist.core.exception.SystemException;
import net.grocerylist.core.model.GroceryList;
import net.grocerylist.core.model.SelectedItem;

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
	
	public void saveGroceryList(final GroceryList groceryList) throws SystemException {
		GroceryList groceryListSaved = groceryListDAO.saveOrUpdate(groceryList);
		if (Objects.nonNull(groceryList.getSelectedItems())) {
			groceryList.getSelectedItems().forEach(si -> si.getId().setGroceryList(groceryListSaved));
			selectedItemDAO.deleteByGroceryListId(groceryListSaved.getId());
			selectedItemDAO.saveAll(groceryList.getSelectedItems());
		}
	}
	
}
