package com.example.library.commandLineRunner;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.library.entity.BookEntity;
import com.example.library.repository.BookRepository;

@Component
public class LibraryManagementCommandLineRunner implements CommandLineRunner{

	@Autowired
	BookRepository bookRepo;
	
	@Override
	public void run(String... args) throws Exception {
		Stream.of(new BookEntity(new Long(1),"HarryPotter", "J.K.Rowling","23-10-1990","/image/Rusty.jpg",100,"English"),
                new BookEntity(new Long(2),"Origin", "Dan Brown","23-10-1990","/image/Rusty.jpg",500,"English"))
              .forEach(book ->
              bookRepo.save(book)
      );
      bookRepo.findAll().forEach(System.out::println);
		
	}

}
