package com.example.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.library.entity.BookEntity;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long>{

}
