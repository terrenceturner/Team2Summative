package com.company.rentalstoregroup.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

public class Item {

    //properties (columns)
    @NotNull
    @Digits(integer = 11, fraction = 0)
    private int item_id;
    @NotNull
    @Size(max = 50)
    private String name;
    @NotNull
    @Size(max = 255)
    private String description;
    @NotNull
    @Digits(integer = 8, fraction = 2)
    private BigDecimal daily_rate;

    //getters and setters
    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDaily_rate() {
        return daily_rate;
    }

    public void setDaily_rate(BigDecimal daily_rate) {
        this.daily_rate = daily_rate;
    }


    //overwrite hashcode and equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return item_id == item.item_id &&
                name.equals(item.name) &&
                Objects.equals(description, item.description) &&
                daily_rate.equals(item.daily_rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item_id, name, description, daily_rate);
    }


    //constructor (optional, recommended)

}
