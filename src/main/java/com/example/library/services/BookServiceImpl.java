package com.example.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.library.entity.BookEntity;
import com.example.library.repository.BookRepository;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class BookServiceImpl implements BookService{
	
	@Autowired
	BookRepository bookRepo;

	@Override
	public boolean deleteBookByName(String bookName) {
		
		List<BookEntity> books = (List<BookEntity>) bookRepo.findAll();
		
		if(books != null)
		{
			for(BookEntity book :books)
			{
				if(book.getName().equals(bookName))
				{
					long id = book.getBookId();
					bookRepo.deleteById(id);
					return true;
				}
			}
		}
		return false;
		
	}

	@Override
	public List<BookEntity> searchBook(String filterBy, String name) {
		List<BookEntity> books = (List<BookEntity>) bookRepo.findAll();
		
		if(books != null && books.size() >0)
		{
			if(filterBy.equals("author"))
			{
				return books.stream().filter(b->b.getAuthor().equals(name)).collect(Collectors.toList());
			}
			if(filterBy.equals("name"))
			{
				return books.stream().filter(b->b.getName().equals(name)).collect(Collectors.toList());
			}
		}
		return null;
		
	}

	@Override
	public List<BookEntity> sortBooks(String sortBy, String method) throws Exception {
		
		List<BookEntity> books = (List<BookEntity>) bookRepo.findAll();
		
		if(books != null && books.size() >0)
		{
			
			if(method.equals("ascending"))
			{
				if(sortBy.equals("price"))
				{
					books.sort((BookEntity s1, BookEntity s2)->s1.getPrice()-s2.getPrice());
				}
				else
				{
					books.sort((BookEntity s1, BookEntity s2)->s1.getName().compareTo(s2.getName()));
				}
			}else if(method.equals("descending"))
			{
				if(sortBy.equals("price"))
				{
					books.sort((BookEntity s1, BookEntity s2)->s2.getPrice()-s1.getPrice());
				}
				else
				{
					books.sort((BookEntity s1, BookEntity s2)->s2.getName().compareTo(s2.getName()));
				}
				
			}
			else
			{
				throw new Exception("Invalid method:"+method);
			}
		}
		
		return books;
	}

}
