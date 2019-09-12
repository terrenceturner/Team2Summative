package com.company.rentalstoregroup.service;

import com.company.rentalstoregroup.dao.CustomerDao;
import com.company.rentalstoregroup.dao.InvoiceDao;
import com.company.rentalstoregroup.dao.Invoice_ItemDao;
import com.company.rentalstoregroup.dao.ItemDao;
import com.company.rentalstoregroup.dto.Invoice_Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceLayer {
    //    private AlbumDao albumDao;
    //    private ArtistDao artistDao;
    //    private LabelDao labelDao;
    //    private TrackDao trackDao;
    //
    //    @Autowired
    //    public ServiceLayer(AlbumDao albumDao, ArtistDao artistDao, LabelDao labelDao, TrackDao trackDao) {
    //        this.albumDao = albumDao;
    //        this.artistDao = artistDao;
    //        this.labelDao = labelDao;
    //        this.trackDao = trackDao;
    //    }

    private CustomerDao customerDao;
    private ItemDao itemDao;
    private Invoice_ItemDao invoice_itemDao;
    private InvoiceDao invoiceDao;

    @Autowired
    public ServiceLayer(CustomerDao customerDao, ItemDao itemDao, Invoice_ItemDao invoice_itemDao, InvoiceDao invoiceDao) {
        this.customerDao = customerDao;
        this.itemDao = itemDao;
        this.invoice_itemDao = invoice_itemDao;
        this.invoiceDao = invoiceDao;
    }

    //Invoice API

}
