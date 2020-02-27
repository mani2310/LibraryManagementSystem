package com.example.library.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GeneratorType;

import lombok.Data;

@Data
@Entity
public class BookEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long bookId;
	private String name;
	private String author;
	private String publicationDate;
	private int price;
	private String thumbnail;
	private String language;
	
	public BookEntity()
	{
		
	}
	
	public BookEntity(Long id, String name, String author, String date, String thumbnail, int price,String lang) {
		
		this.bookId=id;
		this.name=name;
		this.author=author;
		this.publicationDate=date;
		this.price=price;
		this.thumbnail=thumbnail;
		this.language=lang;
	}

}
