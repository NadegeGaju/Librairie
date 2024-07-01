package com.example.librairie.Dao;

import com.example.librairie.Entities.Books;
import com.example.librairie.connection.DatabaseConfig;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.librairie.Dao.Queries.*;

@RequiredArgsConstructor
public class DatabaseMethods implements BookActions{

    private static final DatabaseConfig config = null;
    @Override
    public void addBook(Books book) {

        try{
            Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstm = conn.prepareStatement(INSERT_QUERY);

            pstm.setString(1,book.getTitle());
            pstm.setString(2, book.getAuthor());
            pstm.setInt(3, book.getCopies());
            pstm.setBoolean(4, book.isAvail());
            pstm.executeUpdate();
            System.out.println("Added Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteBook(int id) {
        try {
            Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DELETE_BOOK_BY_ID);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            System.out.println("book id :" + id + " Deleted Successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Books updateBook(int id, Books book) {
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement pstm = connection.prepareStatement(Queries.UPDATE_BOOK_BY_ID)) {

            pstm.setString(1, book.getTitle());
            pstm.setString(2, book.getAuthor());
            pstm.setInt(3, book.getCopies());
            pstm.setBoolean(4, book.isAvail());
            pstm.setInt(5, id);
            pstm.executeUpdate();
            System.out.println("Updated Successfully");

            // Fetch the updated book
            return getBookById(id);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Books> getAllBooks() {
        List<Books> allBooks = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement pstm = connection.prepareStatement(Queries.GET_ALL_BOOKS);
             ResultSet resultSet = pstm.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int copies = resultSet.getInt("copies");
                boolean available = resultSet.getBoolean("available");

                Books book = new Books(id, title, author, copies, available);
                allBooks.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return allBooks;
    }

    @Override
    public String borrowBook(int id) {
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement checkPstm = connection.prepareStatement(GET_BOOK_BY_ID);
             PreparedStatement updatePstm = connection.prepareStatement("UPDATE Books SET available = ? WHERE id = ?")) {

            // Check if the book exists and is available
            checkPstm.setInt(1, id);
            ResultSet resultSet = checkPstm.executeQuery();

            if (resultSet.next()) {
                boolean available = resultSet.getBoolean("available");
                if (available) {
                    // Book is available, update availability to false (borrowed)
                    updatePstm.setBoolean(1, false);
                    updatePstm.setInt(2, id);
                    updatePstm.executeUpdate();

                    return "Book borrowed successfully!";
                } else {
                    // Book is already borrowed
                    return "Book is not available for borrowing!";
                }
            } else {
                // Book does not exist
                return "Book does not exist!";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String returnBook(int id) {
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement checkPstm = connection.prepareStatement(GET_BOOK_BY_ID);
             PreparedStatement updatePstm = connection.prepareStatement("UPDATE Books SET available = ? WHERE id = ?")) {

            // Check if the book exists
            checkPstm.setInt(1, id);
            ResultSet resultSet = checkPstm.executeQuery();

            if (resultSet.next()) {
                // Book exists, update availability to true (returned)
                updatePstm.setBoolean(1, true);
                updatePstm.setInt(2, id);
                updatePstm.executeUpdate();

                return "Book returned successfully!";
            } else {
                // Book does not exist
                return "Book does not exist!";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Books getBookById(int id) {
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement pstm = connection.prepareStatement(Queries.GET_BOOK_BY_ID)) {

            pstm.setInt(1, id);
            ResultSet resultSet = pstm.executeQuery();

            if (resultSet.next()) {
                int bookId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int copies = resultSet.getInt("copies");
                boolean available = resultSet.getBoolean("available");

                return new Books(bookId, title, author, copies, available);

            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    }