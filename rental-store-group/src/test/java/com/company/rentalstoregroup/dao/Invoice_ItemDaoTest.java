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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
        // Clean the Invoice_Item database
        List<Invoice_Item> invoice_itemList = invoice_itemDao.getAllInvoice_Item();
        invoice_itemList.forEach(invoice_item -> invoice_itemDao.deleteInvoice_Item(invoice_item.getInvoice_item_id()));

        // Clean the Invoice database
        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        invoiceList.forEach(invoice -> invoiceDao.deleteInvoice(invoice.getInvoice_id()));

        // Clean the Item database
        List<Item> itemList = itemDao.getAllItems();
        itemList.forEach(item -> itemDao.deleteItem(item.getItem_id()));

        // Clean the Customer database
        List<Customer> customerList = customerDao.findAllCustomer();
        customerList.forEach(customer -> customerDao.deleteCustomer(customer.getCustomerId()));
    }

    // Method Tests
    @Test
    public void addGetDeleteInvoice_ItemTest() {
        // Create and add a new Customer to the database
        Customer customer = new Customer();
        customer.setFirstName("Firstname");
        customer.setLastName("Lastname");
        customer.setEmail("email@gmail.com");
        customer.setCompany("Company");
        customer.setPhone("999-999-9999");
        customer = customerDao.addCustomer(customer);

        // Create and add a new Invoice to the database
        Invoice invoice = new Invoice();
        invoice.setCustomer_id(customer.getCustomerId());
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
        invoice_item.setInvoice_id(invoice.getInvoice_id());
        invoice_item.setItem_id(item.getItem_id());
        invoice_item.setQuantity(2);
        invoice_item.setUnit_rate(new BigDecimal("1.99"));
        invoice_item.setDiscount(new BigDecimal("9.99"));
        invoice_item = invoice_itemDao.addInvoice_Item(invoice_item);

        // Create a copy of invoice_Item
        Invoice_Item invoice_itemCopy = invoice_itemDao.getInvoice_Item(invoice_item.getInvoice_item_id());

        // Test addInvoice_Item() and getInvoice_Item() methods
        assertEquals(invoice_itemCopy, invoice_item);

        // Delete invoice_item from the database
        invoice_itemDao.deleteInvoice_Item(invoice_item.getInvoice_item_id());
        invoice_itemCopy = invoice_itemDao.getInvoice_Item(invoice_item.getInvoice_item_id());

        // Test deleteInvoice_Item() method
        assertNull(invoice_itemCopy);
    }

    @Test
    public void getAllInvoice_ItemTest() {
        // Create and add a new Customer to the database
        Customer customer = new Customer();
        customer.setFirstName("Firstname");
        customer.setLastName("Lastname");
        customer.setEmail("email@gmail.com");
        customer.setCompany("Company");
        customer.setPhone("999-999-9999");
        customer = customerDao.addCustomer(customer);

        // Create and add a new Invoice to the database
        Invoice invoice = new Invoice();
        invoice.setCustomer_id(customer.getCustomerId());
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

        // Create and add a new Invoice_Item to the database (invoice_item1)
        Invoice_Item invoice_item1 = new Invoice_Item();
        invoice_item1.setInvoice_id(invoice.getInvoice_id());
        invoice_item1.setItem_id(item.getItem_id());
        invoice_item1.setQuantity(2);
        invoice_item1.setUnit_rate(new BigDecimal("1.99"));
        invoice_item1.setDiscount(new BigDecimal("9.99"));
        invoice_item1 = invoice_itemDao.addInvoice_Item(invoice_item1);

        // Create and add a second Invoice_Item to the database (invoice_item2)
        Invoice_Item invoice_item2 = new Invoice_Item();
        invoice_item2.setInvoice_id(invoice.getInvoice_id());
        invoice_item2.setItem_id(item.getItem_id());
        invoice_item2.setQuantity(3);
        invoice_item2.setUnit_rate(new BigDecimal("2.99"));
        invoice_item2.setDiscount(new BigDecimal("0.99"));
        invoice_item2 = invoice_itemDao.addInvoice_Item(invoice_item2);

        // Create a list to hold all Invoice_Item's in the database
        List<Invoice_Item> invoice_itemList = invoice_itemDao.getAllInvoice_Item();

        // Test the getAllInvoice_Item() method
        assertEquals(2, invoice_itemList.size());
    }

    @Test
    public void updateInvoice_ItemTest() {
        // Create and add a new Customer to the database
        Customer customer = new Customer();
        customer.setFirstName("Firstname");
        customer.setLastName("Lastname");
        customer.setEmail("email@gmail.com");
        customer.setCompany("Company");
        customer.setPhone("999-999-9999");
        customer = customerDao.addCustomer(customer);

        // Create and add a new Invoice to the database
        Invoice invoice = new Invoice();
        invoice.setCustomer_id(customer.getCustomerId());
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
        invoice_item.setInvoice_id(invoice.getInvoice_id());
        invoice_item.setItem_id(item.getItem_id());
        invoice_item.setQuantity(2);
        invoice_item.setUnit_rate(new BigDecimal("1.99"));
        invoice_item.setDiscount(new BigDecimal("9.99"));
        invoice_item = invoice_itemDao.addInvoice_Item(invoice_item);

        // Update the values for invoice_item
        invoice_item.setQuantity(3);
        invoice_item.setUnit_rate(new BigDecimal("2.00"));
        invoice_item.setDiscount(new BigDecimal("8.99"));
        invoice_itemDao.updateInvoice_Item(invoice_item);

        // Create a copy of the invoice_item
        Invoice_Item invoice_itemCopy = invoice_itemDao.getInvoice_Item(invoice_item.getInvoice_item_id());

        // Test the updateInvoice_Item() method
        assertEquals(invoice_itemCopy, invoice_item);
    }

    @Test
    public void deleteInvoice_ItemTest() {
        // Create and add a new Customer to the database
        Customer customer = new Customer();
        customer.setFirstName("Firstname");
        customer.setLastName("Lastname");
        customer.setEmail("email@gmail.com");
        customer.setCompany("Company");
        customer.setPhone("999-999-9999");
        customer = customerDao.addCustomer(customer);

        // Create and add a new Invoice to the database
        Invoice invoice = new Invoice();
        invoice.setCustomer_id(customer.getCustomerId());
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
        invoice_item.setInvoice_id(invoice.getInvoice_id());
        invoice_item.setItem_id(item.getItem_id());
        invoice_item.setQuantity(2);
        invoice_item.setUnit_rate(new BigDecimal("1.99"));
        invoice_item.setDiscount(new BigDecimal("9.99"));
        invoice_item = invoice_itemDao.addInvoice_Item(invoice_item);

        // Delete invoice_item from the database
        invoice_itemDao.deleteInvoice_Item(invoice_item.getInvoice_item_id());

        // Make a copy of the deleted invoice_item
        Invoice_Item invoice_itemCopy = invoice_itemDao.getInvoice_Item(invoice_item.getInvoice_item_id());

        // Test deleteInvoice_Item() method
        assertNull(invoice_itemCopy);
    }
}
