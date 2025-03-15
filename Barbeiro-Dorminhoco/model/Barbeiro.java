/******************************************************************************************************
* Autor: Rodrigo Ferreira Bento Aguiar
* Matricula: 202310594
* Inicio: 07/06/2024
* Ultima alteracao: 17/06/2024
* Nome: Barbeiro
* Descricao: contem a logica para atender clientes, utilizando semaforos para gerenciar o acesso
*            a recursos compartilhados e evitar condicoes de corrida.
******************************************************************************************************* */

package model;

import controller.ControleGeral;
import static controller.ControleGeral.*;

public class Barbeiro extends Thread {

  // Referencia ao controle geral 
  private ControleGeral controle;

  private volatile boolean rodando = true;

  /******************************************************************************
  * Metodo: Barbeiro
  * Funcao: Construtor da classe Barbeiro
  * Parametros: ControleGeral controle
  * Retorno: nenhum
  *****************************************************************************/
  public Barbeiro(ControleGeral controle) {
    this.controle = controle;
  }

  /*****************************************************************************
  * Metodo: run
  * Funcao: Executa o comportamento do Barbeiro, que espera por clientes,
  *         atende-os e corta seu cabelo. 
  * Parametros: nenhum
  * Retorno: nenhum
  ****************************************************************************/
  public void run() {
    // Loop infinito para simular o funcionamento continuo do barbeiro
    while (rodando) {
      
      try {
        clientes.acquire();           // O barbeiro adquire um cliente
        mutex.acquire();             // Adquire o mutex para garantir acesso exclusivo a variavel 'esperando'
        esperando = esperando - 1;  // Decrementa o numero de clientes esperando
        barbeiro.release();        // Libera o barbeiro para atender o cliente
        mutex.release();          // Libera o mutex apos atualizar a variavel 'esperando'
        cortarCabelo();          // Inicia o processo de cortar o cabelo do cliente
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /*****************************************************************************
  * Metodo: cortarCabelo
  * Funcao: processo de cortar o cabelo de um cliente. O barbeiro corta o cabelo do 
  *         cliente, depois o cliente sai da cadeira e o barbeiro volta a dormir.
  * Parametros: nenhum
  * Retorno: nenhum
  ****************************************************************************/
  private void cortarCabelo() {
    boolean cortandoCabelo = true; // Variavel para controlar o loop de corte de cabelo

    // Loop que continua enquanto o barbeiro estiver cortando cabelo
    while(cortandoCabelo){
      if(controle.velocidadeBarbeiro() == 800){
        controle.barbeiroDormindo();
        while (controle.velocidadeBarbeiro() == 800){ 
          try {
            sleep(1);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }

      // Verifica se ha um cliente sendo atendido
      if(controle.getClienteSendoAtendido().isVisible()){
        controle.barbeiroCortando();        // Muda o estado do barbeiro para cortando
        
        try {
          sleep(controle.velocidadeBarbeiro());
        } catch (Exception e) {
          e.printStackTrace();
        }
        controle.barbeiroDormindo();  // Muda o estado do barbeiro para dormindo
        clienteSaindo();             // Cliente sai da barbearia
        cortandoCabelo = false;     // Sai do loop, terminando o processo de corte de cabelo
      }
    }
  }

  /*****************************************************************************
  * Metodo: clienteSaido
  * Funcao: animacao do cliente saindo da barbearia
  * Parametros: nenhum
  * Retorno: nenhum
  ****************************************************************************/
  private void clienteSaindo() {
    try {
      controle.getClienteSaindo1().setVisible(true);
      sleep(50);
      controle.getClienteSaindo1().setVisible(false);
      controle.getClienteSaindo2().setVisible(true);
      sleep(50);
      controle.getClienteSaindo2().setVisible(false);
      controle.getClienteSaindo3().setVisible(true);
      sleep(50);
      controle.getClienteSaindo3().setVisible(false);
      controle.getClienteSaindo4().setVisible(true);
      sleep(50);
      controle.getClienteSaindo4().setVisible(false);
      controle.getClienteSaindo5().setVisible(true);
      sleep(50);
      controle.getClienteSaindo5().setVisible(false);
      controle.getClienteSaindo6().setVisible(true);
      sleep(50);
      controle.getClienteSaindo6().setVisible(false);
      controle.getClienteSaindo7().setVisible(true);
      sleep(50);
      controle.getClienteSaindo7().setVisible(false);
      controle.getClienteSaindo8().setVisible(true);
      sleep(50);
      controle.getClienteSaindo8().setVisible(false);

      } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /******************************************************************************
  * Metodo: pararDeRodar
  * Funcao: Para a execucao da thread e interrompe o seu funcionamento
  * Parametros: nenhum
  * Retorno: nenhum
  *****************************************************************************/
  public void pararDeRodar() {
    rodando = false;   // Define a variavel rodando como falsa, sinalizando para a thread parar
  }
}
