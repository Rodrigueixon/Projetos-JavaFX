/******************************************************************************************************
* Autor: Rodrigo Ferreira Bento Aguiar
* Matricula: 202310594
* Inicio: 18/06/2024
* Ultima alteracao: 18/06/2024
* Nome: Principal
* Descricao: Este projeto eh uma implementacao do problema do trafego automato, onde simula 
*            a dinamica de carros que andam por rotas e sentidos definidos. O transito possui
*            5 carros que devem evitar de se colidirem e entrarem em estado de deadlock.
******************************************************************************************************* */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import controller.ControleGeral;
import controller.ControleTelas;


@SuppressWarnings("unused")
public class Principal extends Application {
  
  /**********************************************************************
  * Metodo: start
  * Funcao: Inicializa a aplicacao JavaFX
  * Parametros: janela
  * Retorno: void
  **********************************************************************/
  @Override
  public void start(Stage janela) throws Exception {
    try {
      Parent raiz = FXMLLoader.load(getClass().getResource("/view/telaInicial.fxml"));// Carrega o arquivo FXML da tela inicial
      janela.getIcons().add(new Image("/img/icone.png"));                             // Adiciona um icone para a janela
      janela.setTitle("Grande Transito Automato"); // Define o titulo da janela
      janela.setResizable(false);          // Define que a janela nao pode ser redimensionada
      janela.setScene(new Scene(raiz));         // Define a cena da janela utilizando o layout carregado do FXML
      janela.show();          // Exibe a janela
      janela.sizeToScene();  // Ajusta o tamanho da janela para se adequar ao tamanho da cena
      janela.setOnCloseRequest(e -> {System.exit(0);}); // Define uma acao para fechar a aplicacao ao fechar a janela
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