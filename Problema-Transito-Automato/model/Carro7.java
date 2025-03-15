/* ******************************************************************************************************
* Autor: Rodrigo Ferreira Bento Aguiar
* Matricula: 202310594
* Inicio: 02/07/2024
* Ultima alteracao: 05/07/2024
* Nome: Carro7
* Descricao: Classe da thread do carro, que se move ao longo de um percurso predefinido, 
*            respeitando as zonas criticas controladas por semaforos.
******************************************************************************************************* */

package model;

import controller.ControleGeral;
import static controller.ControleGeral.*;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Carro7 extends Thread {

  private ControleGeral controle;
  private ImageView carro;

  private volatile boolean rodando = true;

  // Variavels das coordenadas do carro
  private int posicaoX;
  private int posicaoY;

  /******************************************************************************
   * Metodo: Carro7
   * Funcao: Construtor da classe Carro7
   * Parametros: ControleGeral controle, ImageView carro
   * Retorno: nenhum
   *****************************************************************************/
  public Carro7(ControleGeral controle, ImageView carro) {
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
        moverEsquerda(25); // RH17
        semaforo4e7RC4.acquire(); // entrando na zona critica 4 entre o carro 4
        moverEsquerda(15); // RH17 e RV08 (Cruzamento)
        carro.setRotate(90);
        moverCima(70); // RV08
        semaforo3e7RC2.acquire(); // entrando na zona critica 2 entre o carro 3
        moverCima(17); // RV08 e RH11 (Cruzamento)
        carro.setRotate(360);
        moverEsquerda(30); // RH11
        semaforo4e7RC4.release(); // saindo da zona critica 4 entre o carro 4
        moverEsquerda(35); // RH11
        semaforo7e8RC4.acquire(); // entrando na zona critica 4 entre o carro 8
        semaforo1e7RC1.acquire(); // entrando na zona critica 1 entre o carro 1
        semaforo2e7RC3.acquire(); // entrando na zona critica 3 entre o carro 2
        moverEsquerda(19); // RH11 e RV04 (Cruzamento)
        carro.setRotate(90);
        moverCima(70); // RV04
        moverCima(30); // RV05
        semaforo7e8RC4.release(); // saindo da zona critica 4 entre o carro 8
        moverCima(55); // RV05
        moverCima(20); // RV05 e RH01 (Cruzamento)
        carro.setRotate(180);
        moverDireita(70); // RH01
        semaforo4e7RC1.acquire(); // entrando na zona critica 1 entre o carro 4
        moverDireita(85); // RH02
        semaforo7e8RC5.acquire(); // entrando na zona critica 5 entre o carro 8
        moverDireita(18); // RH02 e RV15 (Cruzamento)
        carro.setRotate(270);
        moverBaixo(20); // RV15
        semaforo4e7RC1.release(); // saindo da zona critica 1 entre o carro 4
        semaforo1e7RC1.release(); // saindo da zona critica 1 entre o carro 1
        semaforo2e7RC3.release(); // saindo da zona critica 3 entre o carro 2
        semaforo3e7RC2.release(); // saindo da zona critica 2 entre o carro 3
        moverBaixo(45); // RV15
        moverBaixo(23); // RV15 e RH08 (Cruzamento)
        carro.setRotate(180);
        moverDireita(30); // RH08
        semaforo7e8RC5.release(); // saindo da zona critica 5 entre o carro 8
        moverDireita(40); // RH08
        semaforo7e8RC6.acquire(); // entrando na zona critica 6 entre o carro 8
        semaforo6e7RC2.acquire(); // entrando na zona critica 2 entre o carro 6
        semaforo4e7RC2.acquire(); // entrando na zona critica 2 entre o carro 4
        moverDireita(20); // RH08 e RV19 (Cruzamento)
        carro.setRotate(270);
        moverBaixo(30); // RV19
        semaforo7e8RC6.release(); // saindo da zona critica 6 entre o carro 8
        moverBaixo(40); // RV19
        semaforo5e7RC1.acquire(); // entrando na zona critica 1 entre o carro 5
        semaforo2e7RC1.acquire(); // entrando na zona critica 1 entre o carro 2
        semaforo3e7RC1.acquire(); // entrando na zona critica 1 entre o carro 3
        moverBaixo(20); // RV19 e RH14 (Cruzamento)
        carro.setRotate(180);
        moverDireita(30); // RH14
        semaforo6e7RC2.release(); // saindo da zona critica 2 entre o carro 6
        semaforo4e7RC2.release(); // saindo da zona critica 2 entre o carro 4
        semaforo2e7RC1.release(); // saindo da zona critica 1 entre o carro 2
        moverDireita(40); // RH14
        moverDireita(15); // RH14 e RV23 (Cruzamento)
        carro.setRotate(270);
        moverBaixo(30); // RV23
        semaforo5e7RC1.release(); // saindo da zona critica 1 entre o carro 5
        semaforo3e7RC1.release(); // saindo da zona critica 1 entre o carro 3
        moverBaixo(40); // RV23
        moverBaixo(15); // RV23 e RH20 (Cruzamento)
        carro.setRotate(180);
        moverDireita(65); // RH20
        semaforo6e7RC1.acquire(); // entrando na zona critica 2 entre o carro 5
        semaforo7e8RC1.acquire(); // entrando na zona critica 1 entre o carro 8
        semaforo2e7RC2.acquire(); // entrando na zona critica 2 entre o carro 2
        semaforo5e7RC2.acquire(); // entrando na zona critica 2 entre o carro 5
        semaforo1e7RC2.acquire(); // entrando na zona critica 2 entre o carro 1
        moverDireita(20); // RH20 e RV27 (Cruzamento)
        carro.setRotate(270);
        moverBaixo(65); // RV27
        moverBaixo(30); // RV26
        semaforo7e8RC1.release(); // saindo da zona critica 1 entre o carro 8
        moverBaixo(47); // RV26
        moverBaixo(23); // RV26 e RH30 (Cruzamento)
        carro.setRotate(360);
        moverEsquerda(70); // RH30
        moverEsquerda(90); // RH29
        semaforo7e8RC2.acquire(); // entrando na zona critica 2 entre o carro 8
        semaforo4e7RC3.acquire(); // entrando na zona critica 3 entre o carro 4
        moverEsquerda(12); // RH29 e RV16 (Cruzamento)
        carro.setRotate(90);
        moverCima(20); // RV16
        semaforo5e7RC2.release(); // saindo da zona critica 2 entre o carro 5
        semaforo1e7RC2.release(); // saindo da zona critica 2 entre o carro 1
        moverCima(45); // RV16
        moverCima(20); // RV16 e RH23 (Cruzamento)
        carro.setRotate(360);
        moverEsquerda(30); // RH23
        semaforo7e8RC2.release(); // saindo da zona critica 2 entre o carro 8
        semaforo6e7RC1.release(); // saindo da zona critica 1 entre o carro 6
        semaforo4e7RC3.release(); // saindo da zona critica 3 entre o carro 4
        semaforo2e7RC2.release(); // saindo da zona critica 2 entre o carro 2
        moverEsquerda(40); // RH23
        semaforo7e8RC3.acquire(); // entrando na zona critica 3 entre o carro 8
        semaforo5e7RC3.acquire(); // entrando na zona critica 3 entre o carro 5
        semaforo2e7RC4.acquire(); // entrando na zona critica 4 entre o carro 2
        moverEsquerda(20); // RH23 e RV12 (Cruzamento)
        carro.setRotate(90);
        moverCima(30); // RV12
        semaforo7e8RC3.release(); // saindo da zona critica 3 entre o carro 8
        moverCima(35); // RV12
        moverCima(15); // RV12 e RH17 (Cruzamento)
        carro.setRotate(360);
        moverEsquerda(20); // RH17
        semaforo5e7RC3.release(); // saindo da zona critica 3 entre o carro 5
        semaforo2e7RC4.release(); // saindo da zona critica 4 entre o carro 2
        moverEsquerda(27); // RH17

        Platform.runLater(() -> carro.setLayoutX(120));
        Platform.runLater(() -> carro.setLayoutY(268));
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
      if (controle.velocidadeCarro7() == 50) {
        while (controle.velocidadeCarro7() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setY(posicaoY++)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro7()); // Espera baseado na velocidade do carro
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
      if (controle.velocidadeCarro7() == 50) {
        while (controle.velocidadeCarro7() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setY(posicaoY--)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro7()); // Espera baseado na velocidade do carro
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
      if (controle.velocidadeCarro7() == 50) {
        while (controle.velocidadeCarro7() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setX(posicaoX--)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro7()); // Espera baseado na velocidade do carro
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
      if (controle.velocidadeCarro7() == 50) {
        while (controle.velocidadeCarro7() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setX(posicaoX++)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro7()); // Espera baseado na velocidade do carro
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
