package com.company.rentalstoregroup.controller;
import com.company.rentalstoregroup.service.ServiceLayer;
import com.company.rentalstoregroup.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class invoiceController {
    @Autowired
    private ServiceLayer service;

    //create
    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public InvoiceViewModel createInvoice(@RequestBody InvoiceViewModel invoiceViewModel){
        return service.createInvoiceViewModel(invoiceViewModel);
    }


    //delete
    @RequestMapping(value = "/invoice/{invoiceId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteInvoice(@PathVariable int invoiceId){
        service.deleteInvoiceViewModel(invoiceId);
    }

    //get invoices by customer id
    @RequestMapping(value = "/invoice/{customer_id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    public List<InvoiceViewModel> getInvoicesByCustomer(@PathVariable int customer_id){
        return service.getInvoiceByCustomer(customer_id);
    }
}
