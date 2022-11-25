/*
 * Classe para inserir dados na tabela Livro do banco de dados bdlivrariauniversitaria
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class InsertLivro {
    
    //variáveis de acesso ao banco de dados
    private final String url = "jdbc:postgresql://localhost/bdlivrariauniversitaria";
	private final String user = "postgres";
	private final String password = "123456";

    //variavel statica final
    private static final String INSERT_LIVROS_SQL = "INSERT INTO Livro" +
    "  (id_isbn, nm_titulo,dt_publicacao, vl_preco) VALUES " +
            " (?, ?, ?, ?);";


    public static void main(String[] args) throws SQLException, ParseException{
        InsertLivro insertLivro = new InsertLivro(); 
        insertLivro.insertRecord();

    }//Fim do main

    public void insertRecord() throws SQLException , ParseException{
        System.out.println(INSERT_LIVROS_SQL);

        // 1. Estabelecer uma conexão e 2. criar uma declaração usando um objeto de conexão 
        try (Connection connection = DriverManager.getConnection(url, user, password); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LIVROS_SQL)){
           
            String dataLivro = "01-01-2001";
            Date formataData = new SimpleDateFormat("dd-MM-yyyy").parse(dataLivro);
            java.sql.Date data_sql = converteData(formataData);
            

            preparedStatement.setString(1, "0000000020");
            preparedStatement.setString(2, "O Inferno de Dante");
            preparedStatement.setDate(3,data_sql);
            preparedStatement.setFloat(4, 80.90f);
          
            System.out.println(preparedStatement);

            //3. Dar update query na declaração

            preparedStatement.executeUpdate();
        } catch(SQLException e){

            printSQLException(e); 
        }
        //4. A instrução try-with-resource fechará automaticamente a conexão.

    }//fim do metodo inserRecord()

    private static java.sql.Date converteData(java.util.Date data) {
        java.sql.Date minhaData = new java.sql.Date(data.getTime());
        return minhaData;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}//fim da classe InsertLivro
