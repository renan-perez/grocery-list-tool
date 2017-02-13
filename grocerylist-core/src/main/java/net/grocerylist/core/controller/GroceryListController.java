package net.grocerylist.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.grocerylist.core.business.GroceryListBusiness;
import net.grocerylist.core.exception.SystemException;
import net.grocerylist.core.model.GroceryList;
import net.grocerylist.core.model.SelectedItem;
import net.grocerylist.core.model.SelectedItemId;

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
	public Boolean saveGroceryList(@RequestBody GroceryList groceryList) {
		try {
			groceryListBusiness.saveGroceryList(groceryList);
			return true;
		} catch (SystemException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping(value = "/newGroceryList", method = RequestMethod.GET)
	public GroceryList newGroceryList() {
		try {
			return groceryListBusiness.saveGroceryList(new GroceryList());
		} catch (SystemException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/removeItemFromList", method = RequestMethod.POST)
	public Boolean removeItemFromList(@RequestBody SelectedItemId selectedItemId) {
		try {
			groceryListBusiness.removeItemFromList(selectedItemId);
			return true;
		} catch (SystemException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping(value = "/addItemsToList", method = RequestMethod.POST)
	public Boolean addItemsToList(@RequestBody List<SelectedItem> selectedItems) {
		try {
			groceryListBusiness.addItemsToList(selectedItems);
			return true;
		} catch (SystemException e) {
			e.printStackTrace();
			return true;
		}
	}
	
	@RequestMapping(value = "/updatePurchaseStatus", method = RequestMethod.POST)
	public Boolean updatePurchaseStatus(@RequestBody SelectedItem selectedItem) {
		try {
			groceryListBusiness.updatePurchaseStatus(selectedItem);
			return true;
		} catch (SystemException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping(value = "/deleteGroceryList/{groceryListId}", method = RequestMethod.POST)
	public Boolean deleteGroceryList(@PathVariable Long groceryListId) {
		try {
			groceryListBusiness.deleteGroceryList(groceryListId);
			return true;
		} catch (SystemException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
