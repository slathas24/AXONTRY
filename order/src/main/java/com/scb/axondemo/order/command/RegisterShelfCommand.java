package com.scb.axondemo.order.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class RegisterShelfCommand {
    @TargetAggregateIdentifier
    private final String shelfId;
    private final String name;

}
