package com.company.rentalstoregroup.dao;
import com.company.rentalstoregroup.dto.Invoice_Item;
import java.util.List;

public interface Invoice_ItemDao {
    // CRUD Methods
    Invoice_Item addInvoice_Item(Invoice_Item invoice_item);
    Invoice_Item getInvoice_Item(int invoice_item_id);
    List<Invoice_Item> getAllInvoice_Item();
    void updateInvoice_Item(Invoice_Item invoice_item);
    void deleteInvoice_Item(int invoice_item_id);
}
