package service;

import connection.Connection;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserService {
    private Statement statement;

    public UserService() {
        try {
            statement = Connection.connect().createStatement();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void createUser(User user){
        try {
            statement.executeUpdate("INSERT INTO Usuario (nome, telefone, email) VALUES ('" + user.getName() + "', '"
                    + user.getPhone() + "', '" + user.getEmail() + "')", Statement.RETURN_GENERATED_KEYS);

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                long userId = generatedKeys.getLong(1);
                System.out.println("\nUsuário cadastrado com sucesso! ID do usuário: " + userId);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void readUser(String sql){
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if(!resultSet.next()){
                System.out.println("\nNenhum usuario foi cadastrado!");
            } else {
                do {
                    System.out.println("\nID: " + resultSet.getLong("id") +
                            "\nNome: " + resultSet.getString("nome") +
                            "\nTelefone: " + resultSet.getString("telefone") +
                            "\nEmail: " + resultSet.getString("email") +"\n");
                } while (resultSet.next());
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateUser(String sql){
        try {
            statement.executeUpdate(sql);
            System.out.println("\nUsuario alterado com sucesso!");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteUser(String id){
        try {
            statement.executeUpdate("DELETE FROM Usuario WHERE id = " + id);
            System.out.println("\nUsuario deletado com sucesso");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean userIdExists(String id) {
        String sql = "SELECT * FROM Usuario WHERE id = " + id;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet != null && resultSet.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public long getUserIdByName(String userName) {
        try {
            String sql = "SELECT id FROM Usuario WHERE nome = " + userName;
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
}
