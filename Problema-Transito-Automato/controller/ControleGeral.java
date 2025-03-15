/* ******************************************************************************************************
* Autor: Rodrigo Ferreira Bento Aguiar
* Matricula: 202310594
* Inicio: 18/06/2024
* Ultima alteracao: 18/06/2024
* Nome: ControleGeral
* Descricao: Classe que gerencia a inicializacao e controle dos elementos visuais 
*            e logicos do sistema de trafego, incluindo carros e suas rotas. Ela controla a 
*            visibilidade dos elementos, a posicao inicial dos carros e a criacao das threads dos  
*            carros. Alem disso, gerencia os semaforos para sincronizacao entre os carros.
******************************************************************************************************* */

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.*;

public class ControleGeral implements Initializable {

  // Imagem dos icones de visivel/invisivel
  @FXML private ImageView simboloRotaInvisivelCarro1;
  @FXML private ImageView simboloRotaInvisivelCarro2;
  @FXML private ImageView simboloRotaInvisivelCarro3;
  @FXML private ImageView simboloRotaInvisivelCarro4;
  @FXML private ImageView simboloRotaInvisivelCarro5;
  @FXML private ImageView simboloRotaInvisivelCarro6;
  @FXML private ImageView simboloRotaInvisivelCarro7;
  @FXML private ImageView simboloRotaInvisivelCarro8;
  @FXML private ImageView simboloRotaVisivelCarro1;
  @FXML private ImageView simboloRotaVisivelCarro2;
  @FXML private ImageView simboloRotaVisivelCarro3;
  @FXML private ImageView simboloRotaVisivelCarro4;
  @FXML private ImageView simboloRotaVisivelCarro5;
  @FXML private ImageView simboloRotaVisivelCarro6;
  @FXML private ImageView simboloRotaVisivelCarro7;
  @FXML private ImageView simboloRotaVisivelCarro8;

  // Imagem das rotas dos carros
  @FXML private ImageView imgRotaCarro1;
  @FXML private ImageView imgRotaCarro2;
  @FXML private ImageView imgRotaCarro3;
  @FXML private ImageView imgRotaCarro4;
  @FXML private ImageView imgRotaCarro5;
  @FXML private ImageView imgRotaCarro6;
  @FXML private ImageView imgRotaCarro7;
  @FXML private ImageView imgRotaCarro8;

  // Sliders que controlam a velocidade dos carros
  @FXML private Slider velocidadeCarro1;
  @FXML private Slider velocidadeCarro2;
  @FXML private Slider velocidadeCarro3;
  @FXML private Slider velocidadeCarro4;
  @FXML private Slider velocidadeCarro5;
  @FXML private Slider velocidadeCarro6;
  @FXML private Slider velocidadeCarro7;
  @FXML private Slider velocidadeCarro8;

  // Imagem dos carros
  @FXML private ImageView imgCarro1;
  @FXML private ImageView imgCarro2;
  @FXML private ImageView imgCarro3;
  @FXML private ImageView imgCarro4;
  @FXML private ImageView imgCarro5;
  @FXML private ImageView imgCarro6;
  @FXML private ImageView imgCarro7;
  @FXML private ImageView imgCarro8;

  // Thread dos carros
  Carro1 carro1;
  Carro2 carro2;
  Carro3 carro3;
  Carro4 carro4;
  Carro5 carro5;
  Carro6 carro6;
  Carro7 carro7;
  Carro8 carro8;

