package service;

import connection.Connection;
import model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthorService {
    private Statement statement;

    public AuthorService() {
        try {
            statement = Connection.connect().createStatement();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public AuthorService(java.sql.Connection connection) {
        try {
            statement = Connection.connect().createStatement();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void createAuthor(Author author){
        try {
            statement.executeUpdate("INSERT INTO Autor (nome) VALUES ('" + author.getName() + "')");
            System.out.println("\nAutor cadastrado com sucesso!");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void readAuthor(String sql){
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if(!resultSet.next()){
                System.out.println("\nNenhum autor foi cadastrado!\n");
            } else {
                do {
                    System.out.println("\nID: " + resultSet.getLong("id") +
                            "\nNome: " + resultSet.getString("nome") + "\n");
                } while (resultSet.next());
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateAuthor(Author author){
        try {
            statement.executeUpdate("UPDATE Autor SET nome = '" + author.getName() + "' WHERE id = " + author.getId());
            System.out.println("\nAutor alterado com sucesso!");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteAuthor(String id){
        try {
            statement.executeUpdate("DELETE FROM Autor WHERE id = " + id);
            System.out.println("\nAutor deletado com sucesso");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean authorIdExists(String id) {
        String sql = "SELECT * FROM Autor WHERE id = " + id;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet != null && resultSet.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public long getAuthorIdByName(String authorName) {
        try {
            String sql = "SELECT id FROM Autor WHERE nome = '" + authorName + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                return resultSet.getLong("id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
}
