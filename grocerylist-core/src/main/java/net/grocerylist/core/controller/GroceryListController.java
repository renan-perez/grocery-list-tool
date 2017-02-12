package net.grocerylist.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.grocerylist.core.business.GroceryListBusiness;
import net.grocerylist.core.exception.SystemException;
import net.grocerylist.core.model.GroceryList;

@RestController
@RequestMapping("/groceryList")
public class GroceryListController {
	
	@Autowired private GroceryListBusiness groceryListBusiness;
	
	@RequestMapping(value = "/getAllLists", method = RequestMethod.GET)
	public List<GroceryList> getAllLists() {
		try {
			return groceryListBusiness.listGroceryList();
		} catch (SystemException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/getGroceryListById/{id}", method = RequestMethod.GET)
	public GroceryList getGroceryListById(@PathVariable Long id) {
		try {
			return groceryListBusiness.getGroceryListById(id);
		} catch (SystemException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/saveGroceryList", method = RequestMethod.POST)
	public Boolean saveGroceryList(@RequestParam(required = true) GroceryList groceryList) {
		try {
			groceryListBusiness.saveGroceryList(groceryList);
			return true;
		} catch (SystemException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
