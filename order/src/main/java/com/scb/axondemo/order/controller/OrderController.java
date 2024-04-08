package com.scb.axondemo.order.controller;

import com.scb.axondemo.order.aggregate.ShelfAggregate;
import com.scb.axondemo.order.command.RegisterBookCommand;
import com.scb.axondemo.order.command.RegisterShelfCommand;
import com.scb.axondemo.order.model.BookBean;
import com.scb.axondemo.order.model.ShelfBean;
import com.scb.axondemo.order.queries.GetBookQuery;
import com.scb.axondemo.order.queries.GetShelfQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.ExecutionException;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class OrderController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @Autowired
    public  OrderController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/library/shelf")
    public String addShelf(@RequestBody ShelfBean shelf) {
        commandGateway.send(new RegisterShelfCommand(shelf.getShelfId(), shelf.getName()));
        return "Saved";
    }

    @GetMapping("/library/{shelfId}")
    public ShelfAggregate getLibrary(@PathVariable String shelfId) throws InterruptedException, ExecutionException, java.util.concurrent.ExecutionException {
        CompletableFuture<ShelfAggregate> future= queryGateway.query(new GetShelfQuery(shelfId),ShelfAggregate.class);
        return future.get();
    }

    @PostMapping("/library/shelf/book")
    public String addBook(@RequestBody BookBean book) {
        System.out.println(book);
        commandGateway.send(new RegisterBookCommand(book.getShelfId(),book.getIsbn(), book.getTitle()));

        return "Added";
    }

@GetMapping("/library/{shelf}/book")
 public List<BookBean> getAllBook(@PathVariable String  shelf) throws InterruptedException, ExecutionException, java.util.concurrent.ExecutionException {
     return queryGateway.query(new GetBookQuery(shelf), ResponseTypes.multipleInstancesOf(BookBean.class)).get();
 }


}
