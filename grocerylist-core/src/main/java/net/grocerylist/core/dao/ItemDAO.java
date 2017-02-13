package net.grocerylist.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.grocerylist.core.exception.SystemException;
import net.grocerylist.core.model.Item;

@Repository
public class ItemDAO extends GenericDAO<Item, Long> {
	
	@Transactional
	public Item save(final Item item) throws SystemException {
		return super.save(item);
	}
	
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
	
	public List<Item> listItemsAvailable(final Long groceryListId) throws SystemException {
		Map<String, Object> paramValueMap = new HashMap<>();
		paramValueMap.put("groceryListId", groceryListId);
		return (List<Item>) super.listByNamedQuery("SelectedItem.listAvailableItems", paramValueMap, Item.class);
	}
	
}
