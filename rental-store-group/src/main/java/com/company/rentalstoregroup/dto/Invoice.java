package com.company.rentalstoregroup.dto;

import javax.swing.text.DateFormatter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Invoice {
    //properties (columns)
    private int invoice_id;
    private int customer_id;
    private Date order_date;
    private Date pickup_date;
    private Date return_date;
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

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Date getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(Date pickup_date) {
        this.pickup_date = pickup_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
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

    public Invoice(int invoice_id, int customer_id, Date order_date, Date pickup_date, Date return_date, BigDecimal late_fee) {
        this.invoice_id = invoice_id;
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.pickup_date = pickup_date;
        this.return_date = return_date;
        this.late_fee = late_fee;
    }

    public Invoice(){}
}
