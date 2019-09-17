package com.company.rentalstoregroup.dto;

import javax.swing.text.DateFormatter;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Invoice {
    //properties (columns)
    @NotNull
    @Digits(integer = 11, fraction = 0)
    private int invoice_id;
    @NotNull
    @Digits(integer = 11, fraction = 0)
    private int customer_id;
    @NotNull
    private LocalDate order_date;
    @NotNull
    private LocalDate pickup_date;
    @NotNull
    private LocalDate return_date;
    @NotNull
    @Digits(integer = 8, fraction = 2)
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
