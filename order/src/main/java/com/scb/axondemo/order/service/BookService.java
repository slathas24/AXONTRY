package com.scb.axondemo.order.service;

import com.scb.axondemo.order.events.BookCreatedEvent;
import com.scb.axondemo.order.model.BookBean;
import com.scb.axondemo.order.queries.GetBookQuery;
import com.scb.axondemo.order.repository.BookEntity;
import com.scb.axondemo.order.repository.BookRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventHandler
    public void addBook(BookCreatedEvent event) throws Exception {
        BookEntity book = new BookEntity();
        book.setIsbn(event.getIsbn());
        book.setShelfId(event.getShelfId());
        book.setTitle(event.getTitle());
        bookRepository.save(book);
    }

    @QueryHandler
    public List<BookBean> getBooks(GetBookQuery query) {
        return bookRepository.findByShelfId(query.getShelfId()).stream().map(toBook()).collect(Collectors.toList());
    }


    private Function<BookEntity, BookBean> toBook() {
        return e -> {
            BookBean book = new BookBean();
            book.setIsbn(e.getIsbn());
            book.setTitle(e.getTitle());
            book.setShelfId(e.getShelfId());
            return book;
        };
    }


}
