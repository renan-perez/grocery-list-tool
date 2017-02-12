package net.grocerylist.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.grocerylist.core.business.ItemBusiness;
import net.grocerylist.core.exception.SystemException;
import net.grocerylist.core.model.Item;

@RestController
@RequestMapping("/items")
public class ItemController {
	
	@Autowired private ItemBusiness itemBusiness;
	
	@RequestMapping(value = "/getItemDetails/{id}", method = RequestMethod.GET)
	public Item getItemDetails(@PathVariable Long id) {
		try {
			return itemBusiness.getItemById(id);
		} catch (SystemException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/getAllItems", method = RequestMethod.GET)
	public List<Item> getAllItems() {
		try {
			return itemBusiness.listItems();
		} catch (SystemException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/updateItem", method = RequestMethod.POST)
	public Boolean update(@RequestBody Item item) {
		try {
			itemBusiness.updateItem(item);
			return true;
		} catch (SystemException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
