package com.scb.axondemo.order.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class RegisterBookCommand {
    @TargetAggregateIdentifier
    private final String shelfId ;
    private final String isbn;
    private final String title;
}
