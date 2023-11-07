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
        Tecnologia tecnologia;
        tecnologia = new Tecnologia();
        int escolhamain;
        do {
            Statement statement = conn.createStatement();
            String txtmain = """
                    ---CRUD---
                    O que voce deseja fazer?
                    1 - Visualizar
                    2 - Cadastrar
                    3 - Atualizar
                    4 - Excluir
                    0 - Sair""";
            System.out.println(txtmain);
            escolhamain = pega.nextInt();
            switch (escolhamain) {
                case 1://Imprime na tela
                    String txtvisualizar = """
                            Qual voce deseja visualizar?
                            1-Produtor
                            2-Tecnologia""";
                    System.out.println(txtvisualizar);
                    int escolhaexibir = pega.nextInt();
                    switch (escolhaexibir) {
                        case 1://Exibe produtores
                            System.out.println("--Lista de Produtores-- \nID - NOME - CNPJ - SITUACAO");
                            ResultSet rs = statement.executeQuery("SELECT * FROM produtor");
                            while (rs.next()) {
                                String lastName = rs.getString("nome");
                                int id = rs.getInt("id");
                                int cnpj = rs.getInt("cnpj");
                                int situacao = rs.getInt("situacao");
                                System.out.println(id + " - " + lastName +" "+ cnpj +" "+ situacao + "\n");
                            }
                            break;
                        case 2://Exibe tecnologia
                            System.out.println("--Lista de Tecnologias--");
                            ResultSet rst = statement.executeQuery("SELECT * FROM tecnologia");
                            while (rst.next()) {
                                String lastName = rst.getString("tipoQueijo");
                                String id = rst.getString("id");
                                System.out.println(id + " - " + lastName + "\n");
                            }
                            break;
                        default:
                            System.out.println("ERRO!");
                            break;
                    }
                    break;
                case 2://Adiciona
                    String txtcadastrar = """
                            O que voce deseja cadastrar?
                            1-Produtor
                            2-Tecnologia""";
                    System.out.println(txtcadastrar);
                    int ecolhacadastrar = pega.nextInt();
                    switch (ecolhacadastrar) {
                        case 1://Caso produtor
                            System.out.println("Digite o nome do produtor:");
                            pega.nextLine();//limpa o buffer do teclado
                            produtor.setNome(pega.nextLine());
                            System.out.println("Digite o CNPJ do produtor:");
                            produtor.setCNPJ(pega.nextInt());
                            System.out.println("Digite a situacao do produtor:(1-Ativo/2-Em implementacao/3-Desistente)");
                            produtor.setSituacao(pega.nextInt());
                            String query = " insert into produtor (cnpj, nome, situacao)"
                                         + " values (?, ?, ?)";
                            PreparedStatement preparedStmt = conn.prepareStatement(query);
                            preparedStmt.setInt(1, produtor.getCNPJ());
                            preparedStmt.setString(2, produtor.getNome());
                            preparedStmt.setInt(3, produtor.getSituacao());
                            preparedStmt.execute();
                            System.out.println("Adicionado com sucesso!");
                            break;

                        case 2://Caso tecnologia
                            System.out.println("Digite o queijo que a tecnologia produzirá:");
                            pega.nextLine();//limpa o buffer do teclado
                            tecnologia.setNomeQueijo(pega.nextLine());
                            System.out.println("Digite o processo utilizado:");
                            tecnologia.setProcesso(pega.nextLine());
                            System.out.println("Digite o tipo de preparo do queijo");
                            tecnologia.setPreparo(pega.nextLine());
                            String queryTecnologia = " insert into tecnologia (tipoQueijo, processoUtilizado, modoPreparo)"
                                                   + " values (?, ?, ?)";
                            PreparedStatement preparedStmtTecnologia = conn.prepareStatement(queryTecnologia);
                            preparedStmtTecnologia.setString(1, tecnologia.getNomeQueijo());
                            preparedStmtTecnologia.setString(2, tecnologia.getProcesso());
                            preparedStmtTecnologia.setString(3, tecnologia.getPreparo());
                            preparedStmtTecnologia.execute();
                            System.out.println("Adicionado com sucesso!");
                            break;

                        default:
                            System.out.println("Erro!");
                            break;
                    }
                    break;
                case 3://Atualizar
                    String txteditar = """
                            De onde voce deseja editar?
                            1-Produtor
                            2-Tecnologia""";
                    System.out.println(txteditar);
                    int escolhaeditar = pega.nextInt();
                    switch (escolhaeditar){
                        case 1://Produtor
                            System.out.println("Insira o ID do produtor que dejesa editar:");
                            int id = pega.nextInt();
                            System.out.println("Deseja editar o nome? (1-Sim/0-Nao)");
                            int edicao = pega.nextInt();
                            if(edicao==1){//Caso editar nome
                                System.out.println("Digite o novo nome:");
                                pega.nextLine();
                                String nvnome = pega.nextLine();
                                String query = "UPDATE produtor SET nome = (?) WHERE id = (?);";
                                PreparedStatement preparedStmt = conn.prepareStatement(query);
                                preparedStmt.setString(1,nvnome);
                                preparedStmt.setInt(2,id);
                                preparedStmt.execute();
                            }
                            System.out.println("Deseja editar o CNPJ? (1-Sim/0-Nao)");
                            edicao = pega.nextInt();
                            if (edicao==1){//Caso editar CNPJ
                                System.out.println("Digite o novo CNPJ:");
                                int nvcnpj = pega.nextInt();
                                String query = "UPDATE produtor SET cnpj = (?) WHERE id = (?);";
                                PreparedStatement preparedStmt = conn.prepareStatement(query);
                                preparedStmt.setInt(1,nvcnpj);
                                preparedStmt.setInt(2,id);
                                preparedStmt.execute();
                            }
                            System.out.println("Deseja editar a situacao? (1-Sim/0-Nao)");
                            edicao = pega.nextInt();
                            if (edicao==1){//Caso editar situacao
                                System.out.println("Digite a nova situacao: (1-Ativo/2-Em implementacao/3-Desistente)");
                                int nvsituacao = pega.nextInt();
                                String query = "UPDATE produtor SET nome = (?) WHERE id = (?);";
                                PreparedStatement preparedStmt = conn.prepareStatement(query);
                                preparedStmt.setInt(1,nvsituacao);
                                preparedStmt.setInt(2,id);
                                preparedStmt.execute();
                            }
                            break;
                        case 2://Tecnologia
                            System.out.println("Insira o ID do queijo que dejesa editar:");
                            int idqueijo = pega.nextInt();
                            System.out.println("Deseja editar o nome? (1-Sim/0-Nao)");
                            int edicaoqueijo = pega.nextInt();
                            if(edicaoqueijo==1){//Caso editar nome
                                System.out.println("Digite o novo nome:");
                                pega.nextLine();
                                String nvnomequeijo = pega.nextLine();
                                String queryqueijo = "UPDATE tecnologia SET nomeQueijo = (?) WHERE id = (?);";
                                PreparedStatement preparedStmt = conn.prepareStatement(queryqueijo);
                                preparedStmt.setString(1,nvnomequeijo);
                                preparedStmt.setInt(2,idqueijo);
                                preparedStmt.execute();
                            }
                            System.out.println("Deseja editar o processo? (1-Sim/0-Nao)");
                            edicaoqueijo = pega.nextInt();
                            if (edicaoqueijo==1){//Caso editar CNPJ
                                System.out.println("Digite o novo processo:");
                                String nvProcesso = pega.nextLine();
                                String query = "UPDATE tecnologia SET processo = (?) WHERE id = (?);";
                                PreparedStatement preparedStmt = conn.prepareStatement(query);
                                preparedStmt.setString(1,nvProcesso);
                                preparedStmt.setInt(2,idqueijo);
                                preparedStmt.execute();
                            }
                            System.out.println("Deseja editar o preparo? (1-Sim/0-Nao)");
                            edicaoqueijo = pega.nextInt();
                            if (edicaoqueijo==1){//Caso editar situacao
                                System.out.println("Digite o novo preparo: ");
                                int nvpreparo = pega.nextInt();
                                String query = "UPDATE tecnologia SET preparo = (?) WHERE id = (?);";
                                PreparedStatement preparedStmt = conn.prepareStatement(query);
                                preparedStmt.setInt(1,nvpreparo);
                                preparedStmt.setInt(2,idqueijo);
                                preparedStmt.execute();
                            }
                            break;
                        default:
                            System.out.println("ERRO!");
                            break;
                    }
                    break;
                case 4://Excluir
                    String txtexcluir = """
                            De onde voce deseja excluir?
                            1-Produtor
                            2-Tecnologia""";
                    System.out.println(txtexcluir);
                    int escolhaexcluir = pega.nextInt();
                    switch (escolhaexcluir) {
                        case 1://Escolha produtor
                            System.out.println("Insira o ID do produtor que deseja excluir:");
                            int idexcluir = pega.nextInt();
                            String query = " DELETE FROM produtor"
                                         + " WHERE id = (?);";
                            PreparedStatement preparedStmt = conn.prepareStatement(query);
                            preparedStmt.setInt(1,idexcluir);
                            preparedStmt.execute();
                            System.out.println("Excluido com sucesso!");
                            break;
                        case 2: // Escolha Tecnologia
                            System.out.println("Insira o ID do queijo que deseja excluir:");
                            int idexcluirqueijo = pega.nextInt();
                            String queryqueijo = " DELETE FROM produtor"
                                    + " WHERE id = (?);";
                            PreparedStatement preparedStmtq = conn.prepareStatement(queryqueijo);
                            preparedStmtq.setInt(1,idexcluirqueijo);
                            preparedStmtq.execute();
                            System.out.println("Excluido com sucesso!");
                            break;
                        default:
                            System.out.println("ERRO!");
                            break;
                    }
                    break;
                default:
                    System.out.println("Erro!");
                    break;
            }
        }while (escolhamain!=0);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}