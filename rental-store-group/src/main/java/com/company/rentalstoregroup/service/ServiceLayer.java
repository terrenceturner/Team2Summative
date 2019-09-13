package com.company.rentalstoregroup.service;

import com.company.rentalstoregroup.dao.CustomerDao;
import com.company.rentalstoregroup.dao.InvoiceDao;
import com.company.rentalstoregroup.dao.Invoice_ItemDao;
import com.company.rentalstoregroup.dao.ItemDao;
import com.company.rentalstoregroup.dto.Invoice_Item;
import com.company.rentalstoregroup.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceLayer {


    private CustomerDao customerDao;
    private ItemDao itemDao;
    private Invoice_ItemDao invoice_itemDao;
    private InvoiceDao invoiceDao;

    @Autowired
    public ServiceLayer(CustomerDao customerDao, ItemDao itemDao, Invoice_ItemDao invoice_itemDao, InvoiceDao invoiceDao) {
        this.customerDao = customerDao;
        this.itemDao = itemDao;
        this.invoice_itemDao = invoice_itemDao;
        this.invoiceDao = invoiceDao;
    }

    //Invoice API



    // Item API
    public Item saveItem(Item item) {

        return itemDao.addItem(item);
    }

    public Item findItem(int id) {

        return itemDao.getItem(id);
    }

    public List<Item> findAllItems() {

        return itemDao.getAllItems();
    }

    public void updateItem(Item item) {

        itemDao.updateItem(item);
    }

    public void removeItem(int id) {

        itemDao.deleteItem(id);
    }

    //Customer API


}
