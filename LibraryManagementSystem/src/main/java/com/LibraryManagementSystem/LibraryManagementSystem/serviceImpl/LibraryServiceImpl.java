package com.LibraryManagementSystem.LibraryManagementSystem.serviceImpl;

import com.LibraryManagementSystem.LibraryManagementSystem.model.Book;
import com.LibraryManagementSystem.LibraryManagementSystem.repository.BookRepository;
import com.LibraryManagementSystem.LibraryManagementSystem.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final BookRepository bookRepository;

    @Autowired
    public LibraryServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll().stream().filter(Book::isAvailable).collect(Collectors.toList());
    }

    @Override
    public Book getBookById(String id) {
        try {
            Long bookId = Long.parseLong(id);
            Optional<Book> optionalBook = bookRepository.findById(bookId);
            return optionalBook.orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Book addBook(Book book) {
        Book savedBook = bookRepository.save(book);
        return savedBook;
    }

    public String removeBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            bookRepository.deleteById(id);
            return "Book with ID " + id + " has been successfully deleted.";
        } else {
            return "Book with ID " + id + " not found. Deletion failed.";
        }
    }

    @Override
    public Book updateBook(String id, Book updateBook) {
        try {
            Long bookId = Long.parseLong(id);
            Optional<Book> optionalBook = bookRepository.findById(bookId);

            if (optionalBook.isPresent()) {
                Book existingBook = optionalBook.get();
                existingBook.setTitle(updateBook.getTitle());
                existingBook.setAuthor(updateBook.getAuthor());
                existingBook.setIsbn(updateBook.getIsbn());
                existingBook.setAvailable(updateBook.isAvailable());

                return bookRepository.save(existingBook);
            } else {
                return null; // or throw a custom exception indicating book not found
            }
        } catch (NumberFormatException e) {
            return null; // or throw a custom exception indicating invalid book ID
        }
    }

    @Override
    public String borrowBook(String id) {
        try {
            Long bookId = Long.parseLong(id); // Convert String to Long
            Optional<Book> optionalBook = bookRepository.findById(bookId);

            if (optionalBook.isPresent()) {
                Book bookToBorrow = optionalBook.get();
                if (bookToBorrow.isAvailable()) {
                    bookToBorrow.setAvailable(false);
                    bookRepository.save(bookToBorrow);
                    return "Book with ID " + id + " has been successfully borrowed.";
                } else {
                    return "Book with ID " + id + " is already borrowed.";
                }
            } else {
                return "Book with ID " + id + " not found.";
            }
        } catch (NumberFormatException e) {
            return "Invalid book ID: " + id;
        }
    }

    @Override
    public String returnBook(String id) {
        try {
            Long bookId = Long.parseLong(id);
            Optional<Book> optionalBook = bookRepository.findById(bookId);

            if (optionalBook.isPresent()) {
                Book bookToReturn = optionalBook.get();
                if (!bookToReturn.isAvailable()) {
                    bookToReturn.setAvailable(true);
                    bookRepository.save(bookToReturn);
                    return "Book with ID " + id + " has been successfully returned.";
                } else {
                    return "Book with ID " + id + " was not borrowed.";
                }
            } else {
                return "Book with ID " + id + " not found.";
            }
        } catch (NumberFormatException e) {
            return "Invalid book ID: " + id;                     //SELECT * FROM book;
        }
    }
}