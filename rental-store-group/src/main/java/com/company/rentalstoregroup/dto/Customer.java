package com.company.rentalstoregroup.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Customer {
    //properties (columns)
    @NotNull
    @Digits(integer = 11, fraction = 0)
    private int customerId;
    @NotNull
    @Size(max = 50)
    private String firstName;
    @NotNull
    @Size(max = 50)
    private String lastName;
    @NotNull
    @Size(max = 75)
    private String email;
    @NotNull
    @Size(max = 50)
    private String company;
    @NotNull
    @Size(max = 50)
    private String phone;

    //getters and setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    //overwrite hashcode and equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId &&
                firstName.equals(customer.firstName) &&
                lastName.equals(customer.lastName) &&
                email.equals(customer.email) &&
                company.equals(customer.company) &&
                phone.equals(customer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, email, company, phone);
    }


    //constructor (optional, recommended)


//    public Customer(Integer customerId, String firstName, String lastName, String email, String company, String phone) {
//        this.customerId = customerId;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.company = company;
//        this.phone = phone;
//    }
}
