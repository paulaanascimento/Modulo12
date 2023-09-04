package connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    public static java.sql.Connection connect(){
        try{
            java.sql.Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Biblioteca",
                    "postgres", "012345");
            if (connection != null){
                System.out.println("A CONEXÃO COM O BANCO DE DADOS FOI BEM SUCEDIDA!");
            }else{
                System.out.println("FALHA AO CONECTAR COM O BANCO DE DADOS!");
                System.exit(-1);
            }
            return connection;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
