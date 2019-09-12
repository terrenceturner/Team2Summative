package com.company.rentalstoregroup.dao;
import com.company.rentalstoregroup.dto.Invoice_Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Invoice_ItemDaoJdbcTemplateImpl implements Invoice_ItemDao {
    // SQL Prepared Statements (Constants)
    private static final String INSERT_INVOICE_ITEM_SQL =
            "INSERT INTO Invoice_Item (invoice_id, quantity, unit_rate, discount) VALUES (?,?,?,?)";
    private static final String SELECT_INVOICE_ITEM_SQL =
            "SELECT * FROM Invoice_Item WHERE invoice_item_id = ?";
    private static final String SELECT_ALL_INVOICE_ITEM_SQL =
            "SELECT * FROM Invoice_Item";
    private static final String UPDATE_INVOICE_ITEM_SQL =
            "UPDATE Invoice_Item SET invoice_id = ?, quantity = ?, unit_rate = ?, discount = ? WHERE invoice_item_id = ?";
    private static final String DELETE_INVOICE_ITEM_SQL =
            "DELETE FROM Invoice_Item WHERE invoice_item_id = ?";

    // Properties
    private JdbcTemplate jdbcTemplate;

    // Constructors
    @Autowired
    public Invoice_ItemDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Method Implementation
    @Override
    @Transactional
    public Invoice_Item addInvoice_Item(Invoice_Item invoice_item) {
        jdbcTemplate.update(INSERT_INVOICE_ITEM_SQL,
                invoice_item.getInvoice_id(),
                invoice_item.getQuantity(),
                invoice_item.getUnit_rate(),
                invoice_item.getDiscount());
        int invoice_item_id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        invoice_item.setInvoice_item_id(invoice_item_id);
        return invoice_item;
    }

    @Override
    public Invoice_Item getInvoice_Item(int invoice_item_id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_ITEM_SQL, this::mapRowToInvoice_Item, invoice_item_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Invoice_Item> getAllInvoice_Item() {
        return jdbcTemplate.query(SELECT_ALL_INVOICE_ITEM_SQL, this::mapRowToInvoice_Item);
    }

    @Override
    @Transactional
    public void updateInvoice_Item(Invoice_Item invoice_item) {
        jdbcTemplate.update(UPDATE_INVOICE_ITEM_SQL,
                invoice_item.getInvoice_id(),
                invoice_item.getQuantity(),
                invoice_item.getUnit_rate(),
                invoice_item.getDiscount(),
                invoice_item.getInvoice_item_id());
    }

    @Override
    @Transactional
    public void deleteInvoice_Item(int invoice_item_id) {
        jdbcTemplate.update(DELETE_INVOICE_ITEM_SQL, invoice_item_id);
    }

    // Object Relational Mapper for Invoice_Item
    private Invoice_Item mapRowToInvoice_Item(ResultSet resultSet, int rowNumber) throws SQLException {
        Invoice_Item invoice_item = new Invoice_Item();
        invoice_item.setInvoice_item_id(resultSet.getInt("invoice_item_id"));
        invoice_item.setInvoice_id(resultSet.getInt("invoice_id"));
        invoice_item.setQuantity(resultSet.getInt("quantity"));
        invoice_item.setUnit_rate(resultSet.getBigDecimal("unit_rate"));
        invoice_item.setDiscount(resultSet.getBigDecimal("discount"));
        return invoice_item;
    }
}
