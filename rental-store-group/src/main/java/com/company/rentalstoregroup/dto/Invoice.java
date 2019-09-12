package com.company.rentalstoregroup.dto;

import javax.swing.text.DateFormatter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Invoice {
    //properties (columns)
    private int invoice_id;
    private int customer_id;
    private LocalDate order_date;
    private LocalDate pickup_date;
    private LocalDate return_date;
    private BigDecimal late_fee;

    //getters and setters

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


    //overwrite hashcode and equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return invoice_id == invoice.invoice_id &&
                customer_id == invoice.customer_id &&
                order_date.equals(invoice.order_date) &&
                pickup_date.equals(invoice.pickup_date) &&
                return_date.equals(invoice.return_date) &&
                late_fee.equals(invoice.late_fee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoice_id, customer_id, order_date, pickup_date, return_date, late_fee);
    }

    //constructor (optional, recommended)

    public Invoice(int customer_id, LocalDate order_date, LocalDate pickup_date, LocalDate return_date, BigDecimal late_fee) {
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.pickup_date = pickup_date;
        this.return_date = return_date;
        this.late_fee = late_fee;
    }

    public Invoice(){}
}
