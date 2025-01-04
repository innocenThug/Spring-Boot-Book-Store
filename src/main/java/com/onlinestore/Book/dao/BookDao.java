package com.onlinestore.Book.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.onlinestore.Book.entity.Book;
import com.onlinestore.Book.exception.BookNotFoundException;

public interface BookDao {
	public List<Book> getAllBooks();
	public Book saveBook(Book book);
	public Book getBook(long id) throws BookNotFoundException;
	public boolean deleteBook(long id) throws BookNotFoundException;
	public Page<Book> findAll(Pageable pageable);
}
