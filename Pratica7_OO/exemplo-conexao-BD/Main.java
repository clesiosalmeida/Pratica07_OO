package br.com.pratica7;

import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JOptionPane;

public class Main {
  public static void main(String[] args) throws SQLException, ParseException {
    int opcao = 0;

    while (opcao != 5) {
      opcao = Integer.parseInt(JOptionPane.showInputDialog(null,
          "<1> Cadastrar Livro \n<2> Pesquisar Livro por Preço  \n<3> Pesquisar Livro por Título  \n<4> Excluir Livro  \n<5> Sair "));

      switch (opcao) {
        case 1:
          InsereLivro iLivro = new InsereLivro();
          iLivro.insertRecord();
          break;
        case 2:
          double vl_preco = Double
              .parseDouble(JOptionPane.showInputDialog(null, "Qual o valor do livro que deseja pesquisar?"));
          PesquisaLivro pesquisaLivroPorPreco = new PesquisaLivro();
          JOptionPane.showMessageDialog(null, pesquisaLivroPorPreco.selectRecordByPreco(vl_preco));
          break;
        case 3:
          String nm_titulo = JOptionPane.showInputDialog(null, "Informe o nome do titulo que deseja pesquisar");
          PesquisaLivro pesquisaLivroPorTitulo = new PesquisaLivro();
          JOptionPane.showMessageDialog(null, pesquisaLivroPorTitulo.selectRecordByTitulo(nm_titulo));
          break;

        case 4:
          String id_isbm = JOptionPane.showInputDialog(null, "Informe o código ISBM do livro");
          ExcluirLivro eLivro = new ExcluirLivro();
          JOptionPane.showMessageDialog(null, "Quantidade de livros localizados e removidos: " + eLivro.deleteRecord(id_isbm));
          break;
        default:
          opcao = 5;
          break;
      }
    }
  }
}