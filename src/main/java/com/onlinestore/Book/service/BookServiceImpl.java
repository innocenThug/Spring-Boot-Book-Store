package com.onlinestore.Book.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.onlinestore.Book.dao.BookDao;
import com.onlinestore.Book.dto.BookMessage;
import com.onlinestore.Book.dto.PageDTO;
import com.onlinestore.Book.entity.Book;
import com.onlinestore.Book.exception.BookNotFoundException;
import com.onlinestore.Book.exception.BookNotSavedException;
@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao dao;
	@Autowired
	private BookMessage bm;
	@Autowired
	private PageDTO dto;
	@Override
	public List<Book> getAllBooks() {
		return dao.getAllBooks();
	}
	@Override
	public String saveBook(Book book) {
		Book newBook= dao.saveBook(book);
		if (newBook!=null) {
			return "Book Saved Successfully";
		} else {
			throw new BookNotSavedException("Book Not Saved");
		}		
	}
	@Override
	public BookMessage getBook(long id)  {
		Book book= dao.getBook(id);
		if (book!=null) {
			bm.setBook(book);
			bm.setMessage("Book Retrieved Successfully");
		} else {
			bm.setBook(book);
			bm.setMessage("Book Not Found for ID: "+id);
		}
		return bm;
	}
	@Override
	public String updateBook(Book book)  {
		dao.saveBook(book);
		return "Book Updated Successfully";
		}
	@Override
	public String deleteBook(long id) throws BookNotFoundException {
		if (dao.deleteBook(id)) {
			return "Book Deleted Successfully";
		} else {
			return "Book Not Found for ID: "+id;
		}	
	}
	@Override
	public PageDTO findPage(int pageNo, int pageSize, String sortField, String sortOrder) {
		Sort sort= sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable=PageRequest.of(pageNo-1, pageSize, sort); //Spring Data JPA API considers zero base send sort object ig both pagination and sorting is needed.
		Page<Book> page= dao.findAll(pageable);
		List<Book> list=page.getContent();
		dto.setTotalPages(page.getTotalPages());
		dto.setTotalBooks(page.getTotalElements());
		dto.setListBooks(list);
		return dto;
	}

}