  // Semaforo de controle dos carros 1 e 2
  public static Semaphore semaforo1e2RC1;
  // Semaforo de controle dos carros 1 e 3
  public static Semaphore semaforo1e3RC1;
  // Semaforo de controle dos carros 1 e 4
  public static Semaphore semaforo1e4RC1;
  public static Semaphore semaforo1e4RC2;
  // Semaforo de controle dos carros 1 e 5
  public static Semaphore semaforo1e5RC1;
  // Semaforo de controle dos carros 1 e 6
  public static Semaphore semaforo1e6RC1;
  // Semaforo de controle dos carros 1 e 7
  public static Semaphore semaforo1e7RC1;
  public static Semaphore semaforo1e7RC2;
  // Semaforo de controle dos carros 1 e 8
  public static Semaphore semaforo1e8RC1;
  public static Semaphore semaforo1e8RC2;
  public static Semaphore semaforo1e8RC3;
  public static Semaphore semaforo1e8RC4;
  // Semaforo de controle dos carros 2 e 3
  public static Semaphore semaforo2e3RC1;
  public static Semaphore semaforo2e3RC2;
  // Semaforo de controle dos carros 2 e 4
  public static Semaphore semaforo2e4RC1;
  public static Semaphore semaforo2e4RC2;
  public static Semaphore semaforo2e4RC3;
  // Semaforo de controle dos carros 2 e 5
  public static Semaphore semaforo2e5RC1;
  public static Semaphore semaforo2e5RC2;
  public static Semaphore semaforo2e5RC3;
  // Semaforo de controle dos carros 2 e 6
  public static Semaphore semaforo2e6RC1;
  // Semaforo de controle dos carros 2 e 7
  public static Semaphore semaforo2e7RC1;
  public static Semaphore semaforo2e7RC2;
  public static Semaphore semaforo2e7RC3;
  public static Semaphore semaforo2e7RC4;
  // Semaforo de controle dos carros 2 e 8
  public static Semaphore semaforo2e8RC1;
  public static Semaphore semaforo2e8RC2;
  public static Semaphore semaforo2e8RC3;
  public static Semaphore semaforo2e8RC4;
  public static Semaphore semaforo2e8RC5;
  // Semaforo de controle dos carros 3 e 4
  public static Semaphore semaforo3e4RC1;
  public static Semaphore semaforo3e4RC2;
  public static Semaphore semaforo3e4RC3;
  // Semaforo de controle dos carros 3 e 5
  public static Semaphore semaforo3e5RC1;
  // Semaforo de controle dos carros 3 e 6
  public static Semaphore semaforo3e6RC1;
  public static Semaphore semaforo3e6RC2;
  // Semaforo de controle dos carros 3 e 7
  public static Semaphore semaforo3e7RC1;
  public static Semaphore semaforo3e7RC2;
  // Semaforo de controle dos carros 3 e 8
  public static Semaphore semaforo3e8RC1;
  public static Semaphore semaforo3e8RC2;
  public static Semaphore semaforo3e8RC3;
  // Semaforo de controle dos carros 4 e 5
  public static Semaphore semaforo4e5RC1;
  public static Semaphore semaforo4e5RC2;
  // Semaforo de controle dos carros 4 e 6
  public static Semaphore semaforo4e6RC1;
  // Semaforo de controle dos carros 4 e 7
  public static Semaphore semaforo4e7RC1;
  public static Semaphore semaforo4e7RC2;
  public static Semaphore semaforo4e7RC3;
  public static Semaphore semaforo4e7RC4;
  // Semaforo de controle dos carros 4 e 8
  public static Semaphore semaforo4e8RC1;
  public static Semaphore semaforo4e8RC2;
  public static Semaphore semaforo4e8RC3;
  public static Semaphore semaforo4e8RC4;
  // Semaforo de controle dos carros 5 e 6
  public static Semaphore semaforo5e6RC1;
  public static Semaphore semaforo5e6RC2;
  // Semaforo de controle dos carros 5 e 7
  public static Semaphore semaforo5e7RC1;
  public static Semaphore semaforo5e7RC2;
  public static Semaphore semaforo5e7RC3;
  // Semaforo de controle dos carros 5 e 8
  public static Semaphore semaforo5e8RC1;
  public static Semaphore semaforo5e8RC2;
  // Semaforo de controle dos carros 6 e 7
  public static Semaphore semaforo6e7RC1;
  public static Semaphore semaforo6e7RC2;
  // Semaforo de controle dos carros 6 e 8
  public static Semaphore semaforo6e8RC1;
  public static Semaphore semaforo6e8RC2;
  public static Semaphore semaforo6e8RC3;
  // Semaforo de controle dos carros 7 e 8
  public static Semaphore semaforo7e8RC1;
  public static Semaphore semaforo7e8RC2;
  public static Semaphore semaforo7e8RC3;
  public static Semaphore semaforo7e8RC4;
  public static Semaphore semaforo7e8RC5;
  public static Semaphore semaforo7e8RC6;

  int velocidadeInicial = 30;

