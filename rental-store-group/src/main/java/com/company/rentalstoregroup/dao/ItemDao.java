package com.company.rentalstoregroup.dao;

import com.company.rentalstoregroup.dto.Item;

import java.util.List;

public interface ItemDao {

    //DAO methods (no implementation code, i.e. Class addClass (Class class);)
    Item getItem(int item_id);

    List<Item> getAllItems();

    Item addItem(Item item);

    void updateItem(Item item);

    void deleteItem(int id);
}
