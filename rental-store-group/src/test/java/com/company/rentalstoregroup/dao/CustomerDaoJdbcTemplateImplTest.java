package com.company.rentalstoregroup.dao;

import com.company.rentalstoregroup.dto.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoJdbcTemplateImplTest {


    @Autowired
    CustomerDao customerDao;

    @Before
    public void setUp() throws Exception {
        List<Customer> customerList = customerDao.findAllCustomer();
        customerList.stream()
                .forEach(customer -> customerDao.deleteCustomer(customer.getCustomerId()));
    }

    @Test
    public void findAllCustomer() {
        Customer customer = new Customer();
        customer.setPhone("7896787868");
        customer.setLastName("Malfoy");
        customer.setFirstName("Draco");
        customer.setEmail("dmal@gmail.com");
        customer.setCompany("BigCoCo");
        customerDao.addCustomer(customer);

        customer = new Customer();
        customer.setPhone("5678934432");
        customer.setLastName("Hastings");
        customer.setFirstName("Greg");
        customer.setEmail("ghast@gmail.com");
        customer.setCompany("BigCo");
        customerDao.addCustomer(customer);

        List<Customer> customerList = customerDao.findAllCustomer();
        assertEquals(customerList.size(), 2);

    }

    @Test
    public void addCustomer() {
        Customer customer = new Customer();
        customer.setPhone("7896787868");
        customer.setLastName("Malfoy");
        customer.setFirstName("Draco");
        customer.setEmail("dmal@gmail.com");
        customer.setCompany("BigCoCo");
        customerDao.addCustomer(customer);

        Customer customer1 = customerDao.getCustomer(customer.getCustomerId());
        assertEquals(customer1, customer);


    }

    @Test
    public void updateCustomer() {
        Customer customer = new Customer();
        customer.setPhone("7896787868");
        customer.setLastName("Malfoy");
        customer.setFirstName("Draco");
        customer.setEmail("dmal@gmail.com");
        customer.setCompany("BigCoCo");
        customerDao.addCustomer(customer);

        customer.setPhone("5678934432");
        customer.setLastName("Hastings");
        customer.setFirstName("Greg");
        customer.setEmail("ghast@gmail.com");
        customer.setCompany("BigCo");
        customerDao.updateCustomer(customer);

        Customer updatedCustomer = customerDao.getCustomer(customer.getCustomerId());
        assertEquals(updatedCustomer, customer);
    }

    @Test
    public void deleteCustomer() {
        Customer customer = new Customer();
        customer.setPhone("7896787868");
        customer.setLastName("Malfoy");
        customer.setFirstName("Draco");
        customer.setEmail("dmal@gmail.com");
        customer.setCompany("BigCoCo");
        customerDao.addCustomer(customer);

        Customer customer1 = customerDao.getCustomer(customer.getCustomerId());
        assertEquals(customer1, customer);

        customerDao.deleteCustomer(customer.getCustomerId());
        customer1 = customerDao.getCustomer(customer.getCustomerId());
        assertNull(customer1);
    }

    @Test
    public void getCustomer() {
        Customer customer = new Customer();
        customer.setPhone("7896787868");
        customer.setLastName("Malfoy");
        customer.setFirstName("Draco");
        customer.setEmail("dmal@gmail.com");
        customer.setCompany("BigCoCo");
        customer = customerDao.addCustomer(customer);

        Customer customer1 = customerDao.getCustomer(customer.getCustomerId());
        assertEquals(customer1, customer);
    }
}