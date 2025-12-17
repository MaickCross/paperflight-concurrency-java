/*******************************************************************************************************
* Autor: Maick Vieira Alves
* Matricula: 202310196
* Inicio: 12/03/2025
* Ultima alteracao: 21/03/2025 
* Nome: Aviao
* Descricao: Essa classe eh a responsavel pela criacao do objeto Aviao, utilizado na tela de
* simulacao do programa, tambem possui seus metodos utilizados no movimento do objeto aviao
******************************************************************************************************* */

package model;

import controller.TelaSimulacaoController;
import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class Aviao01 extends Thread {
  private ImageView imagemAviao;
  private double velocidadeAviao;
  private int posicaoAviao;
  private Slider sliderAviao;

  private int processo = 0; //define o Aviao01 como o processo 0

  private boolean dentroRC01 = false; // variavel para sinalizar se o aviao esta dentro da regiao critica 01
  private boolean dentroRC02 = false; // variavel para sinalizar se o aviao esta dentro da regiao critica 02

  private volatile boolean continuar = true; //para o loop da thread

  /**************************************************************
   * Metodo: Aviao
   * Funcao: construtor da classe Aviao
   * Parametros: ImageView, SLider, int
   * Retorno:
   ***************************************************************/
  public Aviao01(ImageView imagemAviao, Slider sliderAviao, int posicaoAviao) {
    this.imagemAviao = imagemAviao;
    this.sliderAviao = sliderAviao;
    this.posicaoAviao = posicaoAviao;
    this.processo = 0;

  }

  /**************************************************************
   * Metodo: run
   * Funcao: inicia a thread responsavel por executar em loop, as logicas de
   * movimento do Aviao
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  @Override
  public void run() {
    while (continuar) { // mudar o true para variavel booleana dps

      MovimentarAviao(posicaoAviao); // movimenta o aviao conforme a posicao definida pelo jogador na UI

      try {
        sleep(30); // responsavel pela animacao
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /**************************************************************
   * Metodo: MovimentarAviao
   * Funcao: Responsavel pela movimentação do aviao de acordo com as posicoes de
   * inicio, o metodo chama o metodo relacionada a cada posicao de acordo com as
   * escolhas do usuario
   * Parametros: int
   * Retorno: void
   ***************************************************************/
  public void MovimentarAviao(int posicionarAviao) {

    switch (posicionarAviao) {

      case 2: // posiciona o aviao 01 na posicao inferior esquerda

        moverAviao02(); // chama o metodo responsavel por movimentar o aviao
        break;

      case 4: // posiciona o aviao 01 na posicao superior esquerda

        moverAviao04(); // chama o metodo responsavel por movimentar o aviao
        break;
    }
  }

  // metodos relacionados ao movimento dos Avioes:

  /**************************************************************
   * Metodo: moverAviao02
   * Funcao: responsavel pela animacao do aviao que inicia na posicao inferior
   * esquerda
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  private void moverAviao02() {

    ImageView aviao = this.getImagemAviao(); // variavel para o ImageView
    velocidadeAviao = sliderAviao.getValue(); // slider que controla a velocidade

    if (aviao.getLayoutY() > 530) { // Trilho duplo horizontal 03
      Platform.runLater(() -> aviao.setRotate(0));
      Platform.runLater(() -> aviao.setLayoutY(aviao.getLayoutY() - velocidadeAviao));

    }// -------------------------- Regiao Critica 02 -------------------------- 
    else if (aviao.getLayoutY() > 490) { // Trilho duplo diagonal -> 03
      Platform.runLater(() -> aviao.setRotate(45));

      if (!dentroRC02) { // garante que so ira chamar o metodo EntrarRegiaoCritica02 uma unica vez
        EntrarRegiaoCritica02(TelaSimulacaoController.solucao); // entrou na regiao critica 02
        dentroRC02 = true;
      }

      Platform.runLater(() -> aviao.setLayoutY(aviao.getLayoutY() - velocidadeAviao));
      Platform.runLater(() -> aviao.setLayoutX(aviao.getLayoutX() + velocidadeAviao));

    } 
    else if (aviao.getLayoutY() > 400) { // Trilho simples 02
      Platform.runLater(() -> aviao.setRotate(0));
      Platform.runLater(() -> aviao.setLayoutY(aviao.getLayoutY() - velocidadeAviao));

    } // -------------------------- Fim da Regiao Critica 02 -------------------
    else if (aviao.getLayoutY() > 360) { // Trilho duplo Diagonal <- 02

      if (dentroRC02) { // garante que so ira chamar o metodo SairRegiaoCritica02 uma unica vez
        SairRegiaoCritica02(TelaSimulacaoController.solucao); // saiu da regiao critica 02
        dentroRC02 = false;
      }

      Platform.runLater(() -> aviao.setRotate(-45));
      Platform.runLater(() -> aviao.setLayoutY(aviao.getLayoutY() - velocidadeAviao));
      Platform.runLater(() -> aviao.setLayoutX(aviao.getLayoutX() - velocidadeAviao));

    } else if (aviao.getLayoutY() > 250) { // Trilho duplo horizontal 02
      Platform.runLater(() -> aviao.setRotate(0));
      Platform.runLater(() -> aviao.setLayoutY(aviao.getLayoutY() - velocidadeAviao));

    }// -------------------------- Regiao Critica 01 -------------------------- 
    else if (aviao.getLayoutY() > 210) { // Trilho duplo diagonal -> 02
      Platform.runLater(() -> aviao.setRotate(45));

      if (!dentroRC01) { // garante que so ira chamar o metodo EntrarRegiaoCritica01 uma unica vez
        EntrarRegiaoCritica01(TelaSimulacaoController.solucao); // Entrou na Regiao Critica 01
        dentroRC01 = true;
      }

      Platform.runLater(() -> aviao.setLayoutY(aviao.getLayoutY() - velocidadeAviao));
      Platform.runLater(() -> aviao.setLayoutX(aviao.getLayoutX() + velocidadeAviao));

    } 
    else if (aviao.getLayoutY() > 110) { // Trilho simples 01
      Platform.runLater(() -> aviao.setRotate(0));
      Platform.runLater(() -> aviao.setLayoutY(aviao.getLayoutY() - velocidadeAviao));

    } // -------------------------- Fim da Regiao Critica 01 -------------------
    else if (aviao.getLayoutY() > 70) { // Trilho duplo diagonal <- 01

      if (dentroRC01) { // garante que so ira chamar o metodo EntrarRegiaoCritica01 uma unica vez
        SairRegiaoCritica01(TelaSimulacaoController.solucao); // Saiu da Regiao Critica 01
        dentroRC01 = false;
      }

      Platform.runLater(() -> aviao.setRotate(-45));
      Platform.runLater(() -> aviao.setLayoutY(aviao.getLayoutY() - velocidadeAviao));
      Platform.runLater(() -> aviao.setLayoutX(aviao.getLayoutX() - velocidadeAviao));

    } else if (aviao.getLayoutY() >= -50) { // Trilho duplo horizontal 01
      Platform.runLater(() -> aviao.setRotate(0));
      Platform.runLater(() -> aviao.setLayoutY(aviao.getLayoutY() - velocidadeAviao));

    } else if (aviao.getLayoutY() <= -50) { // Final do Trajeto
      Platform.runLater(() -> aviao.setRotate(0));
      Platform.runLater(() -> aviao.setLayoutX(71)); // Posiciona o aviao novamente na coordenada inicial X
      Platform.runLater(() -> aviao.setLayoutY(681)); // Posiciona o aviao novamente na coordenada inicial Y
    }
  }

  /**************************************************************
   * Metodo: moverAviao04
   * Funcao: responsavel pela animacao do aviao que inicia na posicao superior
   * esquerda
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  private void moverAviao04() {

    ImageView aviao = this.getImagemAviao(); // variavel para o ImageView
    velocidadeAviao = sliderAviao.getValue(); // slider que controla a velocidade

    if (aviao.getLayoutY() < 80) { // Trilho duplo horizontal 01
      Platform.runLater(() -> aviao.setRotate(180));
      Platform.runLater(() -> aviao.setLayoutY(aviao.getLayoutY() + velocidadeAviao));

    }// -------------------------- Regiao Critica 01 --------------------------
    else if (aviao.getLayoutY() < 110) { // Trilho duplo diagonal -> 01
      Platform.runLater(() -> aviao.setRotate(135));

      if (!dentroRC01) { // garante que so ira chamar o metodo EntrarRegiaoCritica01 uma unica vez
        EntrarRegiaoCritica01(TelaSimulacaoController.solucao); // Entrou na Regiao Critica 01
        dentroRC01 = true;
      }

      Platform.runLater(() -> aviao.setLayoutY(aviao.getLayoutY() + velocidadeAviao));
      Platform.runLater(() -> aviao.setLayoutX(aviao.getLayoutX() + velocidadeAviao));

    } 
    else if (aviao.getLayoutY() < 210) { // Trilho simples 01
      Platform.runLater(() -> aviao.setRotate(180));
      Platform.runLater(() -> aviao.setLayoutY(aviao.getLayoutY() + velocidadeAviao));

    } // -------------------------- Fim da Regiao Critica 01 -------------------
    else if (aviao.getLayoutY() < 240) { // Trilho duplo Diagonal <- 02

      if (dentroRC01) { // garante que so ira chamar o metodo EntrarRegiaoCritica01 uma unica vez
        SairRegiaoCritica01(TelaSimulacaoController.solucao); // Saiu da Regiao Critica 01
        dentroRC01 = false;
      }
      Platform.runLater(() -> aviao.setRotate(225));
      Platform.runLater(() -> aviao.setLayoutY(aviao.getLayoutY() + velocidadeAviao));
      Platform.runLater(() -> aviao.setLayoutX(aviao.getLayoutX() - velocidadeAviao));

    } else if (aviao.getLayoutY() < 360) { // Trilho duplo horizontal 02
      Platform.runLater(() -> aviao.setRotate(180));
      Platform.runLater(() -> aviao.setLayoutY(aviao.getLayoutY() + velocidadeAviao));

    }// -------------------------- Regiao Critica 02 -------------------------- 
    else if (aviao.getLayoutY() < 390) { // Trilho duplo diagonal -> 02
      Platform.runLater(() -> aviao.setRotate(135));

      if (!dentroRC02) { // garante que so ira chamar o metodo EntrarRegiaoCritica02 uma unica vez
        EntrarRegiaoCritica02(TelaSimulacaoController.solucao); // entrou na regiao critica 02
        dentroRC02 = true;
      }

      Platform.runLater(() -> aviao.setLayoutY(aviao.getLayoutY() + velocidadeAviao));
      Platform.runLater(() -> aviao.setLayoutX(aviao.getLayoutX() + velocidadeAviao));

    } 
    else if (aviao.getLayoutY() < 500) { // Trilho simples 02
      Platform.runLater(() -> aviao.setRotate(180));
      Platform.runLater(() -> aviao.setLayoutY(aviao.getLayoutY() + velocidadeAviao));

    } // -------------------------- Fim da Regiao Critica 01 -------------------
    else if (aviao.getLayoutY() < 530) { // Trilho duplo diagonal <- 03

      if (dentroRC02) { // garante que so ira chamar o metodo SairRegiaoCritica02 uma unica vez
        SairRegiaoCritica02(TelaSimulacaoController.solucao); // saiu da regiao critica 02
        dentroRC02 = false;
      }

      Platform.runLater(() -> aviao.setRotate(225));
      Platform.runLater(() -> aviao.setLayoutY(aviao.getLayoutY() + velocidadeAviao));
      Platform.runLater(() -> aviao.setLayoutX(aviao.getLayoutX() - velocidadeAviao));

    } else if (aviao.getLayoutY() <= 750) { // Trilho duplo horizontal 03
      Platform.runLater(() -> aviao.setRotate(180));
      Platform.runLater(() -> aviao.setLayoutY(aviao.getLayoutY() + velocidadeAviao));

    } else if (aviao.getLayoutY() >= 750) { // Final do Trajeto
      Platform.runLater(() -> aviao.setRotate(180));
      Platform.runLater(() -> aviao.setLayoutX(71)); // Posiciona o aviao novamente na coordenada inicial X
      Platform.runLater(() -> aviao.setLayoutY(-70)); // Posiciona o aviao novamente na coordenada inicial Y
    }
  }

  // Fim dos metodos relacionados ao movimento dos avioes

  // metodos para resolver a concorrencia:

  /**************************************************************
   * Metodo: EntrarRegiaoCritica01
   * Funcao: responsavel por sinalizar quando um aviao entra na regiao critica 01,
   * e
   * resolve a concorrencia de acordo com o metodo selecionado pelo usuario na UI
   * Parametros: int
   * Retorno: void
   ***************************************************************/
  public void EntrarRegiaoCritica01(int solucao) {

    switch (solucao) {

      case 1: // Solucao de Petterson

        int other = 1 - processo;
        TelaSimulacaoController.interestedRC01[processo] = true;
        TelaSimulacaoController.turnRC01 = processo;

        while (TelaSimulacaoController.turnRC01 == processo && TelaSimulacaoController.interestedRC01[other] == true && TelaSimulacaoController.solucao == 1) {
          //System.out.println("Aviao01 esta esperando: Petterson");
        }

        break;

      case 2: // Estrita Alternancia

        while (TelaSimulacaoController.vezRC01 != 0 && TelaSimulacaoController.solucao == 2) {
          //System.out.println("Aviao01 esta esperando: Alternancia");
        }

        break;

      case 3: // Variavel Travamento

        while (TelaSimulacaoController.travaRC01 != 0 && TelaSimulacaoController.solucao == 3) {
          //System.out.println("Aviao01 esta esperando: Variavel Travamento");
        }

        TelaSimulacaoController.travaRC01 = 1;

        break;

    }

  }

  /**************************************************************
   * Metodo: SairRegiaoCritica01
   * Funcao: responsavel por sinalizar quando um aviao sair da regiao critica 01,
   * e
   * resolve a concorrencia de acordo com o metodo selecionado pelo usuario na UI
   * Parametros: int
   * Retorno: void
   ***************************************************************/
  public void SairRegiaoCritica01(int solucao) {

    switch (solucao) {

      case 1: // Solucao de Petterson

        TelaSimulacaoController.interestedRC01[processo] = false;

        break;

      case 2: // Estrita Alternancia

        TelaSimulacaoController.vezRC01 = 1;

        break;

      case 3: // Variavel Travamento

        TelaSimulacaoController.travaRC01 = 0;

        break;

    }
  }

  /**************************************************************
   * Metodo: EntrarRegiaoCritica02
   * Funcao: responsavel por sinalizar quando um aviao entra na regiao critica 02,
   * e
   * resolve a concorrencia de acordo com o metodo selecionado pelo usuario na UI
   * Parametros: int
   * Retorno: void
   ***************************************************************/
  public void EntrarRegiaoCritica02(int solucao) {

    switch (solucao) {

      case 1: // Solucao de Petterson

        int other = 1 - processo;
        TelaSimulacaoController.interestedRC02[processo] = true;
        TelaSimulacaoController.turnRC02 = processo;

        while (TelaSimulacaoController.turnRC02 == processo && TelaSimulacaoController.interestedRC02[other] == true && TelaSimulacaoController.solucao == 1) {

        }

        break;

      case 2: //Estrita Alternancia

        while (TelaSimulacaoController.vezRC02 != 0 && TelaSimulacaoController.solucao == 2) {
          
        }

        break;

      case 3: // Variavel Travamento

        while (TelaSimulacaoController.travaRC02 != 0 && TelaSimulacaoController.solucao == 3) {
          
        }

        TelaSimulacaoController.travaRC02 = 1;

        break;

    }

  }

  /**************************************************************
   * Metodo: SairRegiaoCritica02
   * Funcao: responsavel por sinalizar quando um aviao sair da regiao critica 02,
   * e
   * resolve a concorrencia de acordo com o metodo selecionado pelo usuario na UI
   * Parametros: int
   * Retorno: void
   ***************************************************************/
  public void SairRegiaoCritica02(int solucao) {

    switch (solucao) {

      case 1: // Solucao de Petterson

        TelaSimulacaoController.interestedRC02[processo] = false;

        break;

      case 2:

        TelaSimulacaoController.vezRC02 = 1;

        break;

      case 3: // Variavel Travamento

        TelaSimulacaoController.travaRC02 = 0;

        break;

    }
  }

  // Fim dos metodos de concorrencia

  // Getters e Setters:

  /**************************************************************
   * Metodo: getImagemAviao
   * Funcao: metodo get parareceber a imagem do aviao
   * Parametros: nulo
   * Retorno: ImageView
   ***************************************************************/
  public ImageView getImagemAviao() {
    return imagemAviao;
  }

  /**************************************************************
   * Metodo: setPosicaoAviao
   * Funcao: metodo set para definir a posicao do Aviao
   * Parametros: int
   * Retorno: void
   ***************************************************************/
  public void setPosicaoAviao(int posicaoAviao) {
    this.posicaoAviao = posicaoAviao;

  }


  /**************************************************************
   * Metodo: pararThread
   * Funcao: metodo set para parar a thread 
   * Parametros: null
   * Retorno: void
   ***************************************************************/
  public void pararThread(){
    this.continuar = false;
    this.interrupt(); //garante que a thread seja parada
  }
  // Fim dos Getters e Setters

}