package com.example.librairie.test;

import com.example.librairie.Dao.DatabaseMethods;
import com.example.librairie.Entities.Books;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class Test {

    private static Books book;
    private static DatabaseMethods methods;
    public static void main(String[] args) {
        book = new Books();
        book.setId(1);
        book.setAuthor("Arsene");
        book.setTitle("Dirty minded compain");
        book.setCopies(23);
        book.setAvail(false);
        Books newBook = book;
        methods = new DatabaseMethods();
//        adding a new book
//        methods.addBook(newBook);
//
//        updating and viewing the updated Book
//        System.out.println(
//                methods.updateBook(2,newBook)
//        );
//
//        Deleting book
//                methods.deleteBook(2);
//        listing all books
// Fetch all books
//        List<Books> booksList = methods.getAllBooks();
//
//        // Output the list of books
//        for (Books book : booksList) {
//            System.out.println(book); // Assuming Books class has overridden toString method
//        }
//        // Borrow a book
//        int bookIdToBorrow = 1;
//        String borrowResult = methods.borrowBook(bookIdToBorrow);
//
//        // Output the borrow result
//        System.out.println(borrowResult);
//          returned book
//        int bookIdToReturned = 1;
//        String ReturnResult = methods.returnBook(bookIdToReturned);
////
////        // Output the borrow result
//        System.out.println(ReturnResult);

    }

}
