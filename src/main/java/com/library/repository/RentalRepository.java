package com.library.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.entity.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long>{
	List<Rental> findByReturnDateBefore(LocalDate date);
	
}