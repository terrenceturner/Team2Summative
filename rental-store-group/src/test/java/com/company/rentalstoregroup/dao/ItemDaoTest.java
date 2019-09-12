package com.company.rentalstoregroup.dao;

import com.company.rentalstoregroup.dto.Customer;
import com.company.rentalstoregroup.dto.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ItemDaoTest {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    InvoiceDao invoiceDao;

    @Autowired
    Invoice_ItemDao invoice_itemDao;

    @Before
    public void setUp() throws Exception {

        // Clean up the test db
        List<Item> itemList = itemDao.getAllItems();

        for (Item item : itemList) {
            itemDao.deleteItem(item.getItem_id());
        }
    }

    @Test
    public void getAddDeleteItem() {

        Item item = new Item();
        item.setItem_id(111);
        item.setName("Item");
        item.setDescription("Awesome Item");
        item.setDaily_rate(new BigDecimal("10.99"));
        itemDao.addItem(item);

        Item item1 = itemDao.getItem(item.getItem_id());

        assertEquals(item, item1);

        itemDao.deleteItem(item.getItem_id());

        item1 = itemDao.getItem(item.getItem_id());

        assertNull(item1);
    }

    @Test
    public void updateItem(){

        Item item = new Item();
        item.setItem_id(111);
        item.setName("Item");
        item.setDescription("Awesome Item");
        item.setDaily_rate(new BigDecimal("10.99"));
        itemDao.addItem(item);

        item.setDescription("Nice Item");
        item.setDaily_rate(new BigDecimal("11.99"));

        itemDao.updateItem(item);

        Item item1 = itemDao.getItem(item.getItem_id());

        assertEquals(item, item1);


    }

    @Test
    public void getAllItems(){

        Item item = new Item();
        item.setItem_id(111);
        item.setName("Item");
        item.setDescription("Awesome Item");
        item.setDaily_rate(new BigDecimal("10.99"));
        itemDao.addItem(item);

        item = new Item();
        item.setItem_id(222);
        item.setName("Other Item");
        item.setDescription("Nice Item");
        item.setDaily_rate(new BigDecimal("11.99"));
        itemDao.addItem(item);

        List<Item> itemList = itemDao.getAllItems();

        assertEquals(2, itemList.size());

    }
}