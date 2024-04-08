package com.scb.axondemo.order.events;

import lombok.Data;

@Data
public class ShelfCreatedEvent {
    private final String shelfId;
    private final String name;
}
