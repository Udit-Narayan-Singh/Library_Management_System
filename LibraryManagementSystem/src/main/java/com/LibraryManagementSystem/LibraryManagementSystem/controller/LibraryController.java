package com.LibraryManagementSystem.LibraryManagementSystem.controller;

import com.LibraryManagementSystem.LibraryManagementSystem.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.LibraryManagementSystem.LibraryManagementSystem.service.LibraryService;

import java.util.List;

    @RestController
    @RequestMapping("/api/library")
    public class LibraryController {

        private final LibraryService libraryService;

        @Autowired
        public LibraryController(LibraryService libraryService) {
            this.libraryService = libraryService;
        }

        @GetMapping("/books")
        public List<Book> getAllBooks() {
            return libraryService.getAllBooks();
        }

        @GetMapping("/books/{id}")
        public Book getBookById(@PathVariable String id) {
            return libraryService.getBookById(id);
        }

        @PostMapping("/books")
        public Book addBook(@RequestBody Book book) {
            return libraryService.addBook(book);
        }

        @PutMapping("/books/{id}")
        public ResponseEntity<?> updateBook(@PathVariable String id, @RequestBody Book updateBook) {
            Book updatedBook = libraryService.updateBook(id, updateBook);
            if (updatedBook != null) {
                return ResponseEntity.ok(updatedBook);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/books/{id}")
        public String removeBook(@PathVariable Long id) {
            return libraryService.removeBook(id);
        }

        @PostMapping("/borrow/{id}")
        public String borrowBook(@PathVariable String id) {
            String result = libraryService.borrowBook(id);
            return result;
        }
        @PostMapping("/return/{id}")
        public String returnBook(@PathVariable String id) {
            String result = libraryService.returnBook(id);
            return result;
    }
}