  /******************************************************************************
   * Metodo: initialize
   * Funcao: Inicializa a interface e configura o estado inicial do transito
   * Parametros: URL location, ResourceBundle resources
   * Retorno: nenhum
   *****************************************************************************/
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    iniciarTransito();
  }

  /******************************************************************************
   * Metodo: iniciarTransito
   * Funcao: Inicializa o transito
   * configurando o estado inicial dos componentes
   * Parametros: nenhum
   * Retorno: nenhum
   *****************************************************************************/
  private void iniciarTransito() {
    // Define todos os elementos visuais iniciais como invisiveis
    ajustarVisibidadeDosElementos();

    // Define a posicao inicial de todos os carros
    posicaoDosCarros();

    // Cria as threads dos carros
    carro1 = new Carro1(this, imgCarro1);
    carro2 = new Carro2(this, imgCarro2);
    carro3 = new Carro3(this, imgCarro3);
    carro4 = new Carro4(this, imgCarro4);
    carro5 = new Carro5(this, imgCarro5);
    carro6 = new Carro6(this, imgCarro6);
    carro7 = new Carro7(this, imgCarro7);
    carro8 = new Carro8(this, imgCarro8);

    // Inicializa os semaforos para controle de sincronizacao
    // semaforo dos carros 1 e 2
    semaforo1e2RC1 = new Semaphore(1);
    // semaforo dos carros 1 e 3
    semaforo1e3RC1 = new Semaphore(1);
    // semaforo dos carros 1 e 4
    semaforo1e4RC1 = new Semaphore(1);
    semaforo1e4RC2 = new Semaphore(1);
    // semaforo dos carros 1 e 5
    semaforo1e5RC1 = new Semaphore(1);
    // semaforo dos carros 1 e 6
    semaforo1e6RC1 = new Semaphore(1);
    // semaforo dos carros 1 e 7
    semaforo1e7RC1 = new Semaphore(1);
    semaforo1e7RC2 = new Semaphore(1);
    // semaforo dos carros 1 e 8
    semaforo1e8RC1 = new Semaphore(1);
    semaforo1e8RC2 = new Semaphore(1);
    semaforo1e8RC3 = new Semaphore(1);
    semaforo1e8RC4 = new Semaphore(1);
    // semaforo dos carros 2 e 3
    semaforo2e3RC1 = new Semaphore(1);
    semaforo2e3RC2 = new Semaphore(1);
    // semaforo dos carros 2 e 4
    semaforo2e4RC1 = new Semaphore(1);
    semaforo2e4RC2 = new Semaphore(1);
    semaforo2e4RC3 = new Semaphore(1);
    // semaforo dos carros 2 e 5
    semaforo2e5RC1 = new Semaphore(1);
    semaforo2e5RC2 = new Semaphore(1);
    semaforo2e5RC3 = new Semaphore(1);
    // semaforo dos carros 2 e 6
    semaforo2e6RC1 = new Semaphore(1);
    // semaforo dos carros 2 e 7
    semaforo2e7RC1 = new Semaphore(1);
    semaforo2e7RC2 = new Semaphore(1);
    semaforo2e7RC3 = new Semaphore(1);
    semaforo2e7RC4 = new Semaphore(1);
    // semaforo dos carros 2 e 8
    semaforo2e8RC1 = new Semaphore(1);
    semaforo2e8RC2 = new Semaphore(1);
    semaforo2e8RC3 = new Semaphore(1);
    semaforo2e8RC4 = new Semaphore(1);
    semaforo2e8RC5 = new Semaphore(1);
    // semaforo dos carros 3 e 4
    semaforo3e4RC1 = new Semaphore(1);
    semaforo3e4RC2 = new Semaphore(1);
    semaforo3e4RC3 = new Semaphore(1);
    // semaforo dos carros 3 e 5
    semaforo3e5RC1 = new Semaphore(1);
    // semaforo dos carros 3 e 6
    semaforo3e6RC1 = new Semaphore(1);
    semaforo3e6RC2 = new Semaphore(1);
    // semaforo dos carros 3 e 7
    semaforo3e7RC1 = new Semaphore(1);
    semaforo3e7RC2 = new Semaphore(1);
    // semaforo dos carros 3 e 8
    semaforo3e8RC1 = new Semaphore(1);
    semaforo3e8RC2 = new Semaphore(1);
    semaforo3e8RC3 = new Semaphore(1);
    // semaforo dos carros 4 e 5
    semaforo4e5RC1 = new Semaphore(1);
    semaforo4e5RC2 = new Semaphore(1);
    // semaforo dos carros 4 e 6
    semaforo4e6RC1 = new Semaphore(1);
    // semaforo dos carros 4 e 7
    semaforo4e7RC1 = new Semaphore(1);
    semaforo4e7RC2 = new Semaphore(1);
    semaforo4e7RC3 = new Semaphore(1);
    semaforo4e7RC4 = new Semaphore(1);
    // semaforo dos carros 4 e 8
    semaforo4e8RC1 = new Semaphore(1);
    semaforo4e8RC2 = new Semaphore(1);
    semaforo4e8RC3 = new Semaphore(1);
    semaforo4e8RC4 = new Semaphore(1);
    // semaforo dos carros 5 e 6
    semaforo5e6RC1 = new Semaphore(1);
    semaforo5e6RC2 = new Semaphore(1);
    // semaforo dos carros 5 e 7
    semaforo5e7RC1 = new Semaphore(1);
    semaforo5e7RC2 = new Semaphore(1);
    semaforo5e7RC3 = new Semaphore(1);
    // semaforo dos carros 5 e 8
    semaforo5e8RC1 = new Semaphore(1);
    semaforo5e8RC2 = new Semaphore(1);
    // semaforo dos carros 6 e 7
    semaforo6e7RC1 = new Semaphore(1);
    semaforo6e7RC2 = new Semaphore(1);
    // semaforo dos carros 6 e 8
    semaforo6e8RC1 = new Semaphore(1);
    semaforo6e8RC2 = new Semaphore(1);
    semaforo6e8RC3 = new Semaphore(1);
    // semaforo dos carros 7 e 8
    semaforo7e8RC1 = new Semaphore(1);
    semaforo7e8RC2 = new Semaphore(1);
    semaforo7e8RC3 = new Semaphore(1);
    semaforo7e8RC4 = new Semaphore(1);
    semaforo7e8RC5 = new Semaphore(1);
    semaforo7e8RC6 = new Semaphore(1);

    // Inicia as threads
    carro1.start();
    carro2.start();
    carro3.start();
    carro4.start();
    carro5.start();
    carro6.start();
    carro7.start();
    carro8.start();

  }

  /******************************************************************************
   * Metodo: ajustarVisibidadeDosElementos
   * Funcao: Ajusta a visibilidade de todos os elementos visuais
   * Parametros: nenhum
   * Retorno: nenhum
   *****************************************************************************/
  private void ajustarVisibidadeDosElementos() {
    simboloRotaInvisivelCarro1.setVisible(true);
    simboloRotaInvisivelCarro2.setVisible(true);
    simboloRotaInvisivelCarro3.setVisible(true);
    simboloRotaInvisivelCarro4.setVisible(true);
    simboloRotaInvisivelCarro5.setVisible(true);
    simboloRotaInvisivelCarro6.setVisible(true);
    simboloRotaInvisivelCarro7.setVisible(true);
    simboloRotaInvisivelCarro8.setVisible(true);
    simboloRotaVisivelCarro1.setVisible(false);
    simboloRotaVisivelCarro2.setVisible(false);
    simboloRotaVisivelCarro3.setVisible(false);
    simboloRotaVisivelCarro4.setVisible(false);
    simboloRotaVisivelCarro5.setVisible(false);
    simboloRotaVisivelCarro6.setVisible(false);
    simboloRotaVisivelCarro7.setVisible(false);
    simboloRotaVisivelCarro8.setVisible(false);
    imgRotaCarro1.setVisible(false);
    imgRotaCarro2.setVisible(false);
    imgRotaCarro3.setVisible(false);
    imgRotaCarro4.setVisible(false);
    imgRotaCarro5.setVisible(false);
    imgRotaCarro6.setVisible(false);
    imgRotaCarro7.setVisible(false);
    imgRotaCarro8.setVisible(false);
  }

  /******************************************************************************
   * Metodo: posicaoDosCarros
   * Funcao: Define as posicoes iniciais dos carros
   * Parametros: nenhum
   * Retorno: nenhum
   *****************************************************************************/
  public void posicaoDosCarros() {
    // Define as coordenadas iniciais do carro 1
    imgCarro1.setLayoutX(212);
    imgCarro1.setLayoutY(438);

    // Define as coordenadas iniciais do carro 2
    imgCarro2.setLayoutX(212);
    imgCarro2.setLayoutY(180);

    // Define as coordenadas iniciais do carro 3
    imgCarro3.setLayoutX(120);
    imgCarro3.setLayoutY(180);

    // Define as coordenadas iniciais do carro 4
    imgCarro4.setLayoutX(80);
    imgCarro4.setLayoutY(44);

    // Define as coordenadas iniciais do carro 5
    imgCarro5.setLayoutX(390);
    imgCarro5.setLayoutY(180);

    // Define as coordenadas iniciais do carro 6
    imgCarro6.setLayoutX(260);
    imgCarro6.setLayoutY(44);

    // Define as coordenadas iniciais do carro 7
    imgCarro7.setLayoutX(120);
    imgCarro7.setLayoutY(268);

    // Define as coordenadas iniciais do carro 8
    imgCarro8.setLayoutX(120);
    imgCarro8.setLayoutY(353);
  }

  /******************************************************************************
   * Metodo: visibilidadeCarro1
   * Funcao: Alterna a visibilidade da rota do carro 1
   * Parametros: nenhum
   * Retorno: nenhum
   *****************************************************************************/
  public void visibilidadeCarro1(ActionEvent event) throws IOException {
    // se a rota nao estiver visivel, ela aparece
    if (simboloRotaInvisivelCarro1.isVisible()) {
      simboloRotaInvisivelCarro1.setVisible(false);
      simboloRotaVisivelCarro1.setVisible(true);
      imgRotaCarro1.setVisible(true);
    } else { // se nao, ela desaparece
      simboloRotaVisivelCarro1.setVisible(false);
      imgRotaCarro1.setVisible(false);
      simboloRotaInvisivelCarro1.setVisible(true);
    }
  }

  /******************************************************************************
   * Metodo: visibilidadeCarro2
   * Funcao: Alterna a visibilidade da rota do carro 2
   * Parametros: nenhum
   * Retorno: nenhum
   *****************************************************************************/
  public void visibilidadeCarro2(ActionEvent event) throws IOException {
    // se a rota nao estiver visivel, ela aparece
    if (simboloRotaInvisivelCarro2.isVisible()) {
      simboloRotaInvisivelCarro2.setVisible(false);
      simboloRotaVisivelCarro2.setVisible(true);
      imgRotaCarro2.setVisible(true);
    } else { // se nao, ela desaparece
      imgRotaCarro2.setVisible(false);
      simboloRotaVisivelCarro2.setVisible(false);
      simboloRotaInvisivelCarro2.setVisible(true);
    }
  }

  /******************************************************************************
   * Metodo: visibilidadeCarro3
   * Funcao: Alterna a visibilidade da rota do carro 3
   * Parametros: nenhum
   * Retorno: nenhum
   *****************************************************************************/
  public void visibilidadeCarro3(ActionEvent event) throws IOException {
    // se a rota nao estiver visivel, ela aparece
    if (simboloRotaInvisivelCarro3.isVisible()) {
      simboloRotaInvisivelCarro3.setVisible(false);
      simboloRotaVisivelCarro3.setVisible(true);
      imgRotaCarro3.setVisible(true);
    } else { // se nao, ela desaparece
      imgRotaCarro3.setVisible(false);
      simboloRotaVisivelCarro3.setVisible(false);
      simboloRotaInvisivelCarro3.setVisible(true);
    }
  }

  /******************************************************************************
   * Metodo: visibilidadeCarro4
   * Funcao: Alterna a visibilidade da rota do carro 4
   * Parametros: nenhum
   * Retorno: nenhum
   *****************************************************************************/
  public void visibilidadeCarro4(ActionEvent event) throws IOException {
    // se a rota nao estiver visivel, ela aparece
    if (simboloRotaInvisivelCarro4.isVisible()) {
      simboloRotaInvisivelCarro4.setVisible(false);
      simboloRotaVisivelCarro4.setVisible(true);
      imgRotaCarro4.setVisible(true);
    } else { // se nao, ela desaparece
      imgRotaCarro4.setVisible(false);
      simboloRotaVisivelCarro4.setVisible(false);
      simboloRotaInvisivelCarro4.setVisible(true);
    }
  }

  /******************************************************************************
   * Metodo: visibilidadeCarro5
   * Funcao: Alterna a visibilidade da rota do carro 5
   * Parametros: nenhum
   * Retorno: nenhum
   *****************************************************************************/
  public void visibilidadeCarro5(ActionEvent event) throws IOException {
    // se a rota nao estiver visivel, ela aparece
    if (simboloRotaInvisivelCarro5.isVisible()) {
      simboloRotaInvisivelCarro5.setVisible(false);
      simboloRotaVisivelCarro5.setVisible(true);
      imgRotaCarro5.setVisible(true);
    } else { // se nao, ela desaparece
      imgRotaCarro5.setVisible(false);
      simboloRotaVisivelCarro5.setVisible(false);
      simboloRotaInvisivelCarro5.setVisible(true);
    }
  }

  /******************************************************************************
   * Metodo: visibilidadeCarro6
   * Funcao: Alterna a visibilidade da rota do carro 6
   * Parametros: nenhum
   * Retorno: nenhum
   *****************************************************************************/
  public void visibilidadeCarro6(ActionEvent event) throws IOException {
    // se a rota nao estiver visivel, ela aparece
    if (simboloRotaInvisivelCarro6.isVisible()) {
      simboloRotaInvisivelCarro6.setVisible(false);
      simboloRotaVisivelCarro6.setVisible(true);
      imgRotaCarro6.setVisible(true);
    } else { // se nao, ela desaparece
      imgRotaCarro6.setVisible(false);
      simboloRotaVisivelCarro6.setVisible(false);
      simboloRotaInvisivelCarro6.setVisible(true);
    }
  }

  /******************************************************************************
   * Metodo: visibilidadeCarro7
   * Funcao: Alterna a visibilidade da rota do carro 7
   * Parametros: nenhum
   * Retorno: nenhum
   *****************************************************************************/
  public void visibilidadeCarro7(ActionEvent event) throws IOException {
    // se a rota nao estiver visivel, ela aparece
    if (simboloRotaInvisivelCarro7.isVisible()) {
      simboloRotaInvisivelCarro7.setVisible(false);
      simboloRotaVisivelCarro7.setVisible(true);
      imgRotaCarro7.setVisible(true);
    } else { // se nao, ela desaparece
      imgRotaCarro7.setVisible(false);
      simboloRotaVisivelCarro7.setVisible(false);
      simboloRotaInvisivelCarro7.setVisible(true);
    }
  }

  /******************************************************************************
   * Metodo: visibilidadeCarro8
   * Funcao: Alterna a visibilidade da rota do carro 8
   * Parametros: nenhum
   * Retorno: nenhum
   *****************************************************************************/
  public void visibilidadeCarro8(ActionEvent event) throws IOException {
    // se a rota nao estiver visivel, ela aparece
    if (simboloRotaInvisivelCarro8.isVisible()) {
      simboloRotaInvisivelCarro8.setVisible(false);
      simboloRotaVisivelCarro8.setVisible(true);
      imgRotaCarro8.setVisible(true);
    } else { // se nao, ela desaparece
      imgRotaCarro8.setVisible(false);
      simboloRotaVisivelCarro8.setVisible(false);
      simboloRotaInvisivelCarro8.setVisible(true);
    }
  }

  /**********************************************************************
   * Metodo: resetarTransito
   * Funcao: Reseta a transito, interrompendo as threads em execucao e reiniciando
   * com os valores padrao.
   * Parametros: event
   * Retorno: nenhum
   **********************************************************************/
  public void resetarTransito(ActionEvent event) throws IOException {
    // Define as variaveis rodando como falsa, sinalizando para a thread parar
    carro1.pararDeRodar();
    carro2.pararDeRodar();
    carro3.pararDeRodar();
    carro4.pararDeRodar();
    carro5.pararDeRodar();
    carro6.pararDeRodar();
    carro7.pararDeRodar();
    carro8.pararDeRodar();

    Parent raiz = FXMLLoader.load(getClass().getResource("/view/telaTransito.fxml")); // Carrega o arquivo FXML da telado transito
    Stage janela = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Obtem a janela atual a partir do evento de clique no botao
    Scene cena = new Scene(raiz); // Cria uma nova cena utilizando o layout carregado do FXML
    janela.setScene(cena);       // Define a nova cena na janela
    janela.show();              // Exibe a janela com a nova cena
  }

  /*****************************************************************************
   * Metodo: velocidadeCarro1
   * Funcao: Obtem a velocidade do Carro1 a partir do valor do slider e converte
   * para int
   * Parametros: nenhum
   * Retorno: int - velocidade do Carro1
   *****************************************************************************/
  public int velocidadeCarro1() {

    double velocidadeAntiga = velocidadeCarro1.getValue();
    int velocidadeConvertida = (int) velocidadeAntiga;
    return velocidadeConvertida;
  }

  /*****************************************************************************
   * Metodo: velocidadeCarro2
   * Funcao: Obtem a velocidade do Carro2 a partir do valor do slider e converte
   * para int
   * Parametros: nenhum
   * Retorno: int - velocidade do Carro2
   *****************************************************************************/
  public int velocidadeCarro2() {
    double velocidadeAntiga = velocidadeCarro2.getValue();
    int velocidadeConvertida = (int) velocidadeAntiga;
    return velocidadeConvertida;
  }

  /*****************************************************************************
   * Metodo: velocidadeCarro3
   * Funcao: Obtem a velocidade do Carro3 a partir do valor do slider e converte
   * para int
   * Parametros: nenhum
   * Retorno: int - velocidade do Carro3
   *****************************************************************************/
  public int velocidadeCarro3() {
    double velocidadeAntiga = velocidadeCarro3.getValue();
    int velocidadeConvertida = (int) velocidadeAntiga;
    return velocidadeConvertida;
  }

  /*****************************************************************************
   * Metodo: velocidadeCarro4
   * Funcao: Obtem a velocidade do Carro4 a partir do valor do slider e converte
   * para int
   * Parametros: nenhum
   * Retorno: int - velocidade do Carro4
   *****************************************************************************/
  public int velocidadeCarro4() {
    double velocidadeAntiga = velocidadeCarro4.getValue();
    int velocidadeConvertida = (int) velocidadeAntiga;
    return velocidadeConvertida;
  }

  /*****************************************************************************
   * Metodo: velocidadeCarro5
   * Funcao: Obtem a velocidade do Carro5 a partir do valor do slider e converte
   * para int
   * Parametros: nenhum
   * Retorno: int - velocidade do Carro5
   *****************************************************************************/
  public int velocidadeCarro5() {
    double velocidadeAntiga = velocidadeCarro5.getValue();
    int velocidadeConvertida = (int) velocidadeAntiga;
    return velocidadeConvertida;
  }

  /*****************************************************************************
   * Metodo: velocidadeCarro6
   * Funcao: Obtem a velocidade do Carro6 a partir do valor do slider e converte
   * para int
   * Parametros: nenhum
   * Retorno: int - velocidade do Carro6
   *****************************************************************************/
  public int velocidadeCarro6() {
    double velocidadeAntiga = velocidadeCarro6.getValue();
    int velocidadeConvertida = (int) velocidadeAntiga;
    return velocidadeConvertida;
  }

  /*****************************************************************************
   * Metodo: velocidadeCarro7
   * Funcao: Obtem a velocidade do Carro7 a partir do valor do slider e converte
   * para int
   * Parametros: nenhum
   * Retorno: int - velocidade do Carro7
   *****************************************************************************/
  public int velocidadeCarro7() {
    double velocidadeAntiga = velocidadeCarro7.getValue();
    int velocidadeConvertida = (int) velocidadeAntiga;
    return velocidadeConvertida;
  }

  /*****************************************************************************
   * Metodo: velocidadeCarro8
   * Funcao: Obtem a velocidade do Carro8 a partir do valor do slider e converte
   * para int
   * Parametros: nenhum
   * Retorno: int - velocidade do Carro8
   *****************************************************************************/
  public int velocidadeCarro8() {
    double velocidadeAntiga = velocidadeCarro8.getValue();
    int velocidadeConvertida = (int) velocidadeAntiga;
    return velocidadeConvertida;
  }
}
