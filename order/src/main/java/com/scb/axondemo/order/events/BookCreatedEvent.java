package com.scb.axondemo.order.events;

import lombok.Data;

@Data
public class BookCreatedEvent {
    private final String shelfId ;
    private final String isbn;
    private final String title;
}
