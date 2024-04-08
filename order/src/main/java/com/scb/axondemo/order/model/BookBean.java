package com.scb.axondemo.order.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookBean {
    private String isbn;
    private String title;
     private String shelfId;

}
