package com.company.rentalstoregroup.controller;

import com.company.rentalstoregroup.dto.Customer;
import com.company.rentalstoregroup.service.ServiceLayer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class customerController {

    private ServiceLayer service;

    // Post
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody @Valid Customer customer){
        return service.saveCustomer(customer);
    }

    //Get

    @RequestMapping(value = "/customers/{custId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Customer getCustomer(@PathVariable (name = "custId") int custId){
        return service.findCustomer(custId);
    }

    //put
    @RequestMapping(value = "/customers/{custId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody Customer customer, @PathVariable (name = "custId") int custId){
        service.updateCustomer(customer);
    }

    //delete
    @RequestMapping(value = "/customers/{custId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable(name = "custId") int custId){
        service.removeItem(custId);
    }



}
