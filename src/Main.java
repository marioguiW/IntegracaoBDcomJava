import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Main {
    public static void main(String[] args) {
        String dbURL = "jdbc:mysql://127.0.0.1:3306/?user=root/projetojava";
        String username = "root";
        String password = "SENHA";
        try {
            Connection conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
                System.out.println("BANCO DE DADOS CONECTADO COM SUCESSO!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Produtor produtor;
        produtor = new Produtor();
    }
}