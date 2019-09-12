package com.company.rentalstoregroup.dao;

import com.company.rentalstoregroup.dto.Customer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerDaoJdbcTemplateImpl implements CustomerDao {
    //Prepared Statements
    private static final String INSERT_CUSTOMER_SQL = "insert into customer(first_name,last_name,email,company,phone) values(?,?,?,?,?)";
    private static final String SELECT_ALL_CUSTOMER_SQL = "select * from customer";
    private static final String SELECT_CUSTOMER_BY_ID_SQL = "select * from customer where customer_id=?";
    private static final String DELETE_CUSTOMER_SQL = "delete from customer where customer_id=?";
    private static final String UPDATE_CUSTOMER_SQL = "update customer set first_name=?,last_name=?,email=?,company=?,phone=? where customer_id=? ";

    private JdbcTemplate jdbcTemplate;

    public CustomerDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    //Method Implementation


    @Override
    public List<Customer> findAllCustomer() {
        return jdbcTemplate.query(SELECT_ALL_CUSTOMER_SQL,this::mapRowToCustomer);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        jdbcTemplate.update(INSERT_CUSTOMER_SQL,customer.getFirstName(),customer.getLastName(),customer.getEmail(),customer.getCompany(),
                customer.getPhone());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",Integer.class);
        customer.setCustomerId(customer.getCustomerId());
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) {
        jdbcTemplate.update(UPDATE_CUSTOMER_SQL,customer.getFirstName(),customer.getLastName(),customer.getEmail(),customer.getCompany(),
                customer.getPhone(),customer.getCustomerId());
    }

    @Override
    public void deleteCustomer(int customerId) {
        jdbcTemplate.update(DELETE_CUSTOMER_SQL,customerId);
    }

    @Override
    public Customer getCustomer(int customerId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_CUSTOMER_BY_ID_SQL, this::mapRowToCustomer, customerId);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }


    //row mapper
private Customer mapRowToCustomer(ResultSet rs, int rowNum) throws SQLException{
    Customer customer = new Customer();
    customer.setCustomerId(rs.getInt("customer_id"));
    customer.setFirstName(rs.getString("first_name"));
    customer.setLastName(rs.getString("last_name"));
    customer.setEmail(rs.getString("email"));
    customer.setCompany(rs.getString("company"));
    customer.setPhone(rs.getString("phone"));

    return customer;
}


}
