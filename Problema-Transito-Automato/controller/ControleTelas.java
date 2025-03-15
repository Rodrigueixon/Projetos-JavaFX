/* ******************************************************************************************************
* Autor: Rodrigo Ferreira Bento Aguiar
* Matricula: 202310594
* Inicio: 18/06/2024
* Ultima alteracao: 18/06/2024
* Nome: ControleTelas
* Descricao: classe responsavel por controlar a troca de telas. 
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
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ControleTelas {
  @FXML Button botaoIniciar;
  @FXML Button comoFunciona;
  
  Parent raizInfo;
  private Stage janela;
  Scene cenaInfo;
  Stage stageInfo;
  private Scene cena;

  /**********************************************************************
  * Metodo: trocarParaTelaDoTransito
  * Funcao: Trocar a cena atual para a tela do transito
  * Parametros: event
  * Retorno: void
  **********************************************************************/
  public void trocarParaTelaDoTransito(ActionEvent event) throws IOException {
    Parent raiz = FXMLLoader.load(getClass().getResource("/view/telaTransito.fxml")); // Carrega o arquivo FXML da tela do transito
    janela = (Stage) ((Node) event.getSource()).getScene().getWindow();// Obtem a janela atual a partir do evento de clique no botao
    cena = new Scene(raiz); // Cria uma nova cena utilizando o layout carregado do FXML
    janela.setScene(cena); // Define a nova cena na janela
    janela.show();        // Exibe a janela com a nova cena
  }

  /**********************************************************************
  * Metodo: comoFunciona
  * Funcao: Apresenta o problema do transito automato
  * Parametros: event
  * Retorno: void
  **********************************************************************/
  public void comoFunciona(ActionEvent event) {
    try {
      // Carrega o arquivo FXML da tela de informacao
      FXMLLoader carregar = new FXMLLoader(getClass().getResource("/view/informacao.fxml"));
      Parent raizInfo = carregar.load();

      // Cria um novo Stage para a janela de informacao
      Stage janelaInfo = new Stage();
      janelaInfo.getIcons().add(new Image("/img/iconeInfo.png"));
      Scene cenaInfo = new Scene(raizInfo);

      // Configura o Stage
      janelaInfo.setTitle("Como Funciona");
      janelaInfo.setScene(cenaInfo);
      janelaInfo.setResizable(false);

      // Exibe a nova janela
      janelaInfo.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
