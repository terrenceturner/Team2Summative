package com.company.rentalstoregroup.service;

import com.company.rentalstoregroup.dao.CustomerDao;
import com.company.rentalstoregroup.dao.InvoiceDao;
import com.company.rentalstoregroup.dao.Invoice_ItemDao;
import com.company.rentalstoregroup.dao.ItemDao;
import com.company.rentalstoregroup.dto.Invoice;
import com.company.rentalstoregroup.dto.Invoice_Item;
import com.company.rentalstoregroup.dto.Item;
import com.company.rentalstoregroup.viewmodel.InvoiceViewModel;
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
    public InvoiceViewModel createInvoiceViewModel(InvoiceViewModel invoiceViewModel){
        Invoice invoice = new Invoice(
                invoiceViewModel.getCustomer_id(),
                invoiceViewModel.getOrder_date(),
                invoiceViewModel.getPickup_date(),
                invoiceViewModel.getReturn_date(),
                invoiceViewModel.getLate_fee()
        );

        invoice = invoiceDao.addInvoice(invoice);
        invoiceViewModel.setInvoice_id(invoice.getInvoice_id());

        List<Invoice_Item> invoice_list = invoiceViewModel.getInvoice_items();

        invoice_list.forEach(i->
        {
            i.setInvoice_id(invoiceViewModel.getInvoice_id());
            invoice_itemDao.addInvoice_Item(i);
        });

        List<Invoice_Item> db_list = invoice_itemDao.getInvoice_ItemByInvoice(invoiceViewModel.getInvoice_id());

        invoiceViewModel.setInvoice_items(db_list);

        return invoiceViewModel;
    }

    public void deleteInvoiceViewModel(int invoice_id){
        List<Invoice_Item> invoice_itemList = invoice_itemDao.getInvoice_ItemByInvoice(invoice_id);

        invoice_itemList.forEach(i -> invoice_itemDao.deleteInvoice_Item(i.getInvoice_item_id()));

        invoiceDao.deleteInvoice(invoice_id);
    }


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
