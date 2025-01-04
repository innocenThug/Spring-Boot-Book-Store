package com.onlinestore.Book.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.onlinestore.Book.entity.Book;
import com.onlinestore.Book.exception.BookNotFoundException;
import com.onlinestore.Book.repository.BookRepository;
@Repository
public class BookDaoImpl implements BookDao {
	@Autowired
	private BookRepository repository;

	@Override
	public List<Book> getAllBooks() {
		return repository.findAll();
	}

	@Override
	public Book saveBook(Book book) {
		return repository.save(book);
		
	}

	@Override
	public Book getBook(long id) throws BookNotFoundException {
		Optional<Book> opt=repository.findById(id);
		if(opt.isPresent())
			return opt.get();
		else {
			throw new BookNotFoundException("Book Not Found for ID: "+id);
		}
	}

	@Override
	public boolean deleteBook(long id) throws BookNotFoundException {
			Book b=getBook(id);
			if (b!=null) {
				repository.delete(b);
				return true;
			}
		return false;
	}

	@Override
	public Page<Book> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

}
