import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //COLOCA AS INFORMAÇÕES DE CADASTRO
        String dbURL = "jdbc:mysql://localhost:3306/projetojava";
        String username = "root";
        String password = "SENHA";
        Connection conn = null;
        try {//Tenta abrir o banco
            conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
                System.out.println("BANCO DE DADOS CONECTADO COM SUCESSO!");
            }
            Scanner pega = new Scanner(System.in);
        Produtor produtor;
        produtor = new Produtor();
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
                    switch (ecolhacadastrar) {
                        case 1:
                            System.out.println("Digite o nome do produtor:");
                            produtor.setNome(pega.nextLine());
                            System.out.println("Digite o CNPJ do produtor:");
                            produtor.setCNPJ(pega.nextInt());
                            System.out.println("Digite a situacao do produtor:(1-Ativo/2-Em implementacao/3-Desistente");
                            produtor.setSituacao(pega.nextInt());
                            String query = " insert into produtor (cnpj, nome, situacao)"
                                    + " values (?, ?, ?)";
                            PreparedStatement preparedStmt = conn.prepareStatement(query);
                            preparedStmt.setInt (1, produtor.getCNPJ());
                            preparedStmt.setString (2, produtor.getNome());
                            preparedStmt.setInt (3, produtor.getSituacao());
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