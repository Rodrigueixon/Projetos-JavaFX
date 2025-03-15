/* ******************************************************************************************************
* Autor: Rodrigo Ferreira Bento Aguiar
* Matricula: 202310594
* Inicio: 01/07/2024
* Ultima alteracao: 05/07/2024
* Nome: Carro4
* Descricao: Classe da thread do carro, que se move ao longo de um percurso predefinido, 
*            respeitando as zonas criticas controladas por semaforos.
******************************************************************************************************* */

package model;

import controller.ControleGeral;
import static controller.ControleGeral.*;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Carro4 extends Thread {

  private ControleGeral controle;
  private ImageView carro;

  private volatile boolean rodando = true;

  // Variavels das coordenadas do carro
  private int posicaoX;
  private int posicaoY;

  /******************************************************************************
   * Metodo: Carro4
   * Funcao: Construtor da classe Carro4
   * Parametros: ControleGeral controle, ImageView carro
   * Retorno: nenhum
   *****************************************************************************/
  public Carro4(ControleGeral controle, ImageView carro) {
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
        carro.setRotate(90);
        moverCima(20); // RV10
        semaforo4e6RC1.acquire(); // entrando na zona critica 1 entre o carro 6
        semaforo2e4RC1.acquire(); // entrando na zona critica 1 entre o carro 2
        semaforo1e4RC1.acquire(); // entrando na zona critica 1 entre o carro 1
        semaforo3e4RC3.acquire(); // entrando na zona critica 3 entre o carro 3
        semaforo4e7RC1.acquire(); // entrando na zona critica 1 entre o carro 7
        moverCima(23); // RV10 e RH20 (Cruzamento)
        carro.setRotate(180);
        moverDireita(70); // RH02
        semaforo4e8RC1.acquire(); // entrando na zona critica 1 entre o carro 8
        moverDireita(30); // RH03
        semaforo4e7RC1.release(); // saindo da zona critica 1 entre o carro 7
        moverDireita(55); // RH03
        moverDireita(22); // RH03 e RV20 (Cruzamento)
        carro.setRotate(270);
        moverBaixo(25); // RV20
        semaforo1e4RC1.release(); // saindo da zona critica 1 entre o carro 1
        semaforo2e4RC1.release(); // saindo da zona critica 1 entre o carro 2
        semaforo3e4RC3.release(); // saindo da zona critica 3 entre o carro 3
        moverBaixo(50); // RV20
        semaforo4e7RC2.acquire(); // entrando na zona critica 2 entre o carro 7
        moverBaixo(25); // RV19
        semaforo4e8RC1.release(); // saindo da zona critica 1 entre o carro 8
        moverBaixo(55); // RV19
        semaforo2e4RC2.acquire(); // entrando na zona critica 2 entre o carro 2
        semaforo3e4RC2.acquire(); // entrando na zona critica 2 entre o carro 3
        semaforo4e5RC1.acquire(); // entrando na zona critica 1 entre o carro 5
        moverBaixo(40); // RV18
        semaforo4e7RC2.release(); // saindo da zona critica 2 entre o carro 7
        semaforo3e4RC2.release(); // saindo da zona critica 2 entre o carro 4
        semaforo4e5RC1.release(); // saindo da zona critica 1 entre o carro 5
        moverBaixo(48); // RV18
        moverBaixo(88); // RV17
        semaforo4e8RC2.acquire(); // entrando na zona critica 2 entre o carro 8
        semaforo4e7RC3.acquire(); // entrando na zona critica 3 entre o carro 7
        moverBaixo(80); // RV16
        semaforo1e4RC2.acquire(); // entrando na zona critica 2 entre o carro 1
        semaforo4e5RC2.acquire(); // entrando na zona critica 2 entre o carro 5
        moverBaixo(20); // RV16 e RH28 (Cruzamento)
        carro.setRotate(360);
        moverEsquerda(20); // RH28
        semaforo4e7RC3.release(); // saindo da zona critica 3 entre o carro 7
        semaforo2e4RC2.release(); // saindo da zona critica 2 entre o carro 2
        semaforo4e6RC1.release(); // saindo da zona critica 1 entre o carro 6
        moverEsquerda(55); // RH28
        semaforo2e4RC3.acquire(); // entrando na zona critica 3 entre o carro 2
        moverEsquerda(30); // RH27
        semaforo4e8RC2.release(); // saindo da zona critica 2 entre o carro 8
        semaforo4e5RC2.release(); // saindo da zona critica 2 entre o carro 5
        moverEsquerda(50); // RH27
        moverEsquerda(23); // RH27 e RV06 (Cruzamento)
        carro.setRotate(90);
        moverCima(25); // RV06
        semaforo1e4RC2.release(); // saindo da zona critica 2 entre o carro 1
        semaforo2e4RC3.release(); // saindo da zona critica 3 entre o carro 2
        moverCima(50); // RV06
        semaforo4e8RC3.acquire(); // entrando na zona critica 3 entre o carro 8
        moverCima(25); // RV07
        semaforo4e8RC3.release(); // saindo da zona critica 3 entre o carro 8
        moverCima(50); // RV07
        semaforo4e7RC4.acquire(); // entrando na zona critica 4 entre o carro 7
        moverCima(80); // RV08
        semaforo3e4RC1.acquire(); // entrando na zona critica 1 entre o carro 2
        moverCima(45); // RV09
        semaforo3e4RC1.release(); // saindo da zona critica 1 entre o carro 3
        moverCima(30); // RV09
        semaforo4e7RC4.release(); // saindo da zona critica 4 entre o carro 7
        moverCima(20); // RV09
        semaforo4e8RC4.acquire(); // entrando na zona critica 4 entre o carro 8
        moverCima(25); // RV10
        semaforo4e8RC4.release(); // saindo da zona critica 4 entre o carro 8
        moverCima(42); // RV10

        Platform.runLater(() -> carro.setLayoutX(80));
        Platform.runLater(() -> carro.setLayoutY(44));
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
      if (controle.velocidadeCarro4() == 50) {
        while (controle.velocidadeCarro4() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setY(posicaoY++)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro4()); // Espera baseado na velocidade do carro
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
      if (controle.velocidadeCarro4() == 50) {
        while (controle.velocidadeCarro4() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setY(posicaoY--)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro4()); // Espera baseado na velocidade do carro
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
      if (controle.velocidadeCarro4() == 50) {
        while (controle.velocidadeCarro4() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setX(posicaoX--)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro4()); // Espera baseado na velocidade do carro
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
      if (controle.velocidadeCarro4() == 50) {
        while (controle.velocidadeCarro4() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setX(posicaoX++)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro4()); // Espera baseado na velocidade do carro
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
