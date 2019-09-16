package com.company.rentalstoregroup.serivce;
import com.company.rentalstoregroup.dao.*;
import com.company.rentalstoregroup.dto.Customer;
import com.company.rentalstoregroup.dto.Invoice;
import com.company.rentalstoregroup.dto.Invoice_Item;
import com.company.rentalstoregroup.dto.Item;
import com.company.rentalstoregroup.service.ServiceLayer;

import com.company.rentalstoregroup.viewmodel.InvoiceViewModel;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ServiceLayerTest {
    // Properties
    private ServiceLayer service;
    private CustomerDao customerDao;
    private ItemDao itemDao;
    private Invoice_ItemDao invoice_itemDao;
    private InvoiceDao invoiceDao;

    // ArgumentCaptor's
    private ArgumentCaptor<Item> itemArgumentCaptor = ArgumentCaptor.forClass(Item.class);
    private ArgumentCaptor<Invoice> invoiceArgumentCaptor = ArgumentCaptor.forClass(Invoice.class);
    private ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
    private ArgumentCaptor<Invoice_Item> invoice_itemArgumentCaptor = ArgumentCaptor.forClass(Invoice_Item.class);
    private ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);

    // SetUp()
    @Before
    public void setUp() throws Exception {
        setUpCustomerDaoMock();
        setUpInvoiceDaoMock();
        setUpInvoiceItemDaoMock();
        setUpItemDaoMock();

        service = new ServiceLayer(customerDao, itemDao, invoice_itemDao, invoiceDao);
    }

    // InvoiceView API Test (add, delete, getInvoiceByCustomer)
    @Test
    public void addInvoice() {
        // Add a Customer to the database
        Customer customer = new Customer();
        customer.setFirstName("Dominick");
        customer.setLastName("DeChristofaro");
        customer.setEmail("dominick.dechristofaro@gmail.com");
        customer.setCompany("Omni");
        customer.setPhone("999-999-9999");
        customer = service.saveCustomer(customer);

        // Verify it was added to the database
        Customer customerCopy = service.findCustomer(customer.getCustomerId());
        TestCase.assertEquals(customerCopy, customer);

        // Add an Item to the database
        Item item = new Item();
        item.setName("Drill");
        item.setDescription("Power Tool");
        item.setDaily_rate(new BigDecimal("24.99"));
        item = service.saveItem(item);

        // Verify it was added
        Item itemCopy = service.findItem(item.getItem_id());
        TestCase.assertEquals(itemCopy, item);

        // Add an InvoiceItem to the database
        Invoice_Item invoiceItem = new Invoice_Item();
        invoiceItem.setItem_id(item.getItem_id());
        invoiceItem.setQuantity(42);
        invoiceItem.setUnit_rate(new BigDecimal("4.99"));
        invoiceItem.setDiscount(new BigDecimal("0.99"));

        // Collect all the InvoiceItems into a list
        List<Invoice_Item> invoiceItemList = new ArrayList<>();
        invoiceItemList.add(invoiceItem);

        // Create an InvoiceViewModel
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setCustomer_id(customer.getCustomerId());
        invoiceViewModel.setOrder_date(LocalDate.of(2000,1,1));
        invoiceViewModel.setPickup_date(LocalDate.of(2000,2,2));
        invoiceViewModel.setReturn_date(LocalDate.of(2000,3,3));
        invoiceViewModel.setLate_fee(new BigDecimal("4.99"));
        invoiceViewModel.setInvoice_items(invoiceItemList);
        invoiceViewModel = service.createInvoiceViewModel(invoiceViewModel);

        // Test the addInvoice() method
        InvoiceViewModel invoiceViewModelCopy = service.getInvoiceViewModel(invoiceViewModel.getInvoice_id());
        TestCase.assertEquals(invoiceViewModelCopy, invoiceViewModel);

    }

    @Test
    public void deleteInvoice() {

    }

    @Test
    public void getInvoiceByCustomer() {

    }

    // Item API
    @Test
    public void addItem() {
        // Add a Item to the mock database using the Item API method
        Item item = new Item();
        item.setName("Drill");
        item.setDescription("Power Tool");
        item.setDaily_rate(new BigDecimal("24.99"));
        item = service.saveItem(item);

        // Copy the Item added to the mock database using the Item API method
        Item itemCopy = service.findItem(item.getItem_id());

        // Test the addItem() API method
        verify(itemDao, times(1)).getItem(item.getItem_id());
        TestCase.assertEquals(itemCopy, item);
    }

    @Test
    public void getItem() {
        // Add a Item to the mock database using the Item API method
        Item item = new Item();
        item.setName("Drill");
        item.setDescription("Power Tool");
        item.setDaily_rate(new BigDecimal("24.99"));
        item = service.saveItem(item);

        // Copy the Item added to the mock database using the Item API method
        Item itemCopy = service.findItem(item.getItem_id());

        // Test the getItem() API method
        verify(itemDao, times(1)).getItem(item.getItem_id());
        TestCase.assertEquals(itemCopy, item);
    }

    @Test
    public void getAllItem() {
        // Add an Item to the mock database using the Item API method (item1)
        Item item1 = new Item();
        item1.setName("Drill");
        item1.setDescription("Power Tool");
        item1.setDaily_rate(new BigDecimal("24.99"));
        item1 = service.saveItem(item1);

        // Add an Item to the mock database using the Item API method (item2)
        Item item2 = new Item();
        item2.setName("Screwdriver");
        item2.setDescription("Hand Tool");
        item2.setDaily_rate(new BigDecimal("4.99"));
        item2 = service.saveItem(item2);

        // Add all the Item's in the mock database to a list of Items using the Item API method
        List<Item> itemList = service.findAllItems();

        // Test the getAllItem() API method
        TestCase.assertEquals(2, itemList.size());
        TestCase.assertEquals(item1, itemList.get(0));
        TestCase.assertEquals(item2, itemList.get(1));
    }

    @Test
    public void updateItem() {
        // Add a Item to the mock database using the Item API method
        Item item = new Item();
        item.setName("Drill");
        item.setDescription("Power Tool");
        item.setDaily_rate(new BigDecimal("24.99"));
        item = service.saveItem(item);

        // Update the Item in the mock database using the API method
        item.setName("Screwdriver");
        item.setDescription("Hand Tool");
        item.setDaily_rate(new BigDecimal("4.99"));
        service.updateItem(item);

        // Test the updateItem() method
        verify(itemDao, times(1)).updateItem(itemArgumentCaptor.getValue());
        TestCase.assertEquals(item, itemArgumentCaptor.getValue());
    }

    @Test
    public void deleteItem() {
        // Add a Item to the mock database using the Item API method
        Item item = new Item();
        item.setName("Drill");
        item.setDescription("Power Tool");
        item.setDaily_rate(new BigDecimal("24.99"));
        item = service.saveItem(item);

        // Delete the Item from the database using the Item API method
        service.removeItem(item.getItem_id());

        // Test the deleteItem() method
        verify(itemDao, times(1)).deleteItem(integerArgumentCaptor.getValue());
        TestCase.assertEquals(item.getItem_id(), integerArgumentCaptor.getValue().intValue());
    }

    // Customer API
    @Test
    public void addCustomer() {
        // Add a customer to the mock database using the CustomerAPI
        Customer customer = new Customer();
        customer.setFirstName("Dominick");
        customer.setLastName("DeChristofaro");
        customer.setEmail("dominick.dechristofaro@gmail.com");
        customer.setCompany("Omni");
        customer.setPhone("999-999-9999");
        customer = service.saveCustomer(customer);

        // Copy the customer added to the mock database using the CustomerAPI
        Customer customerCopy = service.findCustomer(customer.getCustomerId());

        // Test addCustomer() API method
        verify(customerDao, times(1)).getCustomer(customer.getCustomerId());
        TestCase.assertEquals(customerCopy, customer);
    }

    @Test
    public void getCustomer() {
        // Add a customer to the mock database using the CustomerAPI
        Customer customer = new Customer();
        customer.setFirstName("Dominick");
        customer.setLastName("DeChristofaro");
        customer.setEmail("dominick.dechristofaro@gmail.com");
        customer.setCompany("Omni");
        customer.setPhone("999-999-9999");
        customer = service.saveCustomer(customer);

        // Copy the customer added to the mock database using the CustomerAPI
        Customer customerCopy = service.findCustomer(customer.getCustomerId());

        // Test getCustomer() API method
        verify(customerDao, times(1)).getCustomer(customer.getCustomerId());
        TestCase.assertEquals(customerCopy, customer);
    }

    @Test
    public void getAllCustomer() {
        // Add a Customer to the mock database (customer1)
        Customer customer1 = new Customer();
        customer1.setFirstName("Dominick");
        customer1.setLastName("DeChristofaro");
        customer1.setEmail("dominick.dechristofaro@gmail.com");
        customer1.setCompany("Omni");
        customer1.setPhone("999-999-9999");
        customer1 = service.saveCustomer(customer1);

        // Add a second Customer to the mock database (customer2)
        Customer customer2 = new Customer();
        customer2.setFirstName("Michael");
        customer2.setLastName("Stuckey");
        customer2.setEmail("michaelallenstuckey@gmail.com");
        customer2.setCompany("NuclearFuelServices");
        customer2.setPhone("222-222-2222");
        customer2 = service.saveCustomer(customer2);

        // Collect all the customers into a list using the Customer API
        List<Customer> customerList = service.findAllCustomers();

        // Test the getAllCustomer() API method
        TestCase.assertEquals(2,customerList.size());
        TestCase.assertEquals(customer1, customerList.get(0));
        TestCase.assertEquals(customer2, customerList.get(1));
    }

    @Test
    public void updateCustomer() {
        // Add a Customer to the database using the CustomerAPI
        Customer customer = new Customer();
        customer.setFirstName("Dominick");
        customer.setLastName("DeChristofaro");
        customer.setEmail("dominick.dechristofaro@gmail.com");
        customer.setCompany("Omni");
        customer.setPhone("999-999-9999");
        customer = service.saveCustomer(customer);

        // Update the Customer in the database
        customer.setFirstName("Michael");
        customer.setLastName("Stuckey");
        customer.setEmail("michaelallenstuckey@gmail.com");
        customer.setCompany("NuclearFuelServices");
        customer.setPhone("222-222-2222");
        service.updateCustomer(customer);

        // Test the updateCustomer() method
        verify(customerDao, times(1)).updateCustomer(customerArgumentCaptor.getValue());
        assertEquals(customer, customerArgumentCaptor.getValue());
    }

    @Test
    public void deleteCustomer() {
        // Add a Customer to the database using the CustomerAPI
        Customer customer = new Customer();
        customer.setFirstName("Dominick");
        customer.setLastName("DeChristofaro");
        customer.setEmail("dominick.dechristofaro@gmail.com");
        customer.setCompany("Omni");
        customer.setPhone("999-999-9999");
        customer = service.saveCustomer(customer);

        // Delete the Customer from the database
        service.removeCustomer(customer.getCustomerId());

        // Test the deleteCustomer() method
        verify(customerDao, times(1)).deleteCustomer(integerArgumentCaptor.getValue());
        assertEquals(customer.getCustomerId(), integerArgumentCaptor.getValue().intValue());
    }

    // Customer DAO Mock Input/Response
    private void setUpCustomerDaoMock() {
        // Set up the CustomerDao Mock
        customerDao = mock(CustomerDaoJdbcTemplateImpl.class);

        // customerDao Mock Input (customerInput1)
        Customer customerInput1 = new Customer();
        customerInput1.setFirstName("Dominick");
        customerInput1.setLastName("DeChristofaro");
        customerInput1.setEmail("dominick.dechristofaro@gmail.com");
        customerInput1.setCompany("Omni");
        customerInput1.setPhone("999-999-9999");

        // customerDao Mock Response (customerResponse1)
        Customer customerResponse1 = new Customer();
        customerResponse1.setCustomerId(1);
        customerResponse1.setFirstName("Dominick");
        customerResponse1.setLastName("DeChristofaro");
        customerResponse1.setEmail("dominick.dechristofaro@gmail.com");
        customerResponse1.setCompany("Omni");
        customerResponse1.setPhone("999-999-9999");

        // customerDao Mock Input (customerInput2)
        Customer customerInput2 = new Customer();
        customerInput2.setFirstName("Michael");
        customerInput2.setLastName("Stuckey");
        customerInput2.setEmail("michaelallenstuckey@gmail.com");
        customerInput2.setCompany("NuclearFuelServices");
        customerInput2.setPhone("222-222-2222");

        // customerDao Mock Response (customerResponse2)
        Customer customerResponse2 = new Customer();
        customerResponse2.setCustomerId(2);
        customerResponse2.setFirstName("Michael");
        customerResponse2.setLastName("Stuckey");
        customerResponse2.setEmail("michaelallenstuckey@gmail.com");
        customerResponse2.setCompany("NuclearFuelServices");
        customerResponse2.setPhone("222-222-2222");

        // Add customerResponse to a list of Customer objects
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customerResponse1);
        customerList.add(customerResponse2);

        // Mocking addCustomer()
        doReturn(customerResponse1).when(customerDao).addCustomer(customerInput1);
        doReturn(customerResponse2).when(customerDao).addCustomer(customerInput2);

        // Mocking getCustomer()
        doReturn(customerResponse1).when(customerDao).getCustomer(customerResponse1.getCustomerId());
        doReturn(customerResponse1).when(customerDao).getCustomer(customerResponse1.getCustomerId());

        // Mocking getAllCustomer()
        doReturn(customerList).when(customerDao).findAllCustomer();

        // Mocking updateCustomer()
        doNothing().when(customerDao).updateCustomer(customerArgumentCaptor.capture());

        // Mocking deleteCustomer()
        doNothing().when(customerDao).deleteCustomer(integerArgumentCaptor.capture());
    }

    // Invoice DAO Mock Input/Response
    private void setUpInvoiceDaoMock() {
        // Set up the InvoiceDao Mock
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);

        // invoiceDao Mock Input (invoiceInput1)
        Invoice invoiceInput1 = new Invoice();
        invoiceInput1.setCustomer_id(1);
        invoiceInput1.setOrder_date(LocalDate.of(2000,1,1));
        invoiceInput1.setPickup_date(LocalDate.of(2000,2,2));
        invoiceInput1.setReturn_date(LocalDate.of(2000,3,3));
        invoiceInput1.setLate_fee(new BigDecimal("4.99"));

        // invoiceDao Mock Response (invoiceResponse1)
        Invoice invoiceResponse1 = new Invoice();
        invoiceResponse1.setInvoice_id(1);
        invoiceResponse1.setCustomer_id(1);
        invoiceResponse1.setOrder_date(LocalDate.of(2000,1,1));
        invoiceResponse1.setPickup_date(LocalDate.of(2000,2,2));
        invoiceResponse1.setReturn_date(LocalDate.of(2000,3,3));
        invoiceResponse1.setLate_fee(new BigDecimal("4.99"));

        // invoiceDao Mock Input (invoiceInput2)
        Invoice invoiceInput2 = new Invoice();
        invoiceInput2.setCustomer_id(2);
        invoiceInput2.setOrder_date(LocalDate.of(1999,4,4));
        invoiceInput2.setPickup_date(LocalDate.of(1999,5,5));
        invoiceInput2.setReturn_date(LocalDate.of(1999,6,6));
        invoiceInput2.setLate_fee(new BigDecimal("7.24"));

        // invoiceDao Mock Response (invoiceResponse2)
        Invoice invoiceResponse2 = new Invoice();
        invoiceResponse2.setInvoice_id(2);
        invoiceResponse2.setCustomer_id(2);
        invoiceResponse2.setOrder_date(LocalDate.of(1999,4,4));
        invoiceResponse2.setPickup_date(LocalDate.of(1999,5,5));
        invoiceResponse2.setReturn_date(LocalDate.of(1999,6,6));
        invoiceResponse2.setLate_fee(new BigDecimal("7.24"));

        // invoiceDao Mock Input (invoiceInput3)
        Invoice invoiceInput3 = new Invoice();
        invoiceInput3.setCustomer_id(2);
        invoiceInput3.setOrder_date(LocalDate.of(2010,7,7));
        invoiceInput3.setPickup_date(LocalDate.of(2010,8,8));
        invoiceInput3.setReturn_date(LocalDate.of(2010,9,9));
        invoiceInput3.setLate_fee(new BigDecimal("11.37"));

        // invoiceDao Mock Response (invoiceResponse3)
        Invoice invoiceResponse3 = new Invoice();
        invoiceResponse3.setInvoice_id(3);
        invoiceResponse3.setCustomer_id(2);
        invoiceResponse3.setOrder_date(LocalDate.of(2010,7,7));
        invoiceResponse3.setPickup_date(LocalDate.of(2010,8,8));
        invoiceResponse3.setReturn_date(LocalDate.of(2010,9,9));
        invoiceResponse3.setLate_fee(new BigDecimal("11.37"));

        // Add all invoiceResponses to a list of Invoice objects
        List<Invoice> allInvoiceList = new ArrayList<>();
        allInvoiceList.add(invoiceResponse1);
        allInvoiceList.add(invoiceResponse2);
        allInvoiceList.add(invoiceResponse3);

        // All all invoiceResponses with a CustomerID of 2 to a list of Invoice objects
        List<Invoice> customer2InvoiceList = new ArrayList<>();
        customer2InvoiceList.add(invoiceResponse2);
        customer2InvoiceList.add(invoiceResponse3);

        // Mocking addInvoice()
        doReturn(invoiceResponse1).when(invoiceDao).addInvoice(invoiceInput1);
        doReturn(invoiceResponse2).when(invoiceDao).addInvoice(invoiceInput2);
        doReturn(invoiceResponse3).when(invoiceDao).addInvoice(invoiceInput3);

        // Mocking getInvoice()
        doReturn(invoiceResponse1).when(invoiceDao).getInvoice(invoiceResponse1.getInvoice_id());
        doReturn(invoiceResponse2).when(invoiceDao).getInvoice(invoiceResponse2.getInvoice_id());
        doReturn(invoiceResponse3).when(invoiceDao).getInvoice(invoiceResponse3.getInvoice_id());

        // Mocking getAllInvoice()
        doReturn(allInvoiceList).when(invoiceDao).getAllInvoices();

        // Mocking getInvoiceByCustomer()
        doReturn(customer2InvoiceList).when(invoiceDao).getInvoicesByCustomer(invoiceResponse2.getCustomer_id());

        // Mocking updateInvoice()
        doNothing().when(invoiceDao).updateInvoice(invoiceArgumentCaptor.capture());

        // Mocking deleteInvoice()
        doNothing().when(invoiceDao).deleteInvoice(integerArgumentCaptor.capture());
    }

    // Item DAO Mock Input/Response
    private void setUpItemDaoMock() {
        // Set up the ItemDao Mock
        itemDao = mock(ItemDaoJdbcTemplateImpl.class);

        // itemDao Mock Input (itemInput1)
        Item itemInput1 = new Item();
        itemInput1.setName("Drill");
        itemInput1.setDescription("Power Tool");
        itemInput1.setDaily_rate(new BigDecimal("24.99"));

        // itemDao Mock Response (itemResponse1)
        Item itemResponse1 = new Item();
        itemResponse1.setItem_id(1);
        itemResponse1.setName("Drill");
        itemResponse1.setDescription("Power Tool");
        itemResponse1.setDaily_rate(new BigDecimal("24.99"));

        // itemDao Mock Input (itemInput2)
        Item itemInput2 = new Item();
        itemInput2.setName("Screwdriver");
        itemInput2.setDescription("Hand Tool");
        itemInput2.setDaily_rate(new BigDecimal("4.99"));

        // itemDao Mock Response (itemResponse2)
        Item itemResponse2 = new Item();
        itemResponse2.setItem_id(2);
        itemResponse2.setName("Screwdriver");
        itemResponse2.setDescription("Hand Tool");
        itemResponse2.setDaily_rate(new BigDecimal("4.99"));

        // Add itemResponse's to a List of Item objects
        List<Item> itemList = new ArrayList<>();
        itemList.add(itemResponse1);
        itemList.add(itemResponse2);

        // Mocking addItem() DAO method
        doReturn(itemResponse1).when(itemDao).addItem(itemInput1);
        doReturn(itemResponse2).when(itemDao).addItem(itemInput2);

        // Mocking getItem() DAO method
        doReturn(itemResponse1).when(itemDao).getItem(itemResponse1.getItem_id());
        doReturn(itemResponse2).when(itemDao).getItem(itemResponse2.getItem_id());

        // Mocking getAllItem() DAO method
        doReturn(itemList).when(itemDao).getAllItems();

        // Mocking updateItem() DAO method
        doNothing().when(itemDao).updateItem(itemArgumentCaptor.capture());

        // Mocking deleteItem() DAO method
        doNothing().when(itemDao).deleteItem(integerArgumentCaptor.capture());
    }

    // InvoiceItem DAO Mock Input/Response
    public void setUpInvoiceItemDaoMock() {
        // Set up teh InvoiceItemDao Mock
        invoice_itemDao = mock(Invoice_ItemDaoJdbcTemplateImpl.class);

        // invoiceItemDao Mock Input (invoiceItemInput1)
        Invoice_Item invoiceItemInput1 = new Invoice_Item();
        invoiceItemInput1.setInvoice_id(1);
        invoiceItemInput1.setItem_id(1);
        invoiceItemInput1.setQuantity(42);
        invoiceItemInput1.setUnit_rate(new BigDecimal("4.99"));
        invoiceItemInput1.setDiscount(new BigDecimal("0.99"));

        // invoiceItemDao Mock Response (invoiceItemResponse1)
        Invoice_Item invoiceItemResponse1 = new Invoice_Item();
        invoiceItemResponse1.setInvoice_item_id(1);
        invoiceItemResponse1.setInvoice_id(1);
        invoiceItemResponse1.setItem_id(1);
        invoiceItemResponse1.setQuantity(42);
        invoiceItemResponse1.setUnit_rate(new BigDecimal("4.99"));
        invoiceItemResponse1.setDiscount(new BigDecimal("0.99"));

        // invoiceItemDao Mock Input (invoiceItemInput2)
        Invoice_Item invoiceItemInput2 = new Invoice_Item();
        invoiceItemInput2.setInvoice_id(2);
        invoiceItemInput2.setItem_id(2);
        invoiceItemInput2.setQuantity(77);
        invoiceItemInput2.setUnit_rate(new BigDecimal("7.42"));
        invoiceItemInput2.setDiscount(new BigDecimal("1.31"));

        // invoiceItemDao Mock Response (invoiceItemResponse2)
        Invoice_Item invoiceItemResponse2 = new Invoice_Item();
        invoiceItemResponse2.setInvoice_item_id(2);
        invoiceItemResponse2.setInvoice_id(2);
        invoiceItemResponse2.setItem_id(2);
        invoiceItemResponse2.setQuantity(77);
        invoiceItemResponse2.setUnit_rate(new BigDecimal("7.42"));
        invoiceItemResponse2.setDiscount(new BigDecimal("1.31"));

        // invoiceItemDao Mock Input (invoiceItemInput3)
        Invoice_Item invoiceItemInput3 = new Invoice_Item();
        invoiceItemInput3.setInvoice_id(2);
        invoiceItemInput3.setItem_id(3);
        invoiceItemInput3.setQuantity(99);
        invoiceItemInput3.setUnit_rate(new BigDecimal("7.42"));
        invoiceItemInput3.setDiscount(new BigDecimal("1.31"));

        // invoiceItemDao Mock Response (invoiceItemResponse3)
        Invoice_Item invoiceItemResponse3 = new Invoice_Item();
        invoiceItemResponse3.setInvoice_item_id(3);
        invoiceItemResponse3.setInvoice_id(2);
        invoiceItemResponse3.setItem_id(3);
        invoiceItemResponse3.setQuantity(99);
        invoiceItemResponse3.setUnit_rate(new BigDecimal("11.99"));
        invoiceItemResponse3.setDiscount(new BigDecimal("55.23"));

        // Add invoiceItemResponses to a List of InvoiceItem objects
        List<Invoice_Item> allInvoiceItemList = new ArrayList<>();
        allInvoiceItemList.add(invoiceItemResponse1);
        allInvoiceItemList.add(invoiceItemResponse2);
        allInvoiceItemList.add(invoiceItemResponse3);

        // Add invoiceItemresponse1 to a List of InvoiceItem objects
        List<Invoice_Item> invoiceId1List = new ArrayList<>();
        invoiceId1List.add(invoiceItemResponse1);

        // Add invoiceItemResponses with InvoiceId of 2 to a list of InvoiceItem objects
        List<Invoice_Item> invoiceId2InvoiceItemList = new ArrayList<>();
        invoiceId2InvoiceItemList.add(invoiceItemResponse2);
        invoiceId2InvoiceItemList.add(invoiceItemResponse3);

        // Add invoiceItemResponses with ItemId of 3 to a list of InvoiceItem objects
        List<Invoice_Item> itemId3InvoiceItemList = new ArrayList<>();
        itemId3InvoiceItemList.add(invoiceItemResponse1);
        itemId3InvoiceItemList.add(invoiceItemResponse3);

        // Mocking addInvoiceItem()
        doReturn(invoiceItemResponse1).when(invoice_itemDao).addInvoice_Item(invoiceItemInput1);
        doReturn(invoiceItemResponse2).when(invoice_itemDao).addInvoice_Item(invoiceItemInput2);
        doReturn(invoiceItemResponse3).when(invoice_itemDao).addInvoice_Item(invoiceItemInput3);

        // Mocking getInvoiceItem()
        doReturn(invoiceItemResponse1).when(invoice_itemDao).getInvoice_Item(invoiceItemResponse1.getInvoice_item_id());
        doReturn(invoiceItemResponse2).when(invoice_itemDao).getInvoice_Item(invoiceItemResponse2.getInvoice_item_id());
        doReturn(invoiceItemResponse3).when(invoice_itemDao).getInvoice_Item(invoiceItemResponse3.getInvoice_item_id());

        // Mocking getAllInvoiceItem()
        doReturn(allInvoiceItemList).when(invoice_itemDao).getAllInvoice_Item();

        // Mocking getInvoiceItemByInvoice()
        doReturn(invoiceId1List).when(invoice_itemDao).getInvoice_ItemByInvoice(invoiceItemResponse1.getInvoice_id());
        doReturn(invoiceId2InvoiceItemList).when(invoice_itemDao).getInvoice_ItemByInvoice(invoiceItemResponse2.getInvoice_id());

        // Mocking updateInvoiceItem()
        doNothing().when(invoice_itemDao).updateInvoice_Item(invoice_itemArgumentCaptor.capture());

        // Mocking deleteInvoiceItem()
        doNothing().when(invoice_itemDao).deleteInvoice_Item(integerArgumentCaptor.capture());
    }
}