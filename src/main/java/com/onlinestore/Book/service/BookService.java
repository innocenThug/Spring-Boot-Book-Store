package com.onlinestore.Book.service;

import java.util.List;

import com.onlinestore.Book.dto.BookMessage;
import com.onlinestore.Book.dto.PageDTO;
import com.onlinestore.Book.entity.Book;
import com.onlinestore.Book.exception.BookNotFoundException;



public interface BookService {
	public List<Book> getAllBooks();
	public String saveBook(Book book);
	public BookMessage getBook(long id) throws BookNotFoundException;
	public String deleteBook(long id) throws BookNotFoundException;
	public PageDTO findPage(int pageNo, int pageSize, String sortField, String sortOrder);
	public String updateBook(Book book);
}
