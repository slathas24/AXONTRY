package com.scb.axondemo.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface  BookRepository extends JpaRepository<BookEntity,String> {
    List<BookEntity> findByShelfId(String shelfId);


}
