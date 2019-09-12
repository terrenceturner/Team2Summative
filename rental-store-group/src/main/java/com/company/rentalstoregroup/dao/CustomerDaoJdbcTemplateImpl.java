package com.company.rentalstoregroup.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoJdbcTemplateImpl {
    //Prepared Statements
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_CUSTOMER_SQL = "insert into customer(first_name,last_name,email,company,phone) values(?,?,?,?,?)";
    private static final String SELECT_ALL_CUSTOMER_SQL = "select * from customer";
    private static final String SELECT_CUSTOMER_BY_ID = "select * from customer where customer_id=?";
    private static final String DELETE_CUSTOMER = "delete from customer where customer_id=?";
    private static final String UPDATE_CUSTOMER = "update customer set first_name=?,last_name=?,email=?,company=?,phone=? where customer_id=? ";

    //Method Implementation

    //row mapper

}
