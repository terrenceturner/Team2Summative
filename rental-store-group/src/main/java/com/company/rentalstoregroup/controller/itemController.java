package com.company.rentalstoregroup.controller;
import com.company.rentalstoregroup.dto.Item;
import com.company.rentalstoregroup.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class itemController {
    // Properties
    @Autowired
    private ServiceLayer service;

    // Post
    @RequestMapping(value = "/item", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Item addItem(@RequestBody Item item){
        return service.saveItem(item);
    }

    // Get Single Item
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Item getItem(@PathVariable int itemId){
        return service.findItem(itemId);
    }

    // Get All Items
    @RequestMapping(value = "/item", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Item> getItem(){
        return service.findAllItems();
    }

    // Put
    @RequestMapping(value = "/item", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateItem(@RequestBody Item item){
        service.updateItem(item);
    }

    // Delete
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable int itemId) {
        service.removeItem(itemId);
    }
}
