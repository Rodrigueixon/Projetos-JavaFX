/* ******************************************************************************************************
* Autor: Rodrigo Ferreira Bento Aguiar
* Matricula: 202310594
* Inicio: 01/07/2024
* Ultima alteracao: 05/07/2024
* Nome: Carro6
* Descricao: Classe da thread do carro, que se move ao longo de um percurso predefinido, 
*            respeitando as zonas criticas controladas por semaforos.
******************************************************************************************************* */

package model;

import controller.ControleGeral;
import static controller.ControleGeral.*;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Carro6 extends Thread {

  private ControleGeral controle;
  private ImageView carro;

  private volatile boolean rodando = true;

  // Variavels das coordenadas do carro
  private int posicaoX;
  private int posicaoY;

  /******************************************************************************
   * Metodo: Carro6
   * Funcao: Construtor da classe Carro6
   * Parametros: ControleGeral controle, ImageView carro
   * Retorno: nenhum
   *****************************************************************************/
  public Carro6(ControleGeral controle, ImageView carro) {
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
      semaforo4e6RC1.acquire(); // dentro da zona critica 1 do carro 4
      semaforo6e8RC3.acquire(); // dentro da zona critica 3 do carro 8
    } catch (Exception e) {
      e.printStackTrace();
    }
    while (rodando) {
      try {
        carro.setRotate(90);
        moverCima(20); // RV20
        semaforo3e6RC2.acquire(); // entrando na zona critica 1 entre o carro 3
        semaforo2e6RC1.acquire(); // entrando na zona critica 1 entre o carro 2
        semaforo1e6RC1.acquire(); // entrando na zona critica 1 entre o carro 1
        moverCima(23); // RV20 e RH04 (Cruzamento)
        carro.setRotate(180);
        moverDireita(30); // RH04
        semaforo6e8RC3.release(); // saindo da zona critica 3 entre o carro 8
        semaforo4e6RC1.release(); // saindo da zona critica 1 entre o carro 4
        moverDireita(40); // RH04
        moverDireita(85); // RH05
        moverDireita(15); // RH05 e RV30 (Cruzamento)
        carro.setRotate(270);
        moverBaixo(25); // RV30
        moverBaixo(40); // RV30
        semaforo6e8RC1.acquire(); // entrando na zona critica 1 entre o carro 8
        moverBaixo(80); // RV29
        semaforo5e6RC1.acquire(); // entrando na zona critica 1 entre o carro 6
        moverBaixo(40); // RV28
        semaforo3e6RC2.release(); // saindo da zona critica 2 entre o carro 3
        moverBaixo(48); // RV28
        semaforo6e7RC1.acquire(); // entrando na zona critica 1 entre o carro 7
        moverBaixo(88); // RV27
        moverBaixo(30); // RV26
        semaforo6e8RC1.release(); // saindo da zona critica 1 entre o carro 8
        moverBaixo(55); // RV26
        moverBaixo(25); // RV26 e RH30 (Cruzamento)
        carro.setRotate(360);
        moverEsquerda(20); // RH30
        moverEsquerda(55); // RH30
        moverEsquerda(30); // RH29
        moverEsquerda(45); // RH29
        semaforo6e8RC2.acquire(); // entrando na zona critica 2 entre o carro 8
        semaforo4e6RC1.acquire(); // entrando na zona critica 1 entre o carro 4
        moverEsquerda(20); // RH29 e RV16 (Cruzamento)
        carro.setRotate(90);
        moverCima(25); // RV16
        semaforo1e6RC1.release(); // saindo da zona critica 1 entre o carro 1
        semaforo5e6RC1.release(); // saindo da zona critica 1 entre o carro 5
        moverCima(50); // RV16
        moverCima(30); // RV17
        semaforo6e8RC2.release(); // saindo da zona critica 2 entre o carro 8
        semaforo6e7RC1.release(); // saindo da zona critica 1 entre o carro 7
        moverCima(45); // RV17
        moverCima(80); // RV18
        semaforo6e7RC2.acquire(); // entrando na zona critica 2 entre o carro 7
        semaforo3e6RC1.acquire(); // entrando na zona critica 1 entre o carro 3
        semaforo5e6RC2.acquire(); // entrando na zona critica 2 entre o carro 5
        moverCima(45); // RV19
        semaforo2e6RC1.release(); // saindo da zona critica 1 entre o carro 2
        semaforo3e6RC1.release(); // saindo da zona critica 1 entre o carro 3
        semaforo5e6RC2.release(); // saindo da zona critica 2 entre o carro 5
        moverCima(50); // RV19
        semaforo6e8RC3.acquire(); // entrando na zona critica 3 entre o carro 8
        moverCima(30); // RV20
        semaforo6e7RC2.release(); // saindo da zona critica 2 entre o carro 7
        moverCima(30); // RV20

        Platform.runLater(() -> carro.setLayoutX(260));
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
      if (controle.velocidadeCarro6() == 50) {
        while (controle.velocidadeCarro6() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setY(posicaoY++)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro6()); // Espera baseado na velocidade do carro
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
      if (controle.velocidadeCarro6() == 50) {
        while (controle.velocidadeCarro6() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setY(posicaoY--)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro6()); // Espera baseado na velocidade do carro
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
      if (controle.velocidadeCarro6() == 50) {
        while (controle.velocidadeCarro6() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setX(posicaoX--)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro6()); // Espera baseado na velocidade do carro
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
      if (controle.velocidadeCarro6() == 50) {
        while (controle.velocidadeCarro6() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setX(posicaoX++)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro6()); // Espera baseado na velocidade do carro
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
