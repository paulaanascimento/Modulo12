package service;

import connection.Connection;
import model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookService {
    private Statement statement;

    public BookService() {
        try {
            statement = Connection.connect().createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createBook(Book book) {
        try {
            statement.executeUpdate("INSERT INTO Livro (titulo, genero, id_autor) VALUES ('" + book.getTitle() + "', '"
                    + book.getGenre() + "', '" + book.getAuthorId() + "')", Statement.RETURN_GENERATED_KEYS);

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long bookCode = generatedKeys.getLong(1);
                System.out.println("\nUsuário cadastrado com sucesso! ID do livro: " + bookCode + "\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readBook(String sql) {
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                System.out.println("\nNenhum livro foi cadastrado!\n");
            } else {
                do {
                    System.out.println("\nCódigo: " + resultSet.getLong("codigo") +
                            "\nTitulo: " + resultSet.getString("titulo") +
                            "\nGenero: " + resultSet.getString("genero") +
                            "\nID do author: " + resultSet.getLong("id_autor") +
                            "\nLivro está emprestado atualmente: " + resultSet.getBoolean("esta_emprestado") + "\n");
                } while (resultSet.next());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateBook(String sql) {
        try {
            statement.executeUpdate(sql);
            System.out.println("\nLivro alterado com sucesso!\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteBook(String id) {
        try {
            statement.executeUpdate("DELETE FROM Livro WHERE id = " + id);
            System.out.println("\nLivro deletado com sucesso\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean bookCodeExists(String bookCode) {
        String sql = "SELECT * FROM Livro WHERE codigo = " + bookCode;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet != null && resultSet.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean isBookBorrowed(String bookCode) {
        try {
            String sql = "SELECT esta_emprestado FROM Livro WHERE id = " + bookCode;
            ResultSet resultSet = statement.executeQuery(sql);

            return resultSet.getBoolean("esta_emprestado");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

