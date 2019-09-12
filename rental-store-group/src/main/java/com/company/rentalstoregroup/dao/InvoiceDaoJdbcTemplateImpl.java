package com.company.rentalstoregroup.dao;
import com.company.rentalstoregroup.dto.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao {
    //Prepared Statements
    private static final String INSERT_INVOICE_SQL = "INSERT INTO invoice (customer_id, order_date, pickup_date, return_date, late_fee) VALUE (?, ?, ?, ?, ?)";
    private static final String SELECT_INVOICE_SQL = "SELECT * FROM invoice WHERE invoice_id =  ?";
    private static final String SELECT_ALL_INVOICES_SQL = "SELECT * FROM invoice";
    private static final String UPDATE_INVOICE_SQL = "UPDATE invoice SET customer_id = ?, order_date = ?, pickup_date = ?, return_date = ?, late_fee = ? WHERE invoice_id = ?";
    private static final String DELETE_INVOICE_SQL = "DELETE FROM invoice WHERE invoice_id = ?";
    private static final String GET_INVOICE_BY_CUSTOMER_SQL = "GET * FROM invoice WHERE customer_id = ?";


    //Method Implementation
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public InvoiceDaoJdbcTemplateImpl (JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Invoice addInvoice(Invoice invoice) {
        jdbcTemplate.update(INSERT_INVOICE_SQL, invoice.getCustomer_id(), invoice.getOrder_date(), invoice.getPickup_date(), invoice.getReturn_date(), invoice.getLate_fee());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        invoice.setInvoice_id(id);
        return invoice;
    }

    @Override
    public Invoice getInvoice(int id) {

        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_SQL, this::mapRowToInvoice, id);
        } catch (EmptyResultDataAccessException e) {
            // if there is no match for this invoice id, return null
            return null;
        }
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return jdbcTemplate.query(SELECT_ALL_INVOICES_SQL, this::mapRowToInvoice);
    }

    @Override
    @Transactional
    public void updateInvoice(Invoice invoice) {
        jdbcTemplate.update(UPDATE_INVOICE_SQL, invoice.getCustomer_id(),invoice.getOrder_date(),invoice.getPickup_date(), invoice.getReturn_date(), invoice.getLate_fee(), invoice.getInvoice_id());
    }

    @Override
    @Transactional
    public void deleteInvoice(int id) {
        jdbcTemplate.update(DELETE_INVOICE_SQL, id);
    }

    @Override
    public List<Invoice> getInvoicesByCustomer(int customerId) {
        return jdbcTemplate.query(GET_INVOICE_BY_CUSTOMER_SQL, this::mapRowToInvoice, customerId);
    }

    //row mapper

    private Invoice mapRowToInvoice(ResultSet rs, int rowNum) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setInvoice_id(rs.getInt("invoice_id"));
        invoice.setCustomer_id(rs.getInt("customer_id"));
        invoice.setOrder_date(rs.getDate("order_date").toLocalDate());
        invoice.setPickup_date(rs.getDate("pickup_date").toLocalDate());
        invoice.setReturn_date(rs.getDate("return_date").toLocalDate());
        invoice.setLate_fee(rs.getBigDecimal("late_fee"));
        return invoice;
    }
}
