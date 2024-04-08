package com.scb.axondemo.order.aggregate;

import com.scb.axondemo.order.command.RegisterBookCommand;
import com.scb.axondemo.order.command.RegisterShelfCommand;
import com.scb.axondemo.order.events.BookCreatedEvent;
import com.scb.axondemo.order.events.ShelfCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Aggregate
public class ShelfAggregate {
    @AggregateIdentifier
    private String  shelfiId;
    private String name;
    private List<String> isbnBooks;

    protected  ShelfAggregate() {

    }
    @CommandHandler
    public ShelfAggregate(RegisterShelfCommand cmd) {
        Assert.notNull(cmd.getShelfId(), "ID should not be null");
        Assert.notNull(cmd.getName(), "Name should not be null");

        AggregateLifecycle.apply(new ShelfCreatedEvent(cmd.getShelfId(), cmd.getName()));
    }

    public String getShelfId() {
        return shelfiId;
    }

    public String getName() {
        return name;
    }

    public List<String> getIsbnBooks() {
        return isbnBooks;
    }

    @CommandHandler
    public void addBook(RegisterBookCommand cmd) {
        System.out.println("HEY I AM HERE");
        Assert.notNull(cmd.getShelfId(), "ID should not be null");
        Assert.notNull(cmd.getIsbn(), "Book ISBN should not be null");
       System.out.println(cmd.getShelfId());
        AggregateLifecycle.apply(new BookCreatedEvent(cmd.getShelfId(), cmd.getIsbn(), cmd.getTitle()));
    }

    @EventSourcingHandler
    private void handleCreatedEvent(ShelfCreatedEvent event) {
      shelfiId= event.getShelfId();
        name = event.getName();
        isbnBooks = new ArrayList<>();
    }

    @EventSourcingHandler
    private void addBook(BookCreatedEvent event) {
        isbnBooks.add(event.getIsbn());
    }

}
