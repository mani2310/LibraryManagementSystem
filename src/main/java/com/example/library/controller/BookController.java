package com.example.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.entity.BookEntity;
import com.example.library.repository.BookRepository;
import com.example.library.services.BookService;
import org.json.*;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	BookRepository bookRepo;
	
	@Autowired
	BookService bookService;
	
	@GetMapping
	public List<BookEntity> getAllBooks()
	{
		return (List<BookEntity>) bookRepo.findAll();
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addNewBook(@RequestBody BookEntity book)
	{
		if(book != null)
		{
			bookRepo.save(book);
			return new ResponseEntity<>("SuccessFully added new book",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("Error occurred while adding new book",HttpStatus.BAD_REQUEST);
		}	
	}
	
	@DeleteMapping("/remove")
	public ResponseEntity<?> deleteBookDetails(@RequestBody String bookName)
	{
		
		if(bookName != null || bookName !="")
		{
			JSONObject jsonObj =new JSONObject(bookName);
			String name = (String) jsonObj.get("bookName").toString();
			
			if(bookService.deleteBookByName(name))
			{
				return new ResponseEntity<>("Successfully Deleted book"+bookName, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>("Unable to delete book",HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	
	@GetMapping("/search")
	public  ResponseEntity<?> searchBooks(@RequestParam(value="filterBy") String filterBy, @RequestParam(value="name") String name)
	{
		
		List<BookEntity> searchList = bookService.searchBook(filterBy,name);
		
		if(searchList != null)
		{
			return new ResponseEntity<>(searchList, HttpStatus.OK);
		}
			
		return new ResponseEntity<>("Unable to search book",HttpStatus.INTERNAL_SERVER_ERROR);		
		
	}
	
	@GetMapping("/sortBooks")
	public  ResponseEntity<?> sortBooks(@RequestParam(value="sortBy") String sortBy, @RequestParam(value="method") String method)
	{
		List<BookEntity> sortedBookList;
		try {
			sortedBookList = bookService.sortBooks(sortBy,method);
			if(sortedBookList != null)
			{
				return new ResponseEntity<>(sortedBookList, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>("Unable to sort book,caught error::"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		return null;
		
		
		
	}

}
