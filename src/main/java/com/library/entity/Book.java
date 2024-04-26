package com.library.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="book")
public class Book {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @NotBlank(message = "Title is required")
	    private String title;

	    @NotBlank(message = "ISBN is required")
	    @Size(min = 3, max = 13, message = "ISBN must be between 10 and 13 characters")
	    private String isbn;

	    @Min(value = 1000, message = "Publication year must be at least 1000")
	    private int publicationYear;

	    private boolean available;
	    
	    @NotBlank(message = "Author is required")
	    @ManyToOne
	    @JoinColumn(name = "author_id")
	    private Author author;

	    // Getters and setters
	    
	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getIsbn() {
			return isbn;
		}

		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}

		public int getPublicationYear() {
			return publicationYear;
		}

		public void setPublicationYear(int publicationYear) {
			this.publicationYear = publicationYear;
		}

		public Author getAuthor() {
			return author;
		}

		public void setAuthor(Author author) {
			this.author = author;
		}
		

		public boolean isAvailable() {
			return available;
		}
		 
		
		public void setAvailable(boolean available) {
		        this.available = available;
		    }

	}


