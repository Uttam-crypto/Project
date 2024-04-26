package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAvailableTrue();

    List<Book> findByAuthorId(Long authorId);

    @Query("SELECT b FROM Book b WHERE b.id IN :bookIds")
    List<Book> findByIdIn(@Param("bookIds") List<Long> bookIds);
}
