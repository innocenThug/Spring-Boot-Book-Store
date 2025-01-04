package com.onlinestore.Book.dto;


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
public class BookMessage {
	String message;
	Book book;
}