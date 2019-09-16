package com.company.rentalstoregroup.controller;

import com.company.rentalstoregroup.service.ServiceLayer;
import com.company.rentalstoregroup.viewmodel.InvoiceViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class invoiceController {
    private ServiceLayer serviceLayer;

    //HTTP methods
    //@RequestMapping(value = "/", method = RequestMethod.*)
    //@ResponseStatus(value = HttpStatus.*)
    //public void methodName(Class class){}
    //will call to service layer

    //create
    @RequestMapping(value = "/invoice", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.CREATED)
    public InvoiceViewModel createInvoice(@RequestBody InvoiceViewModel invoiceViewModel){
        invoiceViewModel = serviceLayer.createInvoiceViewModel(invoiceViewModel);
        return invoiceViewModel;
    }


    //delete
    @RequestMapping(value = "/invoice/{invoiceId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteInvoice(@PathVariable int invoiceId){
        serviceLayer.deleteInvoiceViewModel(invoiceId);
    }

    //get invoices by customer id
    @RequestMapping(value = "/invoice/{customer_id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    public List<InvoiceViewModel> getInvoicesByCustomer(@PathVariable int customerId){
        List<InvoiceViewModel> ivmList = serviceLayer.getInvoiceByCustomer(customerId);
        return ivmList;
    }
}
