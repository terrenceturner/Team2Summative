package com.company.rentalstoregroup.controller;
import com.company.rentalstoregroup.dto.Customer;
import com.company.rentalstoregroup.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class customerController {
    @Autowired
    private ServiceLayer service;

    // Post
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer) {
        return service.saveCustomer(customer);
    }

    // Get
    @RequestMapping(value = "/customer/{custId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Customer getCustomer(@PathVariable int custId) {
        return service.findCustomer(custId);
    }

    // Get All
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Customer> getAllCustomer() {
        return service.findAllCustomers();
    }

    // Put
    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody Customer customer) {
        service.updateCustomer(customer);
    }

    // Delete
    @RequestMapping(value = "/customer/{custId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable int custId){
        service.removeItem(custId);
    }



}
