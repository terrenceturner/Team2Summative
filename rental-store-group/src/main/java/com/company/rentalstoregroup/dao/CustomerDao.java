package com.company.rentalstoregroup.dao;

import com.company.rentalstoregroup.dto.Customer;

import java.util.List;

public interface CustomerDao {

    //DAO methods (no implementation code, i.e. Class addClass (Class class);)



    /**
     * finds all customers
     * @return
     */
    List<Customer> findAllCustomer();

    /**
     * adds a customer object
     * @param customer
     * @return
     */
    Customer addCustomer(Customer customer);

    /**
     * updates customer
     * @param customer
     */
    void updateCustomer(Customer customer);

    /**
     * deletes customer by id
     * @param customerId
     */
    void deleteCustomer(int customerId);

    /**
     * finds a customer by id
     * @param customerId
     * @return
     */
    Customer getCustomer (int customerId);
}
