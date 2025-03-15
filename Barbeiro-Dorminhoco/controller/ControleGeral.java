/* ******************************************************************************************************
* Autor: Rodrigo Ferreira Bento Aguiar
* Matricula: 202310594
* Inicio: 04/05/2024
* Ultima alteracao: 17/06/2024
* Nome: ControleGeral
* Descricao: Classe que controla a logica principal da simulacao do barbeiro dorminhoco. Gerencia as
*            interacoes entre os clientes e o barbeiro, incluindo a animacao dos clientes entrando na fila,
*            sendo atendidos, e saindo, bem como o estado do barbeiro (cortando cabelo ou dormindo).
******************************************************************************************************* */

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
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
import javafx.util.Duration;

import model.*;
import controller.*;

@SuppressWarnings("unused")
public class ControleGeral implements Initializable {

  @FXML private ImageView primeiroClienteFila;
  @FXML private ImageView segundoClienteFila;
  @FXML private ImageView terceiroClienteFila;
  @FXML private ImageView quartoClienteFila;
  @FXML private ImageView ultimoClienteFila;
  @FXML private ImageView clienteEntrouConferindo;
  @FXML private ImageView clienteSendoAtendido;
  @FXML private ImageView clienteTriste;
  @FXML private ImageView clienteSaindo1;
  @FXML private ImageView clienteSaindo2;
  @FXML private ImageView clienteSaindo3;
  @FXML private ImageView clienteSaindo4;
  @FXML private ImageView clienteSaindo5;
  @FXML private ImageView clienteSaindo6;
  @FXML private ImageView clienteSaindo7;
  @FXML private ImageView clienteSaindo8;
  @FXML private ImageView clienteReto1;
  @FXML private ImageView clienteReto2;
  @FXML private ImageView clienteReto3;
  @FXML private ImageView clienteReto4;
  @FXML private ImageView clienteReto5;
  @FXML private ImageView clienteReto6;
  @FXML private ImageView clienteReto7;
  @FXML private ImageView clienteEntrouFila1;
  @FXML private ImageView clienteEntrouFila2;
  @FXML private ImageView clienteEntrouFila3;
  @FXML private ImageView clienteEntrouFila4;
  @FXML private ImageView clienteEntrouFila5;
  @FXML private ImageView clienteEntrouFila6;
  @FXML private ImageView clienteEntrouFila7;
  @FXML private ImageView clienteEntrouFila8;
  @FXML private ImageView clienteEntrouFila9;
  @FXML private ImageView clienteEntrouFila10;
  @FXML private ImageView barbeiroCortandoCabelo;
  @FXML private ImageView barbeiroDormindo;
  @FXML private Slider velocidadeBarbeiro;
  @FXML private Slider velocidadeClientes;

  // Declaracao de constantes e variaveis estaticas para controle da logica de sincronizacao
  public static final int CADEIRAS = 5;  // Numero de cadeiras na sala de espera da barbearia
  public static Semaphore clientes;     // Semaforo para controlar os clientes
  public static Semaphore barbeiro;    // Semaforo para controlar o barbeiro
  public static Semaphore mutex;      // Semaforo para garantir acesso exclusivo as variaveis compartilhadas
  public static int esperando;       // Contador para o numero de clientes esperando

  Barbeiro barbeiroThread;         //thread do barbeiro
  GerarClientes geraClientes;     //thread de geracao dos clientes
  

