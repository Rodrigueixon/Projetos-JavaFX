/******************************************************************************************************
* Autor: Rodrigo Ferreira Bento Aguiar
* Matricula: 202310594
* Inicio: 09/06/2024
* Ultima alteracao: 13/06/2024
* Nome: GerarCliente
* Descricao: Contem a logica para a criacao de novos cliente para a barbearia.
******************************************************************************************************* */

package model;

import controller.ControleGeral;

public class GerarClientes extends Thread{

  // Referencia ao controle geral
  private ControleGeral controle;
  private volatile boolean rodando = true;

  /******************************************************************************
  * Metodo: GerarClientes
  * Funcao: Construtor da classe GerarClientes
  * Parametros: ControleGeral controle
  * Retorno: nenhum
  *****************************************************************************/
  public GerarClientes(ControleGeral controle) {
    this.controle = controle;
  }

  /******************************************************************************
  * Metodo: run
  * Funcao: gera continuamente clientes e inicia suas threads
  * Parametros: nenhum
  * Retorno: nenhum
  *****************************************************************************/
  public void run(){
    // Loop infinito para simular a chegada continua de clientes
    while (rodando) {
      //se o valor do slider for igual a 3000, nao entra mais clientes
      if(controle.velocidadeClientes() == 3000){
        while (controle.velocidadeClientes() == 3000){
          try {
            sleep(1);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }

      //controla a velocidade de chegada dos clientes
      try {
        sleep(controle.velocidadeClientes()); 
      } catch (Exception e) {
        e.printStackTrace();
      }
      
      Cliente cliente = new Cliente(controle); // Cria um novo cliente passando o controle geral
      cliente.setDaemon(true);                // A thread termina quando o programa principal terminar
      cliente.start();                       // Inicia a thread do cliente

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
