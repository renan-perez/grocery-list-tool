package net.grocerylist.core.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.grocerylist.core.dao.ItemDAO;
import net.grocerylist.core.exception.SystemException;
import net.grocerylist.core.model.Item;

@Service
public class ItemBusiness {
	
	@Autowired private ItemDAO itemDAO;
	
	public Item getItemById(final Long id) throws SystemException {
		return itemDAO.get(id);
	}
	
	public List<Item> listItems() throws SystemException {
		return itemDAO.list();
	}
	
	public List<Item> listAvailableItems(final Long groceryListId) throws SystemException {
		return itemDAO.listItemsAvailable(groceryListId);
	}
	
	public Item saveOrUpdateItem(final Item item) throws SystemException {
		return item.getId() == null ? itemDAO.save(item) : itemDAO.update(item);
	}

	public void deleteItem(final Long id) throws SystemException {
		itemDAO.delete(id);
	}
	
}
