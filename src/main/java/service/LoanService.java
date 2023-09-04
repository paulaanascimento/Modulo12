package service;

import connection.Connection;
import model.Loan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoanService {
    private Statement statement;

    public LoanService() {
        try {
            statement = Connection.connect().createStatement();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void createLoan(Loan loan){
        try {
            statement.executeUpdate("INSERT INTO Emprestimo (data_inicio, data_devolucao, codigo_livro, id_usuario) VALUES ('" + loan.getStartDate() + "', '"
                    + loan.getReturnDate() + "', '" + loan.getBookCode() + "', '" + loan.getUserId() + "')");
            System.out.println("\nEmpréstimo cadastrado com sucesso!\n");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void readLoan(String sql){
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if(!resultSet.next()){
                System.out.println("\nNenhum empréstimo foi cadastrado!\n");
            } else {
                do {
                    System.out.println("\nID: " + resultSet.getLong("id") +
                            "\nData de Início: " + resultSet.getDate("data_inicio") +
                            "\nData de Devolução: " + resultSet.getDate("data_devolucao" +
                            "\nCódigo do Livro: " + resultSet.getString("codigo_livro" +
                            "\nID do Usuário: " + resultSet.getString("id_usuario"))));
                } while (resultSet.next());
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateLoan(String sql){
        try {
            statement.executeUpdate(sql);
            System.out.println("\nEmprestimo alterado com sucesso!\n");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteLoan(String id){
        try {
            statement.executeUpdate("DELETE FROM Emprestimo WHERE id = " + id);
            System.out.println("\nEmprestimo deletado com sucesso\n");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean loanIdExists(String id) {
        String sql = "SELECT * FROM Emprestimo WHERE id = " + id;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet != null && resultSet.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
