/*******************************************************************************************************
* Autor: Maick Vieira Alves
* Matricula: 202310196
* Inicio: 12/03/2025
* Ultima alteracao: 21/03/2025 
* Nome: TrocarTelasController
* Descricao: Controller das telas de menu e sobre. Essa classe eh a responsavel por controlar os 
* botoes e controla as trocas de telas do programa
******************************************************************************************************* */

package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TrocarTelasController {
  
  @FXML Button botaoJogar;
  @FXML Button botaoSairSobre;
  @FXML Button sobre;

  @FXML ImageView menuIcone;
  @FXML ImageView menuIcone02;

  private Stage janela;
  private Scene cena;

  // Metodos relacionados ao botao jogar:

  /**************************************************************
   * Metodo: Jogar
   * Funcao: metodo ligado ao botao "botaoJogar". Troca para a tela de "loading",
   * Carrega o FXML
   * Parametros: ActionEvent
   * Retorno: void
   ***************************************************************/
  public void Jogar(ActionEvent event) throws IOException {

    Parent raiz = FXMLLoader.load(getClass().getResource("/view/telaCarregamento.fxml"));
    janela = (Stage) ((Node) event.getSource()).getScene().getWindow();
    cena = new Scene(raiz);
    janela.setScene(cena);
    janela.show();

  }

  /**************************************************************
   * Metodo: MostrarIconeAviao
   * Funcao: metodo ligado ao botao "botaoJogar", exibe um ImageView ao passar o
   * mouse encima do botao
   * Parametros: MouseEvent
   * Retorno: void
   ***************************************************************/
  @FXML
  public void MostrarIconeAviao(MouseEvent event) throws IOException {

    menuIcone.setVisible(true);

  }

  /**************************************************************
   * Metodo: RemoverIconeAviao
   * Funcao: metodo ligado ao botao "botaoJogar", remove o ImageView ao retirar o
   * mouse de cima do botao
   * Parametros: MouseEvent
   * Retorno: void
   ***************************************************************/
  public void RemoverIconeAviao(MouseEvent event) throws IOException {

    menuIcone.setVisible(false);
  }

  // Fim dos metodos

  // Metodos relacionados ao botao sobre:

  /**************************************************************
   * Metodo: VoltarTelaInicialSobre
   * Funcao: metodo ligado ao botao "botaoSairSobre", retorna para a tela
   * inicial(menu)
   * Parametros: ActionEvent
   * Retorno: void
   ***************************************************************/
  public void VoltarTelaInicialSobre(ActionEvent event) throws IOException {

    Parent raiz = FXMLLoader.load(getClass().getResource("/view/telaInicial.fxml"));
    janela = (Stage) ((Node) event.getSource()).getScene().getWindow();
    cena = new Scene(raiz);
    janela.setScene(cena);
    janela.show();
  }

  /**************************************************************
   * Metodo: MostrarIconeAviaoSobre
   * Funcao: metodo ligado ao botao "sobre", exibe um ImageView ao passar o mouse
   * encima do botao
   * Parametros: MouseEvent
   * Retorno: void
   ***************************************************************/
  @FXML
  public void MostrarIconeAviaoSobre(MouseEvent event) throws IOException {

    menuIcone02.setVisible(true); // deixa o icone visivel
  }

  /**************************************************************
   * Metodo: RemoverIconeAviaoSobre
   * Funcao: metodo ligado ao botao "sobre", remove o ImageView ao retirar o mouse
   * de cima do botao
   * Parametros: MouseEvent
   * Retorno: void
   ***************************************************************/
  public void RemoverIconeAviaoSobre(MouseEvent event) throws IOException {

    menuIcone02.setVisible(false); // deixa o icone invisivel
  }

  /**************************************************************
   * Metodo: telaSobre
   * Funcao: metodo ligado ao botao "sobre". Troca para a tela sobre, responsavel
   * por carregar o FXML
   * Parametros: MouseEvent
   * Retorno: void
   ***************************************************************/
  public void telaSobre(ActionEvent event) throws IOException {

    Parent raiz = FXMLLoader.load(getClass().getResource("/view/telaSobre.fxml"));
    janela = (Stage) ((Node) event.getSource()).getScene().getWindow();
    cena = new Scene(raiz);
    janela.setScene(cena);
    janela.show();
  }

  // Fim dos metodos

}
