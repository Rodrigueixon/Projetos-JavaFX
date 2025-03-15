/* ******************************************************************************************************
* Autor: Rodrigo Ferreira Bento Aguiar
* Matricula: 202310594
* Inicio: 29/06/2024
* Ultima alteracao: 05/07/2024
* Nome: Carro3
* Descricao: Classe da thread do carro, que se move ao longo de um percurso predefinido, 
*            respeitando as zonas criticas controladas por semaforos.
******************************************************************************************************* */

package model;

import controller.ControleGeral;
import static controller.ControleGeral.*;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Carro3 extends Thread {

  private ControleGeral controle;
  private ImageView carro;

  private volatile boolean rodando = true;

  // Variavels das coordenadas do carro
  private int posicaoX;
  private int posicaoY;

  /******************************************************************************
   * Metodo: Carro3
   * Funcao: Construtor da classe Carro3
   * Parametros: ControleGeral controle, ImageView carro
   * Retorno: nenhum
   *****************************************************************************/
  public Carro3(ControleGeral controle, ImageView carro) {
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
    while (rodando) {
      try {
        moverDireita(27); // RH12
        semaforo2e3RC1.acquire(); // entrando na zona critica 1 entre o carro 2
        semaforo3e5RC1.acquire(); // entrando na zona critica 1 entre o carro 5
        moverDireita(93); // RH13
        semaforo3e7RC1.acquire(); // entrando na zona critica 1 entre o carro 7
        semaforo3e4RC2.acquire(); // entrando na zona critica 2 entre o carro 4
        semaforo3e6RC1.acquire(); // entrando na zona critica 1 entre o carro 6
        moverDireita(30); // RH13 e RH14 (Cruzamento)
        semaforo2e3RC1.release(); // saindo da zona critica 1 entre o carro 2
        semaforo3e4RC2.release(); // saindo da zona critica 2 entre o carro 4
        semaforo3e6RC1.release(); // saindo da zona critica 1 entre o carro 6
        moverDireita(50); // RH14
        moverDireita(30); // RH15
        semaforo3e7RC1.release(); // saindo da zona critica 1 entre o carro 7
        moverDireita(55); // RH15
        semaforo3e8RC1.acquire(); // entrando na zona critica 1 entre o carro 8
        semaforo3e6RC2.acquire(); // entrando na zona critica 2 entre o carro 6
        semaforo1e3RC1.acquire(); // entrando na zona critica 1 entre o carro 1
        semaforo2e3RC2.acquire(); // entrando na zona critica 2 entre o carro 2
        moverDireita(22); // RH15 e RV29 (Cruzamento)
        carro.setRotate(90);
        moverCima(30); // RV29
        semaforo3e5RC1.release(); // saindo da zona critica 1 entre o carro 5
        moverCima(80);
        semaforo3e8RC1.release(); // saindo da zona critica 1 entre o carro 8
        moverCima(70);
        carro.setRotate(360);
        moverEsquerda(70); // RH05
        moverEsquerda(80); // RH04
        semaforo3e4RC3.acquire(); // entrando na zona critica 3 entre o carro 4
        semaforo3e8RC2.acquire(); // entrando na zona critica 2 entre o carro 8
        moverEsquerda(30); // RH03
        semaforo3e6RC2.release(); // saindo da zona critica 2 entre o carro 6
        moverEsquerda(60); // RH03
        semaforo3e7RC2.acquire(); // entrando na zona critica 2 entre o carro 7
        moverEsquerda(30); // RH02
        semaforo3e8RC2.release(); // saindo da zona critica 2 entre o carro 8
        moverEsquerda(60); // RH02
        moverEsquerda(20); // RH01
        semaforo3e4RC3.release(); // saindo da zona critica 3 entre o carro 4
        moverEsquerda(85); // RH01
        carro.setRotate(270);
        moverBaixo(70);
        semaforo3e8RC3.acquire(); // entrando na zona critica 3 entre o carro 8
        moverBaixo(112);
        carro.setRotate(180);
        moverDireita(20); // RV04 e RH11 (Cruzamento)
        semaforo1e3RC1.release(); // saindo da zona critica 1 entre o carro 2
        semaforo2e3RC2.release(); // saindo da zona critica 2 entre o carro 2
        moverDireita(20); // RH11
        semaforo3e8RC3.release(); // saindo da zona critica 3 entre o carro 8
        moverDireita(30); // RH11
        semaforo3e4RC1.acquire(); // entrando na zona critica 1 entre o carro 4
        moverDireita(20); // RH12
        semaforo3e7RC2.release(); // saindo da zona critica 2 entre o carro 7
        semaforo3e4RC1.release(); // saindo da zona critica 1 entre o carro 4
        moverDireita(35); // RH12

        Platform.runLater(() -> carro.setLayoutX(120));
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
      if (controle.velocidadeCarro3() == 50) {
        while (controle.velocidadeCarro3() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setY(posicaoY++)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro3()); // Espera baseado na velocidade do carro
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
      if (controle.velocidadeCarro3() == 50) {
        while (controle.velocidadeCarro3() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setY(posicaoY--)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro3()); // Espera baseado na velocidade do carro
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
      if (controle.velocidadeCarro3() == 50) {
        while (controle.velocidadeCarro3() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setX(posicaoX--)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro3()); // Espera baseado na velocidade do carro
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
      if (controle.velocidadeCarro3() == 50) {
        while (controle.velocidadeCarro3() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setX(posicaoX++)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro3()); // Espera baseado na velocidade do carro
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
