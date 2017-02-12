package net.grocerylist.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.grocerylist.core.model.Item;

@RestController
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Item home() {
        Item item = new Item();
        return item;
    }

}
