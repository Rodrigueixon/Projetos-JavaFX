/* ******************************************************************************************************
* Autor: Rodrigo Ferreira Bento Aguiar
* Matricula: 202310594
* Inicio: 27/06/2024
* Ultima alteracao: 05/07/2024
* Nome: Carro1
* Descricao: Classe da thread do carro, que se move ao longo de um percurso predefinido, 
*            respeitando as zonas criticas controladas por semaforos.
******************************************************************************************************* */

package model;

import controller.ControleGeral;
import static controller.ControleGeral.*;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Carro1 extends Thread {

  private ControleGeral controle;
  private ImageView carro;

  private volatile boolean rodando = true;

  // Variavels das coordenadas do carro
  private int posicaoX;
  private int posicaoY;

  /******************************************************************************
   * Metodo: Carro1
   * Funcao: Construtor da classe Carro1
   * Parametros: ControleGeral controle, ImageView carro
   * Retorno: nenhum
   *****************************************************************************/
  public Carro1(ControleGeral controle, ImageView carro) {
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
      semaforo1e4RC2.acquire(); // Dentro da zona critica 2 do carro 4
      semaforo1e5RC1.acquire(); // Dentro da zona critica 1 do carro 5
      semaforo1e8RC3.acquire(); // Dentro da zona critica 3 do carro 8
    } catch (Exception e) {
      e.printStackTrace();
    }
    while (rodando) {
      try {
        moverEsquerda(30); // RH28
        semaforo1e2RC1.acquire(); // entrando na zona critica 1 entre o carro 2
        moverEsquerda(30); // RH27
        semaforo1e5RC1.release(); // saindo da zona critica 1 entre o carro 5
        semaforo1e8RC3.release(); // saindo da zona critica 3 entre o carro 8
        moverEsquerda(57); // RH27
        moverEsquerda(20); // RH26
        semaforo1e4RC2.release(); // saindo da zona critica 2 entre o carro 4
        moverEsquerda(67); // RH26
        moverEsquerda(10); // RH26 e RV01 (Cruzamento)
        carro.setRotate(90);
        moverCima(70);     // RV01
        semaforo1e8RC2.acquire(); // entrando na zona critica 2 entre o carro 8
        moverCima(80);     // RV02
        moverCima(80);     // RV03
        semaforo1e3RC1.acquire(); // entrando na zona critica 1 entre o carro 3
        semaforo1e7RC1.acquire(); // entrando na zona critica 1 entre o carro 7
        moverCima(100);    // RV04
        moverCima(43);     // RV05
        semaforo1e8RC2.release(); // saindo da zona critica 2 entre o carro 8
        moverCima(30);     // RV05
        moverCima(30);     // RV05 e RH01 (Cruzamento)
        carro.setRotate(180);
        moverDireita(70);  // RH01
        semaforo1e4RC1.acquire(); // entrando na zona critica 1 entre o carro 4
        moverDireita(85);  // RH02
        semaforo1e8RC1.acquire(); // entrando na zona critica 1 entre o carro 8
        moverDireita(30);  // RH03
        semaforo1e7RC1.release(); // saindo da zona critica 1 entre o carro 7
        moverDireita(60);  // RH03
        semaforo1e6RC1.acquire(); // entrando na zona critica 1 entre o carro 6
        moverDireita(20);  // RH04
        semaforo1e4RC1.release(); // saindo da zona critica 1 entre o carro 4
        semaforo1e8RC1.release(); // saindo da zona critica 1 entre o carro 8
        moverDireita(70);  // RH04
        moverDireita(82);  // RH05
        moverDireita(15);  // RH05 e RV30 (Cruzamento)
        carro.setRotate(270);
        moverBaixo(70);    // RV30
        semaforo1e8RC4.acquire(); // entrando na zona critica 4 entre o carro 8
        moverBaixo(90);    // RV29
        semaforo1e5RC1.acquire(); // entrando na zona critica 1 entre o carro 5
        moverBaixo(40);    // RV29 e RV28 (Cruzamento)
        semaforo1e3RC1.release(); // saindo da zona critica 1 entre o carro 3
        moverBaixo(50);    // RV27
        semaforo1e7RC2.acquire(); // entrando na zona critica 2 entre o carro 7
        moverBaixo(125);   // RV26
        semaforo1e8RC4.release(); // saindo da zona critica 4 entre o carro 8
        moverBaixo(60);    // RV26
        carro.setRotate(360);
        moverEsquerda(72); // RH30
        moverEsquerda(87); // RH29
        semaforo1e8RC3.acquire(); // saindo da zona critica 3 entre o carro 8
        moverEsquerda(25); // RH28
        semaforo1e2RC1.release(); // saindo da zona critica 1 entre o carro 2
        semaforo1e4RC2.acquire(); // entrando na zona critica 2 entre o carro 4
        semaforo1e6RC1.release(); // saindo da zona critica 1 entre o carro 6
        semaforo1e7RC2.release(); // saindo da zona critica 2 entre o carro 7
        moverEsquerda(30); // RH28

        Platform.runLater(() -> carro.setLayoutX(212));
        Platform.runLater(() -> carro.setLayoutY(438));
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
      if (controle.velocidadeCarro1() == 50) {
        while (controle.velocidadeCarro1() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setY(posicaoY++)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro1()); // Espera baseado na velocidade do carro
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
      if (controle.velocidadeCarro1() == 50) {
        while (controle.velocidadeCarro1() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setY(posicaoY--)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro1()); // Espera baseado na velocidade do carro
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
      if (controle.velocidadeCarro1() == 50) {
        while (controle.velocidadeCarro1() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setX(posicaoX--)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro1()); // Espera baseado na velocidade do carro
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
      if (controle.velocidadeCarro1() == 50) {
        while (controle.velocidadeCarro1() == 50) {
          try {
            Thread.sleep(1); // carro fica parado quando o slider esta no minimo
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      Platform.runLater(() -> carro.setX(posicaoX++)); // Atualiza a posicao do carro
      try {
        Thread.sleep(controle.velocidadeCarro1()); // Espera baseado na velocidade do carro
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
