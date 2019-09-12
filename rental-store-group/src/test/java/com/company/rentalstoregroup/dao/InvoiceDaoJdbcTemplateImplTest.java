package com.company.rentalstoregroup.dao;

import com.company.rentalstoregroup.dto.Customer;
import com.company.rentalstoregroup.dto.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoJdbcTemplateImplTest {

    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    CustomerDao customerDao;

    @Before
    public void setUp() throws Exception {
        List<Invoice> invoices = invoiceDao.getAllInvoices();
        for (Invoice i : invoices){
            invoiceDao.deleteInvoice(i.getInvoice_id());
        }
    }

    @Test
    public void addInvoice() {
        Customer customer = new Customer();
        customer.setCompany("A");
        customer.setFirstName("Aa");
        customer.setLastName("Bb");
        customer.setEmail("AaBb@gmail.com");
        customer.setPhone("444-555-9999");
        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice(
                12,
                LocalDate.of(2000,01,01),
                LocalDate.of(2000,01,01),
                LocalDate.of(2000,01,01),
                new BigDecimal("19.99"));
        invoice = invoiceDao.addInvoice(invoice);

        Invoice checkInvoice = invoiceDao.getInvoice(invoice.getInvoice_id());

        assertEquals(invoice, checkInvoice);
    }

    @Test
    public void getAllInvoices() {
        Customer customer = new Customer();
        customer.setCompany("A");
        customer.setFirstName("Aa");
        customer.setLastName("Bb");
        customer.setEmail("AaBb@gmail.com");
        customer.setPhone("444-555-9999");
        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice(
                customer.getCustomerId(),
                LocalDate.of(2000,01,01),
                LocalDate.of(2000,01,02),
                LocalDate.of(2000,01,03),
                new BigDecimal("19.99"));
        invoice = invoiceDao.addInvoice(invoice);

        Invoice second = new Invoice(
                customer.getCustomerId(),
                LocalDate.of(2010,01,01),
                LocalDate.of(2010,02,01),
                LocalDate.of(2010,03,01),
                new BigDecimal("19.99"));
        second = invoiceDao.addInvoice(second);

        List<Invoice> localList = new ArrayList<>();
        localList.add(invoice);
        localList.add(second);

        List<Invoice> resList = invoiceDao.getAllInvoices();

        assertEquals(localList, resList);
    }

    @Test
    public void updateInvoice() {
        Customer customer = new Customer();
        customer.setCompany("A");
        customer.setFirstName("Aa");
        customer.setLastName("Bb");
        customer.setEmail("AaBb@gmail.com");
        customer.setPhone("444-555-9999");
        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice(
                customer.getCustomerId(),
                LocalDate.of(2000,01,01),
                LocalDate.of(2000,01,01),
                LocalDate.of(2000,01,01),
                new BigDecimal("19.99"));
        invoice = invoiceDao.addInvoice(invoice);

        invoice.setLate_fee(new BigDecimal("29.99"));

        invoiceDao.updateInvoice(invoice);

        Invoice checkInvoice = invoiceDao.getInvoice(invoice.getInvoice_id());

        assertEquals(invoice, checkInvoice);

    }

    @Test
    public void deleteInvoice() {
        Customer customer = new Customer();
        customer.setCompany("A");
        customer.setFirstName("Aa");
        customer.setLastName("Bb");
        customer.setEmail("AaBb@gmail.com");
        customer.setPhone("444-555-9999");
        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice(
                customer.getCustomerId(),
                LocalDate.of(2000,01,01),
                LocalDate.of(2000,01,01),
                LocalDate.of(2000,01,01),
                new BigDecimal("19.99"));
        invoice = invoiceDao.addInvoice(invoice);


        invoiceDao.deleteInvoice(invoice.getInvoice_id());

        List<Invoice> checkInv = invoiceDao.getAllInvoices();

        assertEquals(0, checkInv.size());
    }
}