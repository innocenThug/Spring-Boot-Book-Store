package com.onlinestore.Book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.onlinestore.Book.dto.BookMessage;
import com.onlinestore.Book.dto.PageDTO;
import com.onlinestore.Book.entity.Book;
import com.onlinestore.Book.exception.BookNotFoundException;
import com.onlinestore.Book.service.BookService;


@Controller
public class BookController {
	@Autowired
	private BookService service;
	
	//landing page
	@GetMapping("/")
	public String landingPage() {
		return "homepage";
	}
	//display all books
	@GetMapping("/index")
	public ModelAndView allbooks(@RequestParam(defaultValue = "10") int pageSize, ModelAndView mav) {
		return findPaginated(1,pageSize,"id","asc",mav);
	}
	//go to add new book details page
	@GetMapping("/addbookform")
	public ModelAndView newBookForm(ModelAndView mav) {
		Book book=new Book();
		mav.setViewName("newbookform");
		mav.addObject("book", book);
		return mav;

	}
	//method to post and save new book details in the database
	@PostMapping("/savebook")
	public String saveBook(@ModelAttribute Book book, RedirectAttributes rda) {
		String message=service.saveBook(book);
			rda.addFlashAttribute("message", message);
		return "redirect:/index";
	}
	@GetMapping("/find")
	public String getId() throws BookNotFoundException {
		return "findBook";
	}
	//method to get the book by ID
	@GetMapping("/getBookById")
	public ModelAndView getBookById(@ModelAttribute Book b1, ModelAndView mav) throws BookNotFoundException {
	    BookMessage bm = service.getBook(b1.getId());
	    mav.setViewName("bookById");
	    mav.addObject("book", bm.getBook());
	    mav.addObject("message", bm.getMessage());
	    return mav;
	}	
	//method to update the book details
	@GetMapping("/showupdateform/{id}")
	public ModelAndView updateForm(@PathVariable long id, ModelAndView mav) throws BookNotFoundException {
		BookMessage bm=service.getBook(id);
		mav.setViewName("updateform");
		mav.addObject("book",bm.getBook());
		mav.addObject("message", bm.getMessage());
		return mav;
	}
	//method to post and update book details in the database
	@PostMapping("/updatebook")
	public String updateBook(@ModelAttribute Book book, RedirectAttributes rda) {
		String message=service.updateBook(book);
			rda.addFlashAttribute("message", message);
		return "redirect:/index";
	}
	
	
	//method to delete book from list
	@GetMapping("/deletebook/{id}")
	public String deleteBook(@PathVariable long id, RedirectAttributes rda) throws BookNotFoundException {
		String message=service.deleteBook(id);
		rda.addFlashAttribute("message", message);
		return "redirect:/index";
	}
	@GetMapping("/page/{pageNo}")
	public ModelAndView findPaginated(@PathVariable int pageNo,@RequestParam(defaultValue = "10") int pageSize,@RequestParam String sortField,
			@RequestParam(defaultValue ="asc") String sortOrder, ModelAndView mav) { //@PathVariable(value = "pageSize")int pageSize
		// Ensure that the default value of "sortOrder" is either "asc" or "desc"
	    if (sortOrder == null || (!sortOrder.equalsIgnoreCase("asc") && !sortOrder.equalsIgnoreCase("desc")))
	    	sortOrder = "asc";
		PageDTO dto=service.findPage(pageNo, pageSize, sortField, sortOrder);
		mav.setViewName("index");
		mav.addObject("currentPage", pageNo);
		mav.addObject("pageSize", pageSize);
		mav.addObject("totalPages",dto.getTotalPages());
		mav.addObject("totalBooks",dto.getTotalBooks());
		mav.addObject("listBooks",dto.getListBooks());
		mav.addObject("sortField",sortField);
		mav.addObject("sortOrder",sortOrder);
		mav.addObject("reverseSortOrder",sortOrder.equalsIgnoreCase("asc")? "desc" : "asc");
		return mav;
		
	}
}
