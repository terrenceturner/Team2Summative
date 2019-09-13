package com.company.rentalstoregroup.viewmodel;

import com.company.rentalstoregroup.dto.Invoice_Item;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InvoiceViewModel {
    private int invoice_id;
    private int customer_id;
    private LocalDate order_date;
    private LocalDate pickup_date;
    private LocalDate return_date;
    private BigDecimal late_fee;
    private List<Invoice_Item> invoice_items = new ArrayList<>();

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public LocalDate getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }

    public LocalDate getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(LocalDate pickup_date) {
        this.pickup_date = pickup_date;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    public BigDecimal getLate_fee() {
        return late_fee;
    }

    public void setLate_fee(BigDecimal late_fee) {
        this.late_fee = late_fee;
    }

    public List<Invoice_Item> getInvoice_items() {
        return invoice_items;
    }

    public void setInvoice_items(List<Invoice_Item> invoice_items) {
        this.invoice_items = invoice_items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return invoice_id == that.invoice_id &&
                customer_id == that.customer_id &&
                order_date.equals(that.order_date) &&
                pickup_date.equals(that.pickup_date) &&
                return_date.equals(that.return_date) &&
                late_fee.equals(that.late_fee) &&
                invoice_items.equals(that.invoice_items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoice_id, customer_id, order_date, pickup_date, return_date, late_fee, invoice_items);
    }

    public InvoiceViewModel(int customer_id, LocalDate order_date, LocalDate pickup_date, LocalDate return_date, BigDecimal late_fee, List<Invoice_Item> invoice_items) {
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.pickup_date = pickup_date;
        this.return_date = return_date;
        this.late_fee = late_fee;
        this.invoice_items = invoice_items;
    }

    public InvoiceViewModel(){}
}
