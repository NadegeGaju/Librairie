package com.example.librairie.Dao;

import com.example.librairie.Entities.Books;

import java.util.List;

public interface BookActions {

    void addBook(Books book);
    void deleteBook(int id);
    Books updateBook(int id, Books books);
    List<Books> getAllBooks();
    String borrowBook(int id);
    String returnBook(int id);
    Books getBookById(int id);

}
