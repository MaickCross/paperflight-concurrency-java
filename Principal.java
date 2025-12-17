
/*******************************************************************************************************
* Autor: Maick Vieira Alves
* Matricula: 202310196
* Inicio: 12/03/2025
* Ultima alteracao: 21/03/2025 
* Nome: Principal
* Descricao: Este projeto eh uma implementacao de uma simulacao de trens(ou avioes de papeis) utilizando
* recursos de uma GUI(janelas, botoes, areas de texto, menus, figuras, som, etc). A classe Principal
* eh a responsavel por iniciar a aplicacao javaFX e lancar o programa
********************************************************************************************************/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import controller.TelaSimulacaoController;
import controller.TrocarTelasController;
import controller.TelaCarregamentoController;

public class Principal extends Application {

  /**********************************************************************
   * Metodo: start
   * Funcao: Inicializa a aplicacao JavaFX
   * Parametros: Stage
   * Retorno: void
   **********************************************************************/
  @Override
  public void start(Stage janela) throws Exception {

    try {
      Parent raiz = FXMLLoader.load(getClass().getResource("view/telaInicial.fxml"));// Carrega o arquivo FXML da tela
                                                                                     // inicial
      Scene cena = new Scene(raiz);
      janela.setTitle("Apenas um aviao de papel voando por ai"); // Define o nome do programa
      janela.getIcons().add(new Image("/img/icone.jpg")); // Define a imagem como icone do programa
      janela.setResizable(false); // deixa o tamanho da tela fixo

      janela.setScene(cena);
      janela.show();
      janela.setOnCloseRequest(e -> {
        System.exit(0);
      }); // encerra o programa e as threads
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**************************************************************
   * Metodo: main
   * Funcao: lancar o programa
   * Parametros: args
   * Retorno: void
   ***************************************************************/
  public static void main(String[] args) {
    launch(args); // Inicia a aplicacao JavaFX
  }
}