package com.company.rentalstoregroup.dao;

import com.company.rentalstoregroup.dto.Customer;
import com.company.rentalstoregroup.dto.Invoice;

import java.util.List;

public interface InvoiceDao {
    //DAO methods (no implementation code, i.e. Class addClass (Class class);)
    Invoice addInvoice(Invoice invoice);

    Invoice getInvoice(int id);

    List<Invoice> getAllInvoices();

    void updateInvoice(Invoice invoice);

    void deleteInvoice(int id);

    List<Invoice> getInvoicesByCustomer(int customerId);
}
