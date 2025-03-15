/******************************************************************************************************
* Autor: Rodrigo Ferreira Bento Aguiar
* Matricula: 202310594
* Inicio: 04/06/2024
* Ultima alteracao: 05/06/2024
* Nome: Penguim dorminhoco
* Descricao: Este projeto eh uma implementacao do classico problema do barbeiro dorminhoco, onde simula 
*            a dinamica de pinguins que esperam por um cabeleleiro. O iglu possui cinco cadeiras para 
*            espera e uma cadeira para atendimento. Quando nao ha pinguins para atender, o cabeleleiro 
*            dorme ate que um novo cliente chegue.
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
      janela.setTitle("Pinguim dorminhoco"); // Define o titulo da janela
      janela.setResizable(false);           // Define que a janela nao pode ser redimensionada
      janela.setScene(new Scene(raiz));                  // Define a cena da janela utilizando o layout carregado do FXML
      janela.setOnCloseRequest(e -> {System.exit(0);}); // Define uma acao para fechar a aplicacao ao fechar a janela
      janela.show();                                   // Exibe a janela
      janela.sizeToScene();                           // Ajusta o tamanho da janela para se adequar ao tamanho da cena
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