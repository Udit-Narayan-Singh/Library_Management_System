package com.LibraryManagementSystem.LibraryManagementSystem.service;

import com.LibraryManagementSystem.LibraryManagementSystem.model.Book;

import java.util.List;

public interface LibraryService {
    List<Book> getAllBooks();
    Book getBookById(String id);
    Book addBook(Book book);
    Book updateBook(String id, Book updateBook);
    String removeBook(Long id);
    String borrowBook(String id);
    String returnBook(String id);
}
