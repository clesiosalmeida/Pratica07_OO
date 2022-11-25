/*
 * Classe para  consultar os dados na tabela Livro do banco de dados bdlivrariauniversitaria
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class SelectLivro {
    
    //variáveis de acesso ao banco de dados
    private final String url = "jdbc:postgresql://localhost/bdlivrariauniversitaria";
	private final String user = "postgres";
	private final String password = "123456";

    //variavel statica final
    private static final String SELECT_LIVROS_BY_PRECO_SQL = "SELECT id_isbn, id_categoria, id_editora, nm_titulo, dt_publicacao, nu_edicao, nu_volume, vl_preco FROM livro WHERE vl_preco = ?";
    private static final String SELECT_LIVROS_BY_TITULO_SQL = "SELECT id_isbn, id_categoria, id_editora, nm_titulo, dt_publicacao, nu_edicao, nu_volume, vl_preco FROM livro WHERE nm_titulo like ?";
   

    

    public void selectRecord() throws SQLException , ParseException{
        System.out.println(SELECT_LIVRO_POR_TITULO_STRING);

        // 1. Estabelecer uma conexão e 2. criar uma declaração usando um objeto de conexão 
        try (Connection connection = DriverManager.getConnection(url, user, password); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIVRO_POR_TITULO_STRING)){
           
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

    public String selectRecordByPreco(double vl_preco) throws SQLException {
        Connection conn = ConexaoPostgre.connect();
        String str = "";
        try {
          PreparedStatement pstmt = conn.prepareStatement(SELECT_LIVRO_POR_TITULO_STRING);
          pstmt.setDouble(1, vl_preco);
          ResultSet rs = pstmt.executeQuery();
          while (rs.next()) {
            String id_isbn = rs.getString("id_isbn");
            String nm_titulo = rs.getString("nm_titulo");
            String preco = rs.getString("vl_preco");
            str = "ISBN: " + id_isbn + "\nTitulo: " + nm_titulo + "\nPreço: " + preco;
          }
        } catch (SQLException e) {
          ConexaoPostgre.printSQLException(e);
        }
        conn.close();
        return str;
      }

}//fim da classe SelectLivro