  /******************************************************************************
  * Metodo: initialize
  * Funcao: Inicializa a interface e configura o estado inicial da barbearia
  * Parametros: URL location, ResourceBundle resources
  * Retorno: nenhum
  *****************************************************************************/
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    iniciarBarbearia();
  }

  /******************************************************************************
  * Metodo: iniciarBarbearia
  * Funcao: Inicializa a barbearia configurando o estado inicial dos componentes
  * Parametros: nenhum
  * Retorno: nenhum
  *****************************************************************************/
  public void iniciarBarbearia() {
    // Define todos os elementos visuais iniciais como invisiveis
    todosInvisiveis();

    // Inicializa os semaforos para controle de sincronizacao
    clientes = new Semaphore(0);
    barbeiro = new Semaphore(0);
    mutex = new Semaphore(1);
    esperando = 0;

    // Cria as threads do barbeiro e gera clientes
    barbeiroThread = new Barbeiro(this);
    geraClientes = new GerarClientes(this);

    // Define as threads como daemons, ou seja, elas nao impedem a aplicacao de fechar
    barbeiroThread.setDaemon(true);
    geraClientes.setDaemon(true);

    // Inicia as threads
    barbeiroThread.start();
    geraClientes.start();
  }

  /**********************************************************************
  * Metodo: resetarBarbearia
  * Funcao: Reseta a barbearia, interrompendo as threads em execucao e reiniciando com os valores padrao.
  * Parametros: event
  * Retorno: nenhum
  **********************************************************************/
  public void resetarBarbearia(ActionEvent event) throws IOException {
    if(barbeiroThread != null){
      barbeiroThread.pararDeRodar();
    }
    if(geraClientes != null){
      geraClientes.pararDeRodar();
    }
    velocidadeBarbeiro.setValue(800);
    velocidadeClientes.setValue(3000);
    iniciarBarbearia();
  }

  /******************************************************************************
  * Metodo: todosInvisiveis
  * Funcao: Define todos os elementos visuais iniciais como invisiveis
  * Parametros: nenhum
  * Retorno: nenhum
  *****************************************************************************/
  public void todosInvisiveis(){
    primeiroClienteFila.setVisible(false);
    segundoClienteFila.setVisible(false);
    terceiroClienteFila.setVisible(false);
    quartoClienteFila.setVisible(false);
    ultimoClienteFila.setVisible(false);
    clienteEntrouConferindo.setVisible(false);
    clienteSendoAtendido.setVisible(false);
    clienteTriste.setVisible(false);
    clienteSaindo1.setVisible(false);
    clienteSaindo2.setVisible(false);
    clienteSaindo3.setVisible(false);
    clienteSaindo4.setVisible(false);
    clienteSaindo5.setVisible(false);
    clienteSaindo6.setVisible(false);
    clienteSaindo7.setVisible(false);
    clienteSaindo8.setVisible(false);
    clienteReto1.setVisible(false);
    clienteReto2.setVisible(false);
    clienteReto3.setVisible(false);
    clienteReto4.setVisible(false);
    clienteReto5.setVisible(false);
    clienteReto6.setVisible(false);
    clienteReto7.setVisible(false);
    clienteEntrouFila1.setVisible(false);
    clienteEntrouFila2.setVisible(false);
    clienteEntrouFila3.setVisible(false);
    clienteEntrouFila4.setVisible(false);
    clienteEntrouFila5.setVisible(false);
    clienteEntrouFila6.setVisible(false);
    clienteEntrouFila7.setVisible(false);
    clienteEntrouFila8.setVisible(false);
    clienteEntrouFila9.setVisible(false);
    clienteEntrouFila10.setVisible(false);
    barbeiroCortandoCabelo.setVisible(false);
    barbeiroDormindo.setVisible(true);
  }

  /*****************************************************************************
  * Metodo: barbeiroCortando
  * Funcao: Atualiza o estado visual do barbeiro para mostrar que ele esta
  * cortando o cabelo
  * Parametros: nenhum
  * Retorno: nenhum
  *****************************************************************************/
  public void barbeiroCortando() {
    barbeiroCortandoCabelo.setVisible(true);  // Imagem do barbeiro cortando cabelo aparece
    barbeiroDormindo.setVisible(false);      // Imagem do barbeiro dormindo some
    clienteSendoAtendido.setVisible(true);  // Imagem do cliente sendo atendido aparece
  }

  /*****************************************************************************
  * Metodo: barbeiroDormindo
  * Funcao: Atualiza o estado visual do barbeiro para mostrar que ele esta dormindo
  * Parametros: nenhum
  * Retorno: nenhum
  *****************************************************************************/
  public void barbeiroDormindo() {
    barbeiroCortandoCabelo.setVisible(false);  // Esconde a imagem do barbeiro cortando cabelo
    barbeiroDormindo.setVisible(true);        // Exibe a imagem do barbeiro dormindo
    clienteSendoAtendido.setVisible(false);  // Esconde a imagem do cliente sendo atendido
  }

  /*****************************************************************************
  * Metodo: clienteConferindo
  * Funcao: imagem do cliente que entrou e esta conferindo aparece
  * Parametros: nenhum
  * Retorno: nenhum
  *****************************************************************************/
  public void clienteConferindo() {
    clienteEntrouConferindo.setVisible(true);
  }

  /*****************************************************************************
  * Metodo: clienteTriste
  * Funcao: imagem do cliente que entrou e esta conferindo some e do cliente triste aparece
  * Parametros: nenhum
  * Retorno: nenhum
  *****************************************************************************/
  public void clienteTriste() {
    clienteEntrouConferindo.setVisible(false);
    clienteTriste.setVisible(true);
  }

  /*****************************************************************************
  * Metodo: clienteTristeSaiu
  * Funcao: imagem do cliente triste some
  * Parametros: nenhum
  * Retorno: nenhum
  *****************************************************************************/
  public void clienteTristeSaiu() {
    clienteTriste.setVisible(false);
  }

  /*****************************************************************************
  * Metodo: getVelocidadeBarbeiro
  * Funcao: Obtem a velocidade do barbeiro a partir do valor do slider e converte para int
  * Parametros: nenhum
  * Retorno: int - velocidade do barbeiro
  *****************************************************************************/
  public int velocidadeBarbeiro() {
    double velocidadeAntiga = velocidadeBarbeiro.getValue();
    int velocidadeConvertida = (int) velocidadeAntiga;
    return velocidadeConvertida;
  }

  /*****************************************************************************
  * Metodo: velocidadeClientes
  * Funcao: Obtem a velocidade dos clientes a partir do valor do slider e converte para int
  * Parametros: nenhum
  * Retorno: int - velocidade dos clientes
  *****************************************************************************/
  public int velocidadeClientes() {
    double velocidadeAntiga = velocidadeClientes.getValue();
    int velocidadeConvertida = (int) velocidadeAntiga;
    return velocidadeConvertida;
  }

  // ----------------------------METODOS GETTERS----------------------------------//
  /*****************************************************************************
  * Metodo: METODOS GETTERS
  * Funcao: Obtem a imagem dos clientes e do barbeiro
  * Parametros: nenhum
  * Retorno: nenhum
  *****************************************************************************/

  public ImageView getPrimeiroClienteFila() {return primeiroClienteFila;}

  public ImageView getSegundoClienteFila() {return segundoClienteFila;}

  public ImageView getTerceiroClienteFila() {return terceiroClienteFila;}

  public ImageView getQuartoClienteFila() {return quartoClienteFila;}

  public ImageView getUltimoClienteFila() {return ultimoClienteFila;}

  public ImageView getClienteSendoAtendido() {return clienteSendoAtendido;}

  public ImageView getClienteSaindo1() {return clienteSaindo1;}

  public ImageView getClienteSaindo2() {return clienteSaindo2;}

  public ImageView getClienteSaindo3() {return clienteSaindo3;}

  public ImageView getClienteSaindo4() {return clienteSaindo4;}

  public ImageView getClienteSaindo5() {return clienteSaindo5;}

  public ImageView getClienteSaindo6() {return clienteSaindo6;}

  public ImageView getClienteSaindo7() {return clienteSaindo7;}

  public ImageView getClienteSaindo8() {return clienteSaindo8;}

  public ImageView getClienteReto1() {return clienteReto1;}

  public ImageView getClienteReto2() {return clienteReto2;}

  public ImageView getClienteReto3() {return clienteReto3;}

  public ImageView getClienteReto4() {return clienteReto4;}

  public ImageView getClienteReto5() {return clienteReto5;}

  public ImageView getClienteReto6() {return clienteReto6;}

  public ImageView getClienteReto7() {return clienteReto7;}

  public ImageView getClienteEntrouConferindo() {return clienteEntrouConferindo;}

  public ImageView getClienteTriste() {return clienteTriste;}

  public ImageView getClienteEntrouFila1() {return clienteEntrouFila1;}

  public ImageView getClienteEntrouFila2() {return clienteEntrouFila2;}

  public ImageView getClienteEntrouFila3() {return clienteEntrouFila3;}

  public ImageView getClienteEntrouFila4() {return clienteEntrouFila4;}

  public ImageView getClienteEntrouFila5() {return clienteEntrouFila5;}
  
  public ImageView getClienteEntrouFila6() {return clienteEntrouFila6;}

  public ImageView getClienteEntrouFila7() {return clienteEntrouFila7;}

  public ImageView getClienteEntrouFila8() {return clienteEntrouFila8;}

  public ImageView getClienteEntrouFila9() {return clienteEntrouFila9;}

  public ImageView getClienteEntrouFila10() {return clienteEntrouFila10;}
}
