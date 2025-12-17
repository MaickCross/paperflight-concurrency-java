/*******************************************************************************************************
* Autor: Maick Vieira Alves
* Matricula: 202310196
* Inicio: 12/03/2025
* Ultima alteracao: 21/03/2025 
* Nome: TelaCarregamentoController
* Descricao: Controller da telaCarregamento, responsavel pela breve animacao de "loading"
******************************************************************************************************* */

package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.AviaoCarregamento;

public class TelaCarregamentoController {

    @FXML ImageView aviaoCarregamento;

    private AviaoCarregamento aviao;

    /**************************************************************
   * Metodo: initialize
   * Funcao: metodo que sera executado logo apos carregar o arquivo FXML, responsavel por instanciar o Objeto
   * AviaoCarregamento e iniciar sua Thread
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
    @FXML
    public void initialize() {

        aviao = new AviaoCarregamento(aviaoCarregamento, this);
        aviao.start();

    }

    /**************************************************************
   * Metodo: TrocarTelaSimulacao
   * Funcao: trocar a tela para a tela de simulacao, carregando seu FXML, essa funcao eh
   * chamada na classe AviaoCarregamento
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
    @FXML
    public void TrocarTelaSimulacao() {
        try {
            Parent raiz = FXMLLoader.load(getClass().getResource("/view/telaSimulacao.fxml")); // Carregar a nova tela
            Stage janelaAtual = (Stage) aviaoCarregamento.getScene().getWindow(); // Obter a janela atual (Stage)
            Scene novaCena = new Scene(raiz);// Criar a nova cena e atribui-la a janela
            janelaAtual.setScene(novaCena);
            janelaAtual.show();
        } catch (IOException e) {
            e.printStackTrace(); // Exibe o erro no console
        }
    }

}
