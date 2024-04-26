package com.library.entity;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotBlank(message = "Name is required")
        private String name;
        @NotBlank(message = "Biography is required")
        private String biography;
	    
	    public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getBiography() {
			return biography;
		}
		public void setBiography(String biography) {
			this.biography = biography;
		}
	}