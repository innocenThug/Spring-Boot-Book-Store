package com.onlinestore.Book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinestore.Book.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
