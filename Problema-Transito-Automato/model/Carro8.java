/* ******************************************************************************************************
* Autor: Rodrigo Ferreira Bento Aguiar
* Matricula: 202310594
* Inicio: 03/07/2024
* Ultima alteracao: 05/07/2024
* Nome: Carro8
* Descricao: Classe da thread do carro, que se move ao longo de um percurso predefinido, 
*            respeitando as zonas criticas controladas por semaforos.
******************************************************************************************************* */

package model;

import controller.ControleGeral;
import static controller.ControleGeral.*;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Carro8 extends Thread {

  private ControleGeral controle;
  private ImageView carro;

  private volatile boolean rodando = true;

  // Variavels das coordenadas do carro
  private int posicaoX;
  private int posicaoY;

  /******************************************************************************
   * Metodo: Carro8
   * Funcao: Construtor da classe Carro8
   * Parametros: ControleGeral controle, ImageView carro
   * Retorno: nenhum
   *****************************************************************************/
  public Carro8(ControleGeral controle, ImageView carro) {
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
        moverEsquerda(20); // RH22
        semaforo4e8RC3.acquire(); // entrando na zona critica 3 entre o carro 4
        moverEsquerda(35); // RH21
        semaforo4e8RC3.release(); // saindo da zona critica 3 entre o carro 4
        moverEsquerda(50); // RH21
        semaforo1e8RC2.acquire(); // entrando na zona critica 2 entre o carro 1
        semaforo2e8RC4.acquire(); // entrando na zona critica 4 entre o carro 2
        moverEsquerda(20); // RH21 e RV02 (Cruzamento)
        carro.setRotate(90);
        moverCima(65); // RV02
        moverCima(80); // RV03
        semaforo7e8RC4.acquire(); // entrando na zona critica 4 entre o carro 7
        semaforo3e8RC3.acquire(); // entrando na zona critica 3 entre o carro 3
        moverCima(95); // RV04
        moverCima(22); // RV04 e RH06 (Cruzamento)
        carro.setRotate(-360);
        moverDireita(30); // RH06
        semaforo7e8RC4.release(); // saindo da zona critica 4 entre o carro 7
        semaforo3e8RC3.release(); // saindo da zona critica 3 entre o carro 3
        semaforo2e8RC4.release(); // saindo da zona critica 4 entre o carro 2
        semaforo1e8RC2.release(); // saindo da zona critica 2 entre o carro 1
        moverDireita(35); // RH06
        semaforo4e8RC4.acquire(); // entrando na zona critica 4 entre o carro 4
        moverDireita(25); // RH07
        semaforo4e8RC4.release(); // saindo da zona critica 4 entre o carro 4
        moverDireita(60); // RH07
        semaforo7e8RC5.acquire(); // entrando na zona critica 5 entre o carro 7
        moverDireita(23); // RH07 e RV15 (Cruzamento)
        carro.setRotate(90);
        moverCima(65); // RV15
        semaforo4e8RC1.acquire(); // entrando na zona critica 1 entre o carro 4
        semaforo6e8RC3.acquire(); // entrando na zona critica 3 entre o carro 6
        semaforo3e8RC2.acquire(); // entrando na zona critica 2 entre o carro 3
        semaforo2e8RC3.acquire(); // entrando na zona critica 3 entre o carro 2
        semaforo1e8RC1.acquire(); // entrando na zona critica 1 entre o carro 1
        moverCima(20); // RV15 e RH03 (Cruzamento)
        carro.setRotate(-360);
        moverDireita(30); // RH03
        semaforo7e8RC5.release(); // saindo da zona critica 5 entre o carro 7
        moverDireita(35); // RH03
        moverDireita(25); // RH03 e RV20 (Cruzamento)
        carro.setRotate(270);
        moverBaixo(30); // RV20
        semaforo3e8RC2.release(); // saindo da zona critica 2 entre o carro 3
        semaforo2e8RC3.release(); // saindo da zona critica 3 entre o carro 2
        semaforo1e8RC1.release(); // saindo da zona critica 1 entre o carro 1
        moverBaixo(30); // RV20
        semaforo7e8RC6.acquire(); // entrando na zona critica 6 entre o carro 7
        moverBaixo(25); // RV20 e RH09 (Cruzamento)
        carro.setRotate(-360);
        moverDireita(25); // RH09
        semaforo7e8RC6.release(); // saindo da zona critica 6 entre o carro 7
        semaforo6e8RC3.release(); // saindo da zona critica 3 entre o carro 6
        semaforo4e8RC1.release(); // saindo da zona critica 1 entre o carro 4
        moverDireita(40); // RH09
        moverDireita(73); // RH10
        semaforo6e8RC1.acquire(); // entrando na zona critica 1 entre o carro 6
        semaforo3e8RC1.acquire(); // entrando na zona critica 1 entre o carro 3
        semaforo2e8RC2.acquire(); // entrando na zona critica 2 entre o carro 2
        semaforo1e8RC4.acquire(); // entrando na zona critica 4 entre o carro 1
        moverDireita(30); // RH10 e RV29 (Cruzamento)
        carro.setRotate(270);
        moverBaixo(70); // RV29
        semaforo5e8RC1.acquire(); // entrando na zona critica 1 entre o carro 5
        moverBaixo(30); // RV28
        semaforo3e8RC1.release(); // saindo da zona critica 1 entre o carro 3
        moverBaixo(60); // RV28
        semaforo7e8RC1.acquire(); // entrando na zona critica 5 entre o carro 7
        moverBaixo(83); // RV27
        moverBaixo(20); // RV27 e RH25 (Cruzamento)
        carro.setRotate(360);
        moverEsquerda(30); // RH25
        semaforo7e8RC1.release(); // saindo da zona critica 1 entre o carro 7
        semaforo6e8RC1.release(); // saindo da zona critica 1 entre o carro 6
        semaforo5e8RC1.release(); // saindo da zona critica 1 entre o carro 5
        semaforo2e8RC2.release(); // saindo da zona critica 2 entre o carro 2
        semaforo1e8RC4.release(); // saindo da zona critica 4 entre o carro 1
        moverEsquerda(35); // RH25
        moverEsquerda(85); // RH24
        semaforo7e8RC2.acquire(); // entrando na zona critica 2 entre o carro 7
        semaforo6e8RC2.acquire(); // entrando na zona critica 2 entre o carro 6
        semaforo4e8RC2.acquire(); // entrando na zona critica 2 entre o carro 4
        semaforo2e8RC1.acquire(); // entrando na zona critica 1 entre o carro 2
        moverEsquerda(20); // RH24 e RV16 (Cruzamento)
        carro.setRotate(270);
        moverBaixo(65); // RV16
        semaforo5e8RC2.acquire(); // entrando na zona critica 2 entre o carro 5
        semaforo1e8RC3.acquire(); // entrando na zona critica 3 entre o carro 1
        moverBaixo(17); // RV16 e RH28 (Cruzamento)
        carro.setRotate(360);
        moverEsquerda(25); // RH28
        semaforo7e8RC2.release(); // saindo da zona critica 2 entre o carro 7
        semaforo6e8RC2.release(); // saindo da zona critica 2 entre o carro 6
        semaforo2e8RC1.release(); // saindo da zona critica 1 entre o carro 2
        moverEsquerda(45); // RH28
        semaforo2e8RC5.acquire(); // entrando na zona critica 5 entre o carro 2
        moverEsquerda(20); // RH28 e RV11 (Cruzamento)
        carro.setRotate(90);
        moverCima(30); // RV11
        semaforo4e8RC2.release(); // saindo da zona critica 2 entre o carro 4
        semaforo1e8RC3.release(); // saindo da zona critica 3 entre o carro 1
        moverCima(35); // RV11
        semaforo7e8RC3.acquire(); // entrando na zona critica 3 entre o carro 7
        moverCima(20); // RV11 e RH22 (Cruzamento)
        carro.setRotate(360);
        moverEsquerda(25); // RH22
        semaforo7e8RC3.release(); // saindo da zona critica 3 entre o carro 7
        semaforo5e8RC2.release(); // saindo da zona critica 2 entre o carro 5
        semaforo2e8RC5.release(); // saindo da zona critica 5 entre o carro 2
        moverEsquerda(22); // RH22

        Platform.runLater(() -> carro.setLayoutX(120));
        Platform.runLater(() -> carro.setLayoutY(353));
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
      if (controle.velocidadeCarro8() == 50) {
        while (controle.velocidadeCarro8() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setY(posicaoY++)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro8()); // Espera baseado na velocidade do carro
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
      if (controle.velocidadeCarro8() == 50) {
        while (controle.velocidadeCarro8() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setY(posicaoY--)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro8()); // Espera baseado na velocidade do carro
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
      if (controle.velocidadeCarro8() == 50) {
        while (controle.velocidadeCarro8() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setX(posicaoX--)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro8()); // Espera baseado na velocidade do carro
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
      if (controle.velocidadeCarro8() == 50) {
        while (controle.velocidadeCarro8() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setX(posicaoX++)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro8()); // Espera baseado na velocidade do carro
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
