package com.company.rentalstoregroup.controller;

import com.company.rentalstoregroup.dto.Item;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class itemController {

    //HTTP methods
    //@RequestMapping(value = "/", method = RequestMethod.*)
    //@ResponseStatus(value = HttpStatus.*)
    //public void methodName(Class class){}
    //will call to service layer

    private List<Item> itemList = new ArrayList<>();

    //Post
    @RequestMapping(value = "/item", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Item createItem(@RequestBody @Valid Item item){

        itemList.add(item);

        return item;

    }

    //Get
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Item getItem(@PathVariable int itemId){

        for(Item item : itemList) {
            if (Objects.equals(item.getItem_id(), itemId))
                return item;
        }

        throw new IllegalArgumentException("Item not found.");
    }

    //Put
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateItem(@RequestBody @Valid Item item, @PathVariable int itemId){

        if (itemId != item.getItem_id()){
            throw new IllegalArgumentException("Item ID on path must match the ID in the item object");
        }

    }

    //Delete
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable("itemId") int itemId){

        int index = 0;
        boolean found = true;

        if (found)
            itemList.remove(index);
        else throw new IllegalArgumentException("Item not found.");
    }
}
