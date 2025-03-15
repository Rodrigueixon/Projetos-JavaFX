/* ******************************************************************************************************
* Autor: Rodrigo Ferreira Bento Aguiar
* Matricula: 202310594
* Inicio: 27/06/2024
* Ultima alteracao: 05/07/2024
* Nome: Carro2
* Descricao: Classe da thread do carro, que se move ao longo de um percurso predefinido, 
*            respeitando as zonas criticas controladas por semaforos.
******************************************************************************************************* */

package model;

import controller.ControleGeral;
import static controller.ControleGeral.*;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Carro2 extends Thread {
  private ControleGeral controle;
  private ImageView carro;

  private volatile boolean rodando = true;

  // Variavels das coordenadas do carro
  private int posicaoX;
  private int posicaoY;

  /******************************************************************************
   * Metodo: Carro2
   * Funcao: Construtor da classe Carro2
   * Parametros: ControleGeral controle, ImageView carro
   * Retorno: nenhum
   *****************************************************************************/
  public Carro2(ControleGeral controle, ImageView carro) {
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
      semaforo2e3RC1.acquire(); // Dentro da zona critica 1 do carro 3
      semaforo2e5RC3.acquire(); // Dentro da zona critica 3 do carro 5
    } catch (Exception e) {
      e.printStackTrace();
    }
    while (rodando) {
      try {
        carro.setRotate(180);
        moverDireita(24);         // RH13
        semaforo2e6RC1.acquire(); // entrando na zona critica 1 entre o carro 6
        semaforo2e4RC2.acquire(); // entrando na zona critica 2 entre o carro 4
        semaforo2e7RC1.acquire(); // entrando na zona critica 1 entre o carro 7
        moverDireita(24);         // RH13 e RV18 (Cruzamento)
        semaforo2e3RC1.release(); // saindo da zona critica 1 entre o carro 3
        carro.setRotate(270);
        moverBaixo(20);           // RV18
        semaforo2e5RC3.release(); // saindo da zona critica 3 entre o carro 5
        semaforo2e7RC1.release(); // saindo da zona critica 1 entre o carro 7
        moverBaixo(50);           // RV18
        moverBaixo(80);           // RV17
        semaforo2e8RC1.acquire(); // entrando na zona critica 1 entre o carro 8
        semaforo2e7RC2.acquire(); // entrando na zona critica 2 entre o carro 7
        moverBaixo(85);           // RV16
        semaforo1e2RC1.acquire(); // entrando na zona critica 1 entre o carro 1
        semaforo2e5RC2.acquire(); // entrando na zona critica 2 entre o carro 5
        moverBaixo(20);           // RV16 e RH29 (Cruzamento)
        carro.setRotate(180);
        moverDireita(30);         // RH29
        semaforo2e4RC2.release(); // saindo da zona critica 2 entre o carro 4
        semaforo2e8RC1.release(); // saindo da zona critica 1 entre o carro 8
        moverDireita(140);
        carro.setRotate(90);
        moverCima(73);            // RV26
        semaforo2e8RC2.acquire(); // entrando na zona critica 2 entre o carro 8
        moverCima(80);            // RV27
        moverCima(20);            // RV28
        semaforo2e7RC2.release(); // saindo da zona critica 2 entre o carro 7
        moverCima(60);            // RV28
        semaforo2e3RC2.acquire(); // entrando na zona critica 2 entre o carro 3
        moverCima(45);            // RV29
        semaforo2e5RC2.release(); // saindo da zona critica 2 entre o carro 5
        moverCima(50);            // RV29
        moverCima(23);            // RV30
        semaforo2e8RC2.release(); // saindo da zona critica 2 entre o carro 8
        moverCima(57);            // RV30
        moverCima(25);            // RV30 e RH05
        carro.setRotate(360);
        moverEsquerda(70);        // RH05
        moverEsquerda(80);        // RH04
        semaforo2e4RC1.acquire(); // entrando na zona critica 1 entre o carro 4
        semaforo2e8RC3.acquire(); // entrando na zona critica 3 entre o carro 8
        moverEsquerda(30);        // RH03
        semaforo2e6RC1.release(); // saindo da zona critica 1 entre o carro
        moverEsquerda(60);        // RH03
        semaforo2e7RC3.acquire(); // entrando na zona critica 3 entre o carro 7
        moverEsquerda(30);        // RH02
        semaforo2e8RC3.release(); // saindo da zona critica 3 entre o carro 8
        moverEsquerda(60);        // RH02
        moverEsquerda(20);        // RH01
        semaforo2e4RC1.release(); // saindo da zona critica 1 entre o carro 4
        moverEsquerda(63);        // RH01
        moverEsquerda(20);        // RH01 e RV05 (Quina)
        carro.setRotate(270);
        moverBaixo(75);           // RV05
        semaforo2e8RC4.acquire(); // entrando na zona critica 4 entre o carro 8
        moverBaixo(85);           // RV04
        moverBaixo(40);           // RV04 e RV03 (Cruzamento)
        semaforo2e3RC2.release(); // saindo da zona critica 2 entre o carro 3
        semaforo2e7RC3.release(); // saindo da zona critica 3 entre o carro 7
        moverBaixo(180);          // RV03 e RV02
        semaforo2e8RC4.release(); // saindo da zona critica 4 entre o carro 8
        moverBaixo(55);           // RV02 e RV01
        carro.setRotate(180);
        moverDireita(75);         // RH26
        semaforo2e4RC3.acquire(); // entrando na zona critica 3 entre o carro 4
        moverDireita(75);         // RH27
        semaforo2e8RC5.acquire(); // entrando na zona critica 5 entre o carro 8
        semaforo2e5RC1.acquire(); // entrando na zona critica 1 entre o carro 5
        moverDireita(23);         // RH27 e RV11 (Cruzamento)
        carro.setRotate(90);
        semaforo1e2RC1.release(); // saindo da zona critica 1 entre o carro 1
        semaforo2e4RC3.release(); // saindo da zona critica 3 entre o carro 4
        moverCima(70);            // RV11
        semaforo2e7RC4.acquire(); // entrando na zona critica 4 entre o carro 7
        moverCima(25);            // RV12
        semaforo2e8RC5.release(); // saindo da zona critica 5 entre o carro 8
        moverCima(50);            // RV12
        moverCima(29);            // RV13
        semaforo2e7RC4.release(); // saindo da zona critica 4 entre o carro 7
        moverCima(55);            // RV13
        semaforo2e3RC1.acquire(); // entrando na zona critica 1 entre o carro 3
        semaforo2e5RC3.acquire(); // entrando na zona critica 3 entre o carro 5
        moverCima(23);            // RV13 e RH13 (Cruzamento)
        semaforo2e5RC1.release(); // saindo da zona critica 1 entre o carro 5
        carro.setRotate(180);
        moverDireita(42);         // RH13

        Platform.runLater(() -> carro.setLayoutX(212));
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
      if (controle.velocidadeCarro2() == 50) {
        while (controle.velocidadeCarro2() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setY(posicaoY++)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro2()); // Espera baseado na velocidade do carro
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
      if (controle.velocidadeCarro2() == 50) {
        while (controle.velocidadeCarro2() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setY(posicaoY--)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro2()); // Espera baseado na velocidade do carro
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
      if (controle.velocidadeCarro2() == 50) {
        while (controle.velocidadeCarro2() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setX(posicaoX--)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro2()); // Espera baseado na velocidade do carro
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
      if (controle.velocidadeCarro2() == 50) {
        while (controle.velocidadeCarro2() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setX(posicaoX++)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro2()); // Espera baseado na velocidade do carro
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
