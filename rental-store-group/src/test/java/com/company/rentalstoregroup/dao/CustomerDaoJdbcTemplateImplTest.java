package com.company.rentalstoregroup.dao;

import com.company.rentalstoregroup.dto.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
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
        customer.setPhone("111-111-1111");
        customer.setLastName("LastNameOne");
        customer.setFirstName("FirstNameOne");
        customer.setEmail("emailOne@gmail.com");
        customer.setCompany("companyOne");
        customerDao.addCustomer(customer);
    }

    @Test
    public void addCustomer() {


    }

    @Test
    public void updateCustomer() {
    }

    @Test
    public void deleteCustomer() {
    }

    @Test
    public void getCustomer() {
    }
}