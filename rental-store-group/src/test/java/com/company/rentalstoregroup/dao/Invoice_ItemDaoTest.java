package com.company.rentalstoregroup.dao;
import com.company.rentalstoregroup.dto.Customer;
import com.company.rentalstoregroup.dto.Invoice;
import com.company.rentalstoregroup.dto.Invoice_Item;
import com.company.rentalstoregroup.dto.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Invoice_ItemDaoTest {
    // Properties
    @Autowired
    protected CustomerDaoJdbcTemplateImpl customerDao;
    @Autowired
    protected Invoice_ItemDaoJdbcTemplateImpl invoice_itemDao;
    @Autowired
    protected InvoiceDaoJdbcTemplateImpl invoiceDao;
    @Autowired
    protected ItemDaoJdbcTemplateImpl itemDao;

    // setUp()
    @Before
    public void setUp() throws Exception {
        // Clean the Customer database
        List<Customer> customerList = CustomerDao.getAllCustomer();
        customerList.forEach(customer -> customerDao.deleteCustomer(customer.getCustomer_id()));

        // Clean the Invoice_Item database
        List<Invoice_Item> invoice_itemList = invoice_itemDao.getAllInvoice_Item();
        invoice_itemList.forEach(invoice_item -> invoice_itemDao.deleteInvoice_Item(invoice_item.getInvoice_item_id()));

        // Clean the Invoice database
        List<Invoice> invoiceList = invoiceDao.getAllInvoice();
        invoiceList.forEach(invoice -> invoiceDao.deleteInvoice(invoice.getInvoice_id()));

        // Clean the Item database
        List<Item> itemList = itemDao.getAllItem();
        itemList.forEach(item -> itemDao.deleteItem(item.getItem_id()));
    }

    // Method Tests
    @Test
    public void addGetDeleteInvoice_ItemTest() {
        // Create and add a new Customer to the database
        Customer customer = new Customer();
        customer.setFirst_name("Firstname");
        customer.setLast_name("Lastname");
        customer.setEmail("email@gmail.com");
        customer.setCompany("Company");
        customer.setPhone("999-999-9999");
        customer = customerDao.addCustomer(customer);

        // Create and add a new Invoice to the database
        Invoice invoice = new Invoice();
        invoice.setCustomer_id(customer.getCustomer_id());
        invoice.setOrder_date(LocalDate.of(2000,1,1));
        invoice.setPickup_date(LocalDate.of(2000,1,1));
        invoice.setReturn_date(LocalDate.of(2000,1,1));
        invoice.setLate_fee(new BigDecimal("14.99"));
        invoice = invoiceDao.addInvoice(invoice);

        // Create and add an Item to the database
        Item item = new Item();
        item.setName("Test Item");
        item.setDescription("Test Description");
        item.setDaily_rate(new BigDecimal("1.99"));
        item = itemDao.addItem(item);

        // Create and add a new Invoice_Item to the database
        Invoice_Item invoice_item = new Invoice_Item();
        invoice_item.setInvoice_id(getInvoice_id());
        invoice_item.setItem_id(getItem_id());
        invoice_item.setQuantity(2);
        invoice_item.setUnit_rate(new BigDecimal("1.99"));
        invoice_item.setDiscount(new BigDecimal("9.99"));
        invoice_item = invoice_itemDao.addInvoice_Item(invoice_item);
    }
}
