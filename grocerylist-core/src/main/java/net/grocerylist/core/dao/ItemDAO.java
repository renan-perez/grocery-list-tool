package net.grocerylist.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.grocerylist.core.exception.SystemException;
import net.grocerylist.core.model.Item;

@Repository
public class ItemDAO extends GenericDAO<Item, Long> {
	
	@Transactional
	public Item update(final Item item) throws SystemException {
		Item itemToUpdate = get(item.getId());
		itemToUpdate.setName(item.getName());
		itemToUpdate.setNotes(item.getNotes());
		return super.update(itemToUpdate);
	}
	
	public Item get(Long id) throws SystemException {
		return super.get(Item.class, id);
	}
	
	public List<Item> list() throws SystemException {
		return (List<Item>) super.list(Item.class);
	}
	
}
