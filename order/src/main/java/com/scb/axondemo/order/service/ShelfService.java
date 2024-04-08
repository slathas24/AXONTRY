package com.scb.axondemo.order.service;

import com.scb.axondemo.order.aggregate.ShelfAggregate;

import com.scb.axondemo.order.queries.GetShelfQuery;
import org.axonframework.messaging.ExecutionException;
import org.axonframework.modelling.command.Repository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ShelfService {
    private final Repository<ShelfAggregate> shelfRepository;

    public ShelfService(Repository<ShelfAggregate> shelfRepository) {
        this.shelfRepository = shelfRepository;
    }

    @QueryHandler
    public ShelfAggregate getShelf(GetShelfQuery query) throws InterruptedException,  java.util.concurrent.ExecutionException {
        CompletableFuture<ShelfAggregate> future = new CompletableFuture<ShelfAggregate>();
       shelfRepository.load("" + query.getShelfId()).execute(future::complete);
        return future.get();
    }

}
