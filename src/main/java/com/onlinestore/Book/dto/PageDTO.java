package com.onlinestore.Book.dto;
import java.util.List;

import org.springframework.stereotype.Component;

import com.onlinestore.Book.entity.Book;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class PageDTO {
	int totalPages;
	long totalBooks;
	List<Book> listBooks;
}
