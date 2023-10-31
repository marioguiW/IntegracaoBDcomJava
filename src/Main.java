import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String dbURL = "jdbc:mysql://localhost:3306/projetojava";
        String username = "root";
        String password = "MYindustri@l123QL";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
                System.out.println("BANCO DE DADOS CONECTADO COM SUCESSO!");
            }
            Scanner pega = new Scanner(System.in);
//        Produtor produtor;
//        produtor = new Produtor();
            Statement statement = conn.createStatement();
            String txtmain = """
                ---CRUD---
                O que voce deseja fazer?
                1-Cadastrar""";
            System.out.println(txtmain);
            int escolhamain = pega.nextInt();
            switch (escolhamain) {
                case 1:
                    String txtcadastrar = """
                        O que voce deseja cadastrar?
                        1-Produtor""";
                    System.out.println(txtcadastrar);
                    int ecolhacadastrar = pega.nextInt();
                    switch (escolhamain) {
                        case 1:
                            System.out.println("Digite o nome do produtor:");
                            String nome = pega.nextLine();
                            System.out.println("Digite o CNPJ do produtor:");
                            int cnpj = pega.nextInt();
                            System.out.println("Digite a situacao do produtor:(1-Ativo/2-Em implementacao/3-Desistente");
                            int situacao = pega.nextInt();
                            String query = " insert into produtor (cnpj, nome, situacao)"
                                    + " values (?, ?, ?)";
                            PreparedStatement preparedStmt = conn.prepareStatement(query);
                            preparedStmt.setInt (1, cnpj);
                            preparedStmt.setString (2, nome);
                            preparedStmt.setInt (3, situacao);
                            preparedStmt.execute();
                            conn.close();
                            break;
                    }
                    break;
                default:
                    System.out.println("Erro!");
                    break;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}