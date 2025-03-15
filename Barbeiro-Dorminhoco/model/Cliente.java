/******************************************************************************************************
* Autor: Rodrigo Ferreira Bento Aguiar
* Matricula: 202310594
* Inicio: 08/06/2024
* Ultima alteracao: 17/06/2024
* Nome: Cliente
* Descricao: Contem a logica para a chegada do cliente na barbearia, esperando 
*            na fila ou saindo caso nao haja lugares disponiveis.
******************************************************************************************************* */

package model;

import controller.ControleGeral;
import static controller.ControleGeral.*;

public class Cliente extends Thread{

  private ControleGeral controle;

  /******************************************************************************
  * Metodo: Cliente
  * Funcao: Construtor da classe Cliente
  * Parametros: ControleGeral controle
  * Retorno: nenhum
  *****************************************************************************/
  public Cliente(ControleGeral controle){
    this.controle = controle;
  }

  /*****************************************************************************
  * Metodo: run
  * Funcao: Executa o comportamento do Cliente, que tenta entrar na barbearia, 
 *          aguarda se houver lugares disponiveis, ou sai se estiver cheia.
  * Parametros: nenhum
  * Retorno: nenhum
  ****************************************************************************/
  public void run(){
    try {
      // entra na regiao critica
      mutex.acquire();
      // Se nao houver cadeiras livres, o cliente vai embora
      if(esperando < CADEIRAS) {
        esperando = esperando + 1;  // Incrementa a contagem de clientes esperando
        clienteEntrandoNaFila();   // Se houver, o cliente entra na fila
        clientes.release();       // semaforo clientes indica que um cliente esta esperando
        mutex.release();         // semaforo mutex libera para que outros clientes entrem na seccao critica
        barbeiro.acquire();     // Tenta adquirir o semaforo barbeiro para ser atendido
        cortandoCabelo();      // Senta-se e eh atendido
      }else {
        mutex.release();     // semaforo mutex libera para que outros clientes entrem na seccao critica
        saiTriste();        // barbearia esta cheia e cliente sai triste 
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /*****************************************************************************
  * Metodo: saiTriste
  * Funcao: Simula o comportamento de um cliente que sai triste da barbearia 
  * Parametros: nenhum
  * Retorno: nenhum
  ****************************************************************************/
  private void saiTriste() {
    if(controle.getUltimoClienteFila().isVisible() && !(controle.getClienteEntrouConferindo().isVisible()) && !(controle.getClienteTriste().isVisible())){
      try {
        sleep(300);
        controle.clienteConferindo();
        sleep(300);
        controle.clienteTriste();
        sleep(300);
        controle.clienteTristeSaiu();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /****************************************************************************
  * Metodo: cortandoCabelo
  * Funcao: mostrar o cliente sendo atendido e liberando o lugar na fila
  * Parametros: nenhum
  * Retorno: nenhum
  ****************************************************************************/
  private void cortandoCabelo() {
    if(controle.velocidadeBarbeiro() == 800){
      while (controle.velocidadeBarbeiro() == 800){
        try {
          sleep(1);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    
    //o ultimo lugar fica vago quando o primeiro cliente eh atendido
    if (controle.getUltimoClienteFila().isVisible()){
      controle.getUltimoClienteFila().setVisible(false);
    //o quarto lugar fica vago quando o primeiro cliente eh atendido
    }else if(controle.getQuartoClienteFila().isVisible()){
      controle.getQuartoClienteFila().setVisible(false);
      
    //o terceiro lugar fica vago quando o primeiro cliente eh atendido
    }else if(controle.getTerceiroClienteFila().isVisible()){
      controle.getTerceiroClienteFila().setVisible(false);
      
    //o segundo lugar fica vago quando o primeiro cliente eh atendido
    }else if(controle.getSegundoClienteFila().isVisible()){
      controle.getSegundoClienteFila().setVisible(false);
      
    //o primeiro lugar fica vago quando ele vai ser atendido
    }else if(controle.getPrimeiroClienteFila().isVisible()){
      controle.getPrimeiroClienteFila().setVisible(false);
    }

    //animacao do cliente indo ate a cadeira do barbeiro 
    if(controle.getPrimeiroClienteFila().isVisible()){
      saindoDaFilaPraCadeiraDoBarbeiro();
    } 
  
    // mostrar o cliente sendo atendido pelo barbeiro
    controle.barbeiroCortando();
  }

  /*****************************************************************************
  * Metodo: clienteEntrandoNaFila
  * Funcao: mostrar o cliente entrando na fila de espera
  * Parametros: nenhum
  * Retorno: nenhum
  ****************************************************************************/
  private void clienteEntrandoNaFila(){
    if(controle.velocidadeClientes() != 3000){
      try {

        //Se o primeiro banco estiver vazio, o cliente senta
        if(!controle.getPrimeiroClienteFila().isVisible()){
          controle.getClienteEntrouFila1().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila1().setVisible(false);
          controle.getClienteEntrouFila2().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila2().setVisible(false);
          controle.getClienteEntrouFila3().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila3().setVisible(false);
          controle.getClienteEntrouFila4().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila4().setVisible(false);
          controle.getClienteEntrouFila5().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila5().setVisible(false);
          controle.getClienteEntrouFila6().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila6().setVisible(false);
          controle.getClienteEntrouFila7().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila7().setVisible(false);
          controle.getClienteEntrouFila8().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila8().setVisible(false);
          controle.getClienteEntrouFila9().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila9().setVisible(false);
          controle.getClienteEntrouFila10().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila10().setVisible(false);
  
          controle.getPrimeiroClienteFila().setVisible(true);

        //Se o segundo banco estiver vazio, o cliente senta
        }else if(!controle.getSegundoClienteFila().isVisible()){
          controle.getClienteEntrouFila1().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila1().setVisible(false);
          controle.getClienteEntrouFila2().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila2().setVisible(false);
          controle.getClienteEntrouFila3().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila3().setVisible(false);
          controle.getClienteEntrouFila4().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila4().setVisible(false);
          controle.getClienteEntrouFila5().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila5().setVisible(false);
          controle.getClienteEntrouFila6().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila6().setVisible(false);
          controle.getClienteEntrouFila7().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila7().setVisible(false);
          controle.getClienteEntrouFila8().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila8().setVisible(false);
          controle.getSegundoClienteFila().setVisible(true);

        //Se o terceiro banco estiver vazio, o cliente senta
        }else if(!controle.getTerceiroClienteFila().isVisible()){
          controle.getClienteEntrouFila1().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila1().setVisible(false);
          controle.getClienteEntrouFila2().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila2().setVisible(false);
          controle.getClienteEntrouFila3().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila3().setVisible(false);
          controle.getClienteEntrouFila4().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila4().setVisible(false);
          controle.getClienteEntrouFila5().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila5().setVisible(false);
          controle.getTerceiroClienteFila().setVisible(true);

        //Se o quarto banco estiver vazio, o cliente senta
        }else if(!controle.getQuartoClienteFila().isVisible()){
          controle.getClienteEntrouFila1().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila1().setVisible(false);
          controle.getClienteEntrouFila2().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila2().setVisible(false);
          controle.getClienteEntrouFila3().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila3().setVisible(false);
          controle.getClienteEntrouFila4().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila4().setVisible(false);
          controle.getQuartoClienteFila().setVisible(true);
          
        //Se o ultimo banco estiver vazio, o cliente senta
        }else if(!controle.getUltimoClienteFila().isVisible()){
          controle.getClienteEntrouFila1().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila1().setVisible(false);
          controle.getClienteEntrouFila2().setVisible(true);
          sleep(50);
          controle.getClienteEntrouFila2().setVisible(false);
          controle.getUltimoClienteFila().setVisible(true);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  
  /*****************************************************************************
  * Metodo: saindoDaFilaPraCadeiraDoBarbeiro
  * Funcao: animacao do cliente saindo da fila e indo para a cadeira do barbeiro
  * Parametros: nenhum
  * Retorno: nenhum
  ****************************************************************************/
  private void saindoDaFilaPraCadeiraDoBarbeiro(){
    try {
      controle.getClienteReto1().setVisible(true);
      sleep(50);
      controle.getClienteReto1().setVisible(false);
      controle.getClienteReto2().setVisible(true);
      sleep(50);
      controle.getClienteReto2().setVisible(false);
      controle.getClienteReto3().setVisible(true);
      sleep(50);
      controle.getClienteReto3().setVisible(false);
      controle.getClienteReto4().setVisible(true);
      sleep(50);
      controle.getClienteReto4().setVisible(false);
      controle.getClienteReto5().setVisible(true);
      sleep(50);
      controle.getClienteReto5().setVisible(false);
      controle.getClienteReto6().setVisible(true);
      sleep(50);
      controle.getClienteReto6().setVisible(false);
      controle.getClienteReto7().setVisible(true);
      sleep(50);
      controle.getClienteReto7().setVisible(false);

      } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
