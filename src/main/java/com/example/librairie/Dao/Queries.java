package com.example.librairie.Dao;

    public class Queries {
        public static final String INSERT_QUERY = "INSERT INTO books (title, author, copies, available)" +
                " VALUES (?, ?, ?, ?)";
        public static final String GET_ALL_BOOKS = "SELECT * FROM books";
        public static final String GET_BOOK_BY_ID = "SELECT * FROM books WHERE id = ?";
        public static final String UPDATE_BOOK_BY_ID = "UPDATE books SET title = ?, author = ?, copies = ?, available = ? WHERE id = ?";
        public static final String DELETE_BOOK_BY_ID = "DELETE FROM books WHERE id = ?";
    }
