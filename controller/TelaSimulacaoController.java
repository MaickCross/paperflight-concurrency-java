/*******************************************************************************************************
* Autor: Maick Vieira Alves
* Matricula: 202310196
* Inicio: 12/03/2025
* Ultima alteracao: 21/03/2025 
* Nome: TelaSimulacaoController
* Descricao: Controller das tela de simulacao. Responsavel por gerencias as funcoes de botoes, sliders e instanciar o objeto Avial
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
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Aviao01;
import model.Aviao02;

public class TelaSimulacaoController {

  //Imagens da UI
  @FXML ImageView aviao01;
  @FXML ImageView aviao02;
  @FXML ImageView menuIcone;
  @FXML ImageView imagemRota;
  @FXML ImageView aviaoPeterson;
  @FXML ImageView aviaoAlternancia;
  @FXML ImageView aviaoTravamento;

  //Sliders de velocidades
  @FXML Slider sliderAviao02;
  @FXML Slider sliderAviao01;

  //botoes da tela de Simulacao
  @FXML Button botao01;
  @FXML Button botao02;
  @FXML Button botao03;
  @FXML Button botao04;
  @FXML Button botaoReset;
  @FXML Button botaoRota;
  @FXML Button botaoVoltar;
  @FXML Button botaoPetterson;
  @FXML Button botaoAlternancia;
  @FXML Button botaoTravamento;

  //cria a variavel do tipo avioes
  Aviao02 av2;
  Aviao01 av1;

  //variavel para definir a posicao inicial dos avioes
  int posicaoInicial;

  //variaveis para resetar as telas do fxml
  private Scene cena;
  private Stage janela;

  // declaracao das variaveis que serao utilizada na Solucao de Peterson
  public static volatile int turnRC01;
  public static volatile int turnRC02;
  public static volatile boolean[] interestedRC01 = { false, false };
  public static volatile boolean[] interestedRC02 = { false, false };

  // declaracao das variaveis que serao utilizadas na Solucao Estrita Alternancia
  public static volatile int vezRC01 = 0;
  public static volatile int vezRC02 = 0;

  // declaracao das variaveis que serao utilizadas na Solucao Variavel Travamento
  public static volatile int travaRC01 = 0;
  public static volatile int travaRC02 = 0;

  //variavel que define qual solucao esta ativa no momento
  public static volatile int solucao = 1;

  /**************************************************************
   * Metodo: initialize
   * Funcao: metodo que sera executado logo apos carregar o arquivo FXML,
   * responsavel por instanciar os Objetos
   * Aviao e iniciar suas Threads
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
  @FXML
  public void initialize() {
    // aviao01
    av1 = new Aviao01(aviao01, sliderAviao01, posicaoInicial = 2);
    av1.start();

    // aviao02
    av2 = new Aviao02(aviao02, sliderAviao02, posicaoInicial = 1);
    av2.start();

    // metodo para mudar o icone das solucoes na UI
    switch (solucao) {

      case 1: //Solucao de Peterson ativa

        aviaoPeterson.setVisible(true);
        aviaoAlternancia.setVisible(false);
        aviaoTravamento.setVisible(false);

        break;

      case 2: //Solucao de Estrita Alternancia ativa

        aviaoPeterson.setVisible(false);
        aviaoAlternancia.setVisible(true);
        aviaoTravamento.setVisible(false);

        break;

      case 3: //Solucao da Variavel de Travamento ativa

      aviaoPeterson.setVisible(false);
      aviaoAlternancia.setVisible(false);
      aviaoTravamento.setVisible(true);
    }

  }

  // Botoes para posicionar o aviao:

  /**************************************************************
   * Metodo: Posicao01
   * Funcao: metodo ligado ao botao "botao01", posiciona ambos os avioes embaixo
   * da tela
   * e atribui um valor que sera conferido pela thread para a chamada de seu
   * respectivo metodo
   * Parametros: ActionEvent
   * Retorno: void
   ***************************************************************/
  public void Posicao01(ActionEvent event) throws IOException {

    av1.setPosicaoAviao(2);
    av2.setPosicaoAviao(1);

    aviao01.setLayoutX(71);
    aviao01.setLayoutY(681);

    aviao02.setLayoutX(141);
    aviao02.setLayoutY(681);

  }

  /**************************************************************
   * Metodo: Posicao02
   * Funcao: metodo ligado ao botao "botao02", posiciona ambos os avioes enCIM da
   * tela
   * e atribui um valor que sera conferido pela thread para a chamada de seu
   * respectivo metodo
   * Parametros: ActionEvent
   * Retorno: void
   ***************************************************************/
  public void Posicao02(ActionEvent event) throws IOException {

    av1.setPosicaoAviao(4);
    av2.setPosicaoAviao(3);

    aviao01.setLayoutX(71);
    aviao01.setLayoutY(-70);

    aviao02.setLayoutX(141);
    aviao02.setLayoutY(-70);
  }

  /**************************************************************
   * Metodo: Posicao03
   * Funcao: metodo ligado ao botao "botao03", posiciona o aviao01 encima e o
   * aviao 02 embaixo da tela
   * e atribui um valor que sera conferido pela thread para a chamada de seu
   * respectivo metodo
   * Parametros: ActionEvent
   * Retorno: void
   ***************************************************************/
  public void Posicao03(ActionEvent event) throws IOException {

    av1.setPosicaoAviao(4);
    av2.setPosicaoAviao(1);

    aviao01.setLayoutX(71);
    aviao01.setLayoutY(-70);

    aviao02.setLayoutX(141);
    aviao02.setLayoutY(681);

  }

  /**************************************************************
   * Metodo: Posicao04
   * Funcao: metodo ligado ao botao "botao04", posiciona o aviao01 embaixo e o
   * aviao 02 encima da tela
   * e atribui um valor que sera conferido pela thread para a chamada de seu
   * respectivo metodo
   * Parametros: ActionEvent
   * Retorno: void
   ***************************************************************/
  public void Posicao04(ActionEvent event) throws IOException {

    av1.setPosicaoAviao(2);
    av2.setPosicaoAviao(3);

    aviao01.setLayoutX(71);
    aviao01.setLayoutY(681);

    aviao02.setLayoutX(141);
    aviao02.setLayoutY(-70);

  }

  // Fim dos botoes para posicionar o aviao

  /**************************************************************
   * Metodo: Resetar
   * Funcao: metodo ligado ao botao "botaoReset", reinicia a tela carregando o
   * FXML novamente
   * Parametros: ActionEvent
   * Retorno: void
   ***************************************************************/
  public void Resetar(ActionEvent event) throws IOException {

    // resetar os valores das variaveis das solucoes:

    av1.pararThread();
    av2.pararThread();

    resetarVariaveis();

    Parent raiz = FXMLLoader.load(getClass().getResource("/view/telaSimulacao.fxml"));
    janela = (Stage) ((Node) event.getSource()).getScene().getWindow();
    cena = new Scene(raiz);
    janela.setScene(cena);
    janela.show();

  }

  /**************************************************************
   * Metodo: Voltar
   * Funcao: metodo ligado ao botao "botaoVoltar", volta para o menu inicial
   * carregando o FXML
   * Parametros: ActionEvent
   * Retorno: void
   ***************************************************************/
  public void Voltar(ActionEvent event) throws IOException {

    // resetar os valores das variaveis das solucoes:

    av1.pararThread();
    av2.pararThread();

    resetarVariaveis();

    Parent raiz = FXMLLoader.load(getClass().getResource("/view/telaInicial.fxml"));
    janela = (Stage) ((Node) event.getSource()).getScene().getWindow();
    cena = new Scene(raiz);
    janela.setScene(cena);
    janela.show();

  }

  /**************************************************************
   * Metodo: ExibirRota
   * Funcao: liga/desliga a imagem do "trilho"
   * Parametros: ActionEvent
   * Retorno: void
   ***************************************************************/

  @FXML
  public void ExibirRota(ActionEvent event) throws IOException {
    if (imagemRota.isVisible()) {
      imagemRota.setVisible(false); // Torna a imagem invisível
    } else {
      imagemRota.setVisible(true); // Torna a imagem visível
    }
  }

  //Inicio dos metodos relacionados a troca de solucao

  /**************************************************************
   * Metodo: SolucaoPetterson
   * Funcao: metodo ligado ao botao botaoPetterson, define a solucao para
   * Petterson
   * Parametros: ActionEvent
   * Retorno: void
   ***************************************************************/

  @FXML
  public void SolucaoPetterson(ActionEvent event) throws IOException {

    this.solucao = 1;

    // para a thread e reinicia o programa
    av1.pararThread();
    av2.pararThread();


    resetarVariaveis();
    // reseta a tela com a solucao de peterson
    resetarTelaAnimacao(event);

  }

  /**************************************************************
   * Metodo: solucaoEstritaAlternancia
   * Funcao: metodo ligado ao botao botaoAlternancia, define a solucao para
   * Estrita Alternancia
   * Parametros: ActionEvent
   * Retorno: void
   ***************************************************************/

  @FXML
  public void solucaoEstritaAlternancia(ActionEvent event) throws IOException {

    // av1.setSolucao(2);
    // av2.setSolucao(2);
    this.solucao = 2;

    // para a thread e reinicia o programa
    av1.pararThread();
    av2.pararThread();

    resetarVariaveis();
    /// reseta a tela de animacao e muda para o metodo estrita alternancia
    resetarTelaAnimacao(event);

  }

  /**************************************************************
   * Metodo: solucaoVariavelTravamento
   * Funcao: metodo ligado ao botao botaoTravamento, define a solucao para
   * Variavel Travamento
   * Parametros: ActionEvent
   * Retorno: void
   ***************************************************************/

  @FXML
  public void solucaoVariavelTravamento(ActionEvent event) throws IOException {

    this.solucao = 3;

    // para a thread e reinicia o programa
    av1.pararThread();
    av2.pararThread();

    resetarVariaveis();

    // reseta a tela de animacao e troca para a solucao variavel travamento
    resetarTelaAnimacao(event);

  }

  //Fim dos metodos de solucoes

  /**************************************************************
   * Metodo: resetarTelaAnimacao
   * Funcao: Reseta a cena da tela de simulacaoo
   * Parametros: ActionEvent
   * Retorno: void
   ***************************************************************/
  public void resetarTelaAnimacao(ActionEvent event) throws IOException {

    Parent raiz = FXMLLoader.load(getClass().getResource("/view/telaSimulacao.fxml"));
    janela = (Stage) ((Node) event.getSource()).getScene().getWindow();
    cena = new Scene(raiz);
    janela.setScene(cena);
    janela.show();
  }

    /**************************************************************
   * Metodo: resetarVariaveis
   * Funcao: Reseta os valores das variaveis dos metodos de solucoes
   * Parametros: null
   * Retorno: void
   ***************************************************************/
  public void resetarVariaveis() {

    turnRC01 = 0;
    turnRC02 = 0;
    interestedRC01[0] = false;
    interestedRC01[1] = false;
    interestedRC02[0] = false;
    interestedRC02[1] = false;
    vezRC01 = 0;
    vezRC02 = 0;
    travaRC01 = 0;
    travaRC02 = 0;
  }

}
