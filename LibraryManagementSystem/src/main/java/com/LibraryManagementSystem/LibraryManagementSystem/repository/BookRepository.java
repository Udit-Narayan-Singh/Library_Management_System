package com.LibraryManagementSystem.LibraryManagementSystem.repository;

import com.LibraryManagementSystem.LibraryManagementSystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
