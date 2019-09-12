package com.company.rentalstoregroup.dao;

import com.company.rentalstoregroup.dto.Customer;
import com.company.rentalstoregroup.dto.Invoice;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao {
    //Prepared Statements
    private static final String INSERT_INVOICE_SQL = "INSERT INTO invoice (invoice_id, customer_id, order_date, pickup_date, return_date, late_fee values (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_INVOICE_SQL = "SELECT * FROM invoice WHERE invoice_id =  ?";
    private static final String SELECT_ALL_INVOICES_SQL = "SELECT * FROM invoices";
    private static final String UPDATE_INVOICE_SQL = "UPDATE invoice SET customer_id = ?, order_date = ?, pickup_date = ?, return_date = ?, late_fee = ? WHERE invoice_id = ?";
    private static final String DELETE_INVOICE_SQL = "DELETE FROM invoice WHERE invoice_id = ?";
    private static final String GET_INVOICE_BY_CUSTOMER_SQL = "GET * FROM invoice WHERE customer_id = ?";


    //Method Implementation
    private JdbcTemplate jdbcTemplate;

    @Override
    public Invoice addInvoice(Invoice invoice) {
        return null;
    }

    @Override
    public Invoice getInvoice(int id) {
        return null;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return null;
    }

    @Override
    public void updateInvoice(Invoice invoice) {

    }

    @Override
    public void deleteInvoice(int id) {

    }

    @Override
    public List<Invoice> getInvoicesByCustomer(Customer customer) {
        return null;
    }

    //row mapper
}
