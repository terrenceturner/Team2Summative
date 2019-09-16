package com.company.rentalstoregroup.controller;

import com.company.rentalstoregroup.dto.Item;
import com.company.rentalstoregroup.service.ServiceLayer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class itemController {

    private ServiceLayer service;


    //Post
    @RequestMapping(value = "/item", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Item addItem(@RequestBody @Valid Item item){

        return service.saveItem(item);
    }

    //Get
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Item getItem(@PathVariable (name = "itemId") int itemId){

        return service.findItem(itemId);
    }

    //Put
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateItem(@RequestBody Item item, @PathVariable (name = "itemId") int itemId){

        service.saveItem(item);

    }

    //Delete
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable("itemId") int itemId) {

        service.removeItem(itemId);

    }
}
