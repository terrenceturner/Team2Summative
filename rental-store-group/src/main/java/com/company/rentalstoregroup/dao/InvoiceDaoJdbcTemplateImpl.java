package com.company.rentalstoregroup.dao;

import com.company.rentalstoregroup.dto.Invoice;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao {
    //Prepared Statements

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

    //row mapper
}
