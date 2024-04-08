package com.scb.axondemo.order.repository;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class BookEntity {
         @Id
        private String isbn;
        @Column
        private String shelfId;
        @Column
        private String title;
    }



