package com.example.library.services;

import java.util.List;

import com.example.library.entity.BookEntity;

public interface BookService {

	public boolean deleteBookByName(String bookName);

	public List<BookEntity> searchBook(String filterBy, String name);

	public List<BookEntity> sortBooks(String sortBy, String method) throws Exception;

}
