/* ******************************************************************************************************
* Autor: Rodrigo Ferreira Bento Aguiar
* Matricula: 202310594
* Inicio: 01/07/2024
* Ultima alteracao: 05/07/2024
* Nome: Carro5
* Descricao: Classe da thread do carro, que se move ao longo de um percurso predefinido, 
*            respeitando as zonas criticas controladas por semaforos.
******************************************************************************************************* */

package model;

import controller.ControleGeral;
import static controller.ControleGeral.*;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Carro5 extends Thread {

  private ControleGeral controle;
  private ImageView carro;

  private volatile boolean rodando = true;

  // Variavels das coordenadas do carro
  private int posicaoX;
  private int posicaoY;

  /******************************************************************************
   * Metodo: Carro5
   * Funcao: Construtor da classe Carro5
   * Parametros: ControleGeral controle, ImageView carro
   * Retorno: nenhum
   *****************************************************************************/
  public Carro5(ControleGeral controle, ImageView carro) {
    this.controle = controle;
    this.carro = carro;
  }

  /*****************************************************************************
   * Metodo: run
   * Funcao: Controla o movimento do carro em um loop continuo, movendo em
   * diferentes direcoes.
   * Parametros: nenhum
   * Retorno: nenhum
   ****************************************************************************/
  public void run() {
    try {
      semaforo3e5RC1.acquire(); // Dentro da zona critica do carro 3
    } catch (Exception e) {
      e.printStackTrace();
    }
    while (rodando) {
      try {
        carro.setRotate(180);
        moverDireita(20); // RH15
        semaforo5e8RC1.acquire(); // entrando na zona critica 1 entre o carro 8
        semaforo1e5RC1.acquire(); // entrando na zona critica 1 entre o carro 1
        semaforo2e5RC2.acquire(); // entrando na zona critica 1 entre o carro 2
        semaforo5e6RC1.acquire(); // entrando na zona critica 1 entre o carro 2
        moverDireita(20); // RH15 e RV28 (Cruzamento)
        carro.setRotate(270);
        moverBaixo(30); // RV28
        semaforo3e5RC1.release(); // saindo da zona critica 1 entre o carro 3
        moverBaixo(40); // RV28
        semaforo5e7RC2.acquire(); // entrando na zona critica 2 entre o carro 7
        moverBaixo(80); // RV27
        moverBaixo(30); // RV26
        semaforo5e8RC1.release(); // saindo da zona critica 1 entre o carro 8
        moverBaixo(55); // RV26
        moverBaixo(20); // RV26 e RH30 (Cruzamento)
        carro.setRotate(360);
        moverEsquerda(65); // RH30
        moverEsquerda(85); // RH29
        semaforo5e8RC2.acquire(); // entrando na zona critica 2 entre o carro 8
        semaforo4e5RC2.acquire(); // entrando na zona critica 2 entre o carro 4
        moverEsquerda(35); // RH28
        semaforo5e7RC2.release(); // saindo da zona critica 2 entre o carro 7
        semaforo2e5RC2.release(); // saindo da zona critica 2 entre o carro 2
        semaforo5e6RC1.release(); // saindo da zona critica 1 entre o carro 6
        moverEsquerda(52); // RH28
        semaforo2e5RC1.acquire(); // entrando na zona critica 1 entre o carro 2
        moverEsquerda(23); // RH28 e RV11 (Cruzamento)
        carro.setRotate(90);
        moverCima(20); // RV11
        semaforo1e5RC1.release(); // saindo da zona critica 1 entre o carro 1
        semaforo4e5RC2.release(); // saindo da zona critica 2 entre o carro 4
        moverCima(45); // RV11
        semaforo5e7RC3.acquire(); // entrando na zona critica 3 entre o carro 7
        moverCima(30); // RV12
        semaforo5e8RC2.release(); // saindo da zona critica 2 entre o carro 8
        moverCima(55); // RV12
        moverCima(30); // RV13
        semaforo5e7RC3.release(); // saindo da zona critica 3 entre o carro 7
        moverCima(55); // RV13
        semaforo3e5RC1.acquire(); // entrando na zona critica 1 entre o carro 3
        semaforo2e5RC3.acquire(); // entrando na zona critica 3 entre o carro 2
        moverCima(20); // RV13 e RH13 (Cruzamento)
        semaforo2e5RC1.release(); // saindo da zona critica 1 entre o carro 2
        carro.setRotate(180);
        moverDireita(75); // RH13
        semaforo5e6RC2.acquire(); // entrando na zona critica 2 entre o carro 6
        semaforo5e7RC1.acquire(); // entrando na zona critica 1 entre o carro 7
        semaforo4e5RC1.acquire(); // entrando na zona critica 1 entre o carro 4
        moverDireita(25); // RH14
        semaforo2e5RC3.release(); // saindo da zona critica 3 entre o carro 2
        semaforo5e6RC2.release(); // saindo da zona critica 2 entre o carro 6
        semaforo4e5RC1.release(); // saindo da zona critica 1 entre o carro 4
        moverDireita(65); // RH14
        moverDireita(30); // RH15
        semaforo5e7RC1.release(); // saindo da zona critica 1 entre o carro 7
        moverDireita(32); // RH15

        Platform.runLater(() -> carro.setLayoutX(390));
        Platform.runLater(() -> carro.setLayoutY(180));
        posicaoX = 0;
        posicaoY = 0;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /*****************************************************************************
   * Metodo: moverBaixo
   * Funcao: Move o carro para baixo por um determinado numero de pixels.
   * Parametros: pixels - o numero de pixels para mover
   * Retorno: nenhum
   *****************************************************************************/
  private void moverBaixo(int pixels) {
    for (int i = 0; i <= pixels; i++) {
      if (controle.velocidadeCarro5() == 50) {
        while (controle.velocidadeCarro5() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setY(posicaoY++)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro5()); // Espera baseado na velocidade do carro
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /*****************************************************************************
   * Metodo: moverCima
   * Funcao: Move o carro para cima por um determinado numero de pixels.
   * Parametros: pixels - o numero de pixels para mover
   * Retorno: nenhum
   *****************************************************************************/
  private void moverCima(int pixels) {
    for (int i = 0; i <= pixels; i++) {
      if (controle.velocidadeCarro5() == 50) {
        while (controle.velocidadeCarro5() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setY(posicaoY--)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro5()); // Espera baseado na velocidade do carro
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /*****************************************************************************
   * Metodo: moverEsquerda
   * Funcao: Move o carro para a esquerda por um determinado numero de pixels.
   * Parametros: pixels - o numero de pixels para mover
   * Retorno: nenhum
   *****************************************************************************/
  public void moverEsquerda(int pixels) {
    for (int i = 0; i <= pixels; i++) {
      if (controle.velocidadeCarro5() == 50) {
        while (controle.velocidadeCarro5() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setX(posicaoX--)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro5()); // Espera baseado na velocidade do carro
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /*****************************************************************************
   * Metodo: moverDireita
   * Funcao: Move o carro para a direita por um determinado numero de pixels.
   * Parametros: pixels - o numero de pixels para mover
   * Retorno: nenhum
   *****************************************************************************/
  public void moverDireita(int pixels) {
    for (int i = 0; i <= pixels; i++) {
      if (controle.velocidadeCarro5() == 50) {
        while (controle.velocidadeCarro5() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setX(posicaoX++)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro5()); // Espera baseado na velocidade do carro
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /******************************************************************************
   * Metodo: pararDeRodar
   * Funcao: Para a execucao da thread e interrompe o seu funcionamento
   * Parametros: nenhum
   * Retorno: nenhum
   *****************************************************************************/
  public void pararDeRodar() {
    rodando = false; // Define a variavel rodando como falsa, sinalizando para a thread parar
  }
}
