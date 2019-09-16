package com.company.rentalstoregroup.service;
import com.company.rentalstoregroup.dao.CustomerDao;
import com.company.rentalstoregroup.dao.InvoiceDao;
import com.company.rentalstoregroup.dao.Invoice_ItemDao;
import com.company.rentalstoregroup.dao.ItemDao;
import com.company.rentalstoregroup.dto.Customer;
import com.company.rentalstoregroup.dto.Invoice;
import com.company.rentalstoregroup.dto.Invoice_Item;
import com.company.rentalstoregroup.dto.Item;
import com.company.rentalstoregroup.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {
    // Properties
    private CustomerDao customerDao;
    private ItemDao itemDao;
    private Invoice_ItemDao invoice_itemDao;
    private InvoiceDao invoiceDao;

    // Constructor
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

    public InvoiceViewModel getInvoiceViewModel(int invoice_id) {
        // Create a new InvoiceViewModel
        InvoiceViewModel ivm = new InvoiceViewModel();
        Invoice invoice = invoiceDao.getInvoice(invoice_id);
        ivm.setInvoice_id(invoice_id);
        ivm.setCustomer_id(invoice.getCustomer_id());
        ivm.setOrder_date(invoice.getOrder_date());
        ivm.setPickup_date(invoice.getPickup_date());
        ivm.setReturn_date(invoice.getReturn_date());
        ivm.setLate_fee(invoice.getLate_fee());
        ivm.setInvoice_items(invoice_itemDao.getInvoice_ItemByInvoice(invoice_id));
        return ivm;
    }

    public void deleteInvoiceViewModel(int invoice_id){
        List<Invoice_Item> invoice_itemList = invoice_itemDao.getInvoice_ItemByInvoice(invoice_id);

        invoice_itemList.forEach(i -> invoice_itemDao.deleteInvoice_Item(i.getInvoice_item_id()));

        invoiceDao.deleteInvoice(invoice_id);
    }

    public List<InvoiceViewModel> getInvoiceByCustomer(int customer_id) {
        List<Invoice> invoiceList = invoiceDao.getInvoicesByCustomer(customer_id);

        List<InvoiceViewModel> invoiceViewModelList = new ArrayList<>();

        invoiceList.forEach(invoice -> invoiceViewModelList.add(buildInvoiceViewModel(invoice)));

        return invoiceViewModelList;
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
    public Customer saveCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }

    public Customer findCustomer(int id) {
        return customerDao.getCustomer(id);
    }

    public List<Customer> findAllCustomers() {
        return customerDao.findAllCustomer();
    }

    public void updateCustomer(Customer customer) {
        customerDao.updateCustomer(customer);
    }

    public void removeCustomer(int id) {
        customerDao.deleteCustomer(id);
    }

    // Build InvoiceView
    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
        // Get the associated customer
        Customer customer = customerDao.getCustomer(invoice.getCustomer_id());

        // Get the invoiceItems associated with the invoice
        List<Invoice_Item> invoiceItemList = invoice_itemDao.getInvoice_ItemByInvoice(invoice.getInvoice_id());

        // Assemble the InvoiceViewModel
        InvoiceViewModel invoiceView = new InvoiceViewModel();
        invoiceView.setInvoice_id(invoice.getInvoice_id());
        invoiceView.setCustomer_id(customer.getCustomerId());
        invoiceView.setOrder_date(invoice.getOrder_date());
        invoiceView.setPickup_date(invoice.getPickup_date());
        invoiceView.setReturn_date(invoice.getReturn_date());
        invoiceView.setLate_fee(invoice.getLate_fee());
        invoiceView.setInvoice_items(invoiceItemList);

        // Return the invoiceView
        return invoiceView;
    }
}
