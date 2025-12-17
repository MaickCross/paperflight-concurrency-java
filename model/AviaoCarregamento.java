/*******************************************************************************************************
* Autor: Maick Vieira Alves
* Matricula: 202310196
* Inicio: 12/03/2025
* Ultima alteracao: 21/03/2025 
* Nome: AviaoCarregamento
* Descricao: Essa classe eh a responsavel pela criacao do objeto AviaoCarregamento, utilizado na tela de
* "loading" do programa, tambem possui suas logicas de movimentacao e final do "loading"
******************************************************************************************************* */

package model;

import controller.TelaCarregamentoController;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class AviaoCarregamento extends Thread {

  private ImageView imagemAviaoCarregamento;

  private TelaCarregamentoController controller;

  private boolean continuar = true;

  /**************************************************************
   * Metodo: AviaoCarregamento
   * Funcao: construtor da classe AviaoCarregamento
   * Parametros: ImageView, controller
   * Retorno: nulo
   ***************************************************************/
  public AviaoCarregamento(ImageView imagemAviaoCarregamento, TelaCarregamentoController controller) {
    this.imagemAviaoCarregamento = imagemAviaoCarregamento;
    this.controller = controller;
  }

  /**************************************************************
   * Metodo: run
   * Funcao: inicia a thread responsavel por executar o movimento do aviao
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  @Override
  public void run() {
    while (continuar) {
      Platform.runLater(() -> moverAviao());
      try {
        sleep(40);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /**************************************************************
   * Metodo: moverAviao
   * Funcao: metodo responsavel por movimentar o aviao de acordo com suas
   * coordenada x y
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  private void moverAviao() {
    ImageView imagem = this.getImagemAviaoCarregamento(); // variavel para o ImageView

    if (imagem.getLayoutX() < 170) { // movimento reto

      imagem.setLayoutX(imagem.getLayoutX() + 10);

    } else if (imagem.getLayoutX() < 280) { // movimento diagonal inferior

      imagem.setLayoutX(imagem.getLayoutX() + 8);
      imagem.setLayoutY(imagem.getLayoutY() + 2);

    } else if (imagem.getLayoutX() < 300) { // movimento reto

      imagem.setLayoutX(imagem.getLayoutX() + 10);

    } else if (imagem.getLayoutX() < 400) { // movimento diagonal superior

      imagem.setLayoutX(imagem.getLayoutX() + 8);
      imagem.setLayoutY(imagem.getLayoutY() - 2);

    } else if (imagem.getLayoutX() < 420) { // movimento reto

      imagem.setLayoutX(imagem.getLayoutX() + 10);

    } else if (imagem.getLayoutX() < 540) { // movimento diagonal inferior

      imagem.setLayoutX(imagem.getLayoutX() + 8);
      imagem.setLayoutY(imagem.getLayoutY() + 2);

    } else if (imagem.getLayoutX() < 800) {

      imagem.setLayoutX(imagem.getLayoutX() + 10); // movimento reto

    } else if (imagem.getLayoutX() >= 800) { // Quando chega ao destino final

      continuar = false; // para a thread do aviaoCarregamento
      Platform.runLater(() -> controller.TrocarTelaSimulacao()); // chama a tela de simulacao

    }
  }

  /**************************************************************
   * Metodo: getImagemAviaoCarregamento
   * Funcao: metodo get, para receber o ImageView do Aviao
   * Parametros: nulo
   * Retorno: ImageView
   ***************************************************************/
  public ImageView getImagemAviaoCarregamento() {
    return imagemAviaoCarregamento;
  }

}
