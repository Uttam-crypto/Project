package com.library.entity;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="rental")
public class Rental {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @NotNull(message = "Rental date is required")
	    private LocalDate rentalDate;

	    @FutureOrPresent(message = "Return date must be in the present or future")
	    private LocalDate returnDate;

	    @NotNull(message = "Book is required")
	    @ManyToOne
	    @JoinColumn(name = "book_id")
	    private Book book;
	    
	    @NotBlank(message = "Renter name is required")
	    private String renterName;

	    // Getters and setters
	    public Long getId() {
			return id;
		}
	    
	    public void setId(Long id) {
			this.id = id;
		}

		public LocalDate getRentalDate() {
			return rentalDate;
		}

		public void setRentalDate(LocalDate rentalDate) {
			this.rentalDate = rentalDate;
		}

		public LocalDate getReturnDate() {
			return returnDate;
		}

		public void setReturnDate(LocalDate returnDate) {
			this.returnDate = returnDate;
		}

		public Book getBook() {
			return book;
		}

		public void setBook(Book book) {
			this.book = book;
		}
		
		public String getRenterName() {
			return renterName;
		}

		public void setRenterName(String renterName) {
			this.renterName = renterName;
		}

}
