package net.grocerylist.core.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public void updateItem(final Item item) throws SystemException {
		itemDAO.update(item);
	}
}
