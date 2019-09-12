package com.company.rentalstoregroup.dto;
import java.math.BigDecimal;
import java.util.Objects;

public class Invoice_Item {
    // Properties
    private int invoice_item_id;
    private int invoice_id;
    private int quantity;
    private BigDecimal unit_rate;
    private BigDecimal discount;

    // Getters & Setters
    public int getInvoice_item_id() {
        return this.invoice_item_id;
    }
    public void setInvoice_item_id(int invoice_item_idIn) {
        this.invoice_item_id = invoice_item_idIn;
    }
    public int getInvoice_id() {
        return this.invoice_id;
    }
    public void setInvoice_id(int invoice_idIn) {
        this.invoice_id = invoice_idIn;
    }

    public int getQuantity() {
        return this.quantity;
    }
    public void setQuantity(int quantityIn) {
        this.quantity = quantityIn;
    }
    public BigDecimal getUnit_rate() {
        return this.unit_rate;
    }
    public void setUnit_rate(BigDecimal unit_rateIn) {
        this.unit_rate = unit_rateIn;
    }
    public BigDecimal getDiscount() {
        return this.discount;
    }
    public void setDiscount(BigDecimal discountIn) {
        this.discount = discountIn;
    }

    // equals() & hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice_Item that = (Invoice_Item) o;
        return invoice_item_id == that.invoice_item_id &&
                invoice_id == that.invoice_id &&
                quantity == that.quantity &&
                unit_rate.equals(that.unit_rate) &&
                discount.equals(that.discount);
    }
    @Override
    public int hashCode() {
        return Objects.hash(invoice_item_id, invoice_id, quantity, unit_rate, discount);
    }
}
