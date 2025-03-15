/* ******************************************************************************************************
* Autor: Rodrigo Ferreira Bento Aguiar
* Matricula: 202310594
* Inicio: 16/04/2024
* Ultima alteracao: 26/05/2024
* Nome: TremGalinha
* Descricao: Classe que implementa a logica de movimento do trem usando a Variavel de travamento,
*            estrita alternancia e solucao de Peterson para controle de concorrencia. 
******************************************************************************************************* */
import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class TremGalinha extends Thread {

  private ImageView trem;
  private Slider sliderTrem;
  private int posInicialX;
  private int posInicialY;
  private boolean emMovimento = true;
  private boolean vindoDeBaixo;
  private String solucao;
  private int processo = 1;

  /******************************************************************************
   * Metodo: TremGalinha
   * Funcao: Construtor da classe TremGalinha
   * Parametros: ImageView trem, Slider sliderTrem,int posInicialX, int
   * posInicialY, boolean vindoDeBaixo
   * Retorno: nenhum
   *****************************************************************************/
  public TremGalinha(ImageView trem, Slider sliderTrem, int posInicialX, int posInicialY, boolean vindoDeBaixo) {
    this.trem = trem;
    this.sliderTrem = sliderTrem;
    this.posInicialX = posInicialX;
    this.posInicialY = posInicialY;
    this.vindoDeBaixo = vindoDeBaixo;
  }// fim do construtor

  /*****************************************************************************
   * Metodo: run
   * Funcao: Metodo executado quando a thread e iniciada. Controla o movimento do
   * trem, aplicando tres diferentes solucoes para evitar as colisoes dos trens.
   * Parametros: nenhum
   * Retorno: nenhum
   ****************************************************************************/
  public void run() {
    trem.setLayoutX(posInicialX);
    trem.setLayoutY(posInicialY);

    while (emMovimento) {
      Platform.runLater(() -> {
        switch (solucao) {
          case "variavelDeTravamento":
            if (vindoDeBaixo) {
              vindoDeBaixoVariavelDeTravamento();
            } else {
              vindoDeCimaVariavelDeTravamento();
            }
            break;

          case "estritaAlternancia":
            if (vindoDeBaixo) {
              vindoDeBaixoEstritaAlternancia();
            } else {
              vindoDeCimaEstritaAlternancia();
            }
            break;

          case "solucaoDePeterson":
            if (vindoDeBaixo) {
              vindoDeBaixoSolucaoDePeterson();
            } else {
              vindoDeCimaSolucaoDePeterson();
            }
            break;

          default:
            break;
        }
      });
      try {
        Thread.sleep(16);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }





  // __________________________VARIAVEL DE TRAVAMENTO_____________________________________________//
  /******************************************************************************
   * Metodo: vindoDeBaixoVariavelDeTravamento
   * Funcao: Movimenta o trem de baixo para cima utilizando a estrategia de
   *         Variavel de travamento para controle de zona critica.
   * Parametros: nenhum
   * Retorno: nenhum
   ******************************************************************************/
  public void vindoDeBaixoVariavelDeTravamento() {
    if (trem.getLayoutY() >= 655) {
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 593 && trem.getLayoutY() <= 655) {
      trem.setRotate(-45);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
    }
    // ZONA CRITICA 1 ----------------------------------------------------------------------------------
    if (trem.getLayoutY() >= 591 && trem.getLayoutY() <= 593 && Principal.variavelDeTravamento1 == 0) {
      trem.setRotate(-45);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 570 && trem.getLayoutY() <= 591) {
      Principal.variavelDeTravamento1 = 1;
      trem.setRotate(-45);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 475 && trem.getLayoutY() <= 570) {
      trem.setRotate(0);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 473 && trem.getLayoutY() <= 475) {
      Principal.variavelDeTravamento1 = 0;
      trem.setRotate(45);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    // FIM DA ZONA CRITICA 1--------------------------------------------------------------
    if (trem.getLayoutY() >= 395 && trem.getLayoutY() <= 473) {
      trem.setRotate(45);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 305 && trem.getLayoutY() <= 395) {
      trem.setRotate(0);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 252 && trem.getLayoutY() <= 305) {
      trem.setRotate(-45);
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    // ZONA CRITICA 2 ----------------------------------------------------------------------------------
    if (trem.getLayoutY() >= 250 && trem.getLayoutY() <= 252 && Principal.variavelDeTravamento2 == 0) {
      trem.setRotate(-45);
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 230 && trem.getLayoutY() <= 250) {
      Principal.variavelDeTravamento2 = 1;
      trem.setRotate(-45);
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 135 && trem.getLayoutY() <= 230) {
      trem.setRotate(0);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 133 && trem.getLayoutY() <= 135) {
      Principal.variavelDeTravamento2 = 0;
      trem.setRotate(45);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    // FIM DA ZONA CRITICA 2 ------------------------------------------------------------------------
    if (trem.getLayoutY() >= 60 && trem.getLayoutY() <= 133) {
      trem.setRotate(45);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() > -40 && trem.getLayoutY() <= 60) {
      trem.setRotate(0);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= -40) {
      trem.setLayoutY(760);
      trem.setLayoutX(posInicialX);
    }
  }

  /******************************************************************************
   * Metodo: vindoDeCimaVariavelDeTravamento
   * Funcao: Movimenta o trem de cima para baixo utilizando a estrategia de
   *         Variavel de travamento para controle de zona critica.
   * Parametros: nenhum
   * Retorno: nenhum
   ********************************************************************************/
  public void vindoDeCimaVariavelDeTravamento() {
    if (trem.getLayoutY() <= 55) {
      trem.setRotate(180);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 112 && trem.getLayoutY() >= 55) {
      trem.setRotate(-135);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
    }
    // ZONA CRITICA 2----------------------------------------------------------------------------------
    if (trem.getLayoutY() <= 114 && trem.getLayoutY() >= 112 && Principal.variavelDeTravamento2 == 0) {
      trem.setRotate(-135);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 137 && trem.getLayoutY() >= 114) {
      Principal.variavelDeTravamento2 = 1;
      trem.setRotate(-135);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 224 && trem.getLayoutY() >= 137) {
      trem.setRotate(180);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 226 && trem.getLayoutY() >= 224) {
      Principal.variavelDeTravamento2 = 0;
      trem.setRotate(-230);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    // FIM DA ZONA CRITICA 2----------------------------------------------------------------------------------
    if (trem.getLayoutY() <= 300 && trem.getLayoutY() >= 226) {
      trem.setRotate(-230);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 399 && trem.getLayoutY() >= 300) {
      trem.setRotate(180);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 475 && trem.getLayoutY() >= 399) {
      trem.setRotate(-135);
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 450 && trem.getLayoutY() >= 475) {
      trem.setRotate(-135);
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    // ZONA CRITICA 1 ----------------------------------------------------------------
    if (trem.getLayoutY() <= 452 && trem.getLayoutY() >= 450 && Principal.variavelDeTravamento1 == 0) {
      trem.setRotate(-135);
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 475 && trem.getLayoutY() >= 452) {
      Principal.variavelDeTravamento1 = 1;
      trem.setRotate(-135);
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 560 && trem.getLayoutY() >= 475) {
      trem.setRotate(180);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 562 && trem.getLayoutY() >= 560) {
      Principal.variavelDeTravamento1 = 0;
      trem.setRotate(-230);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    // FIM DA ZONA CRITICA 1 --------------------------------------------------------
    if (trem.getLayoutY() <= 650 && trem.getLayoutY() >= 562) {
      trem.setRotate(-230);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() < 770 && trem.getLayoutY() >= 650) {
      trem.setRotate(180);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 770) {
      trem.setLayoutY(-20);
      trem.setLayoutX(posInicialX);
    }
  }
  // __________________________FIM DA VARIAVEL DE TRAVAMENTO_____________________________________________//







  // __________________________ESTRITA ALTERNANCIA____________________________________________________//
  /******************************************************************************
   * Metodo: vindoDeBaixoEstritaAlternancia
   * Funcao: Movimenta o trem de baixo para cima utilizando a estrategia de
   *         estrita alternancia para controle de zona critica.
   * Parametros: nenhum
   * Retorno: nenhum
   *******************************************************************************/
  public void vindoDeBaixoEstritaAlternancia() {
    if (trem.getLayoutY() >= 655) {
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 593 && trem.getLayoutY() <= 655) {
      trem.setRotate(-45);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
    }
    // ZONA CRITICA 1 ----------------------------------------------------------------------------------
    if (trem.getLayoutY() >= 591 && trem.getLayoutY() <= 593 && Principal.vez1 == 1) {
      trem.setRotate(-45);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 570 && trem.getLayoutY() <= 591) {
      trem.setRotate(-45);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 475 && trem.getLayoutY() <= 570) {
      trem.setRotate(0);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 473 && trem.getLayoutY() <= 475) {
      Principal.vez1 = 0;
      trem.setRotate(45);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    // FIM DA ZONA CRITICA 1--------------------------------------------------------------
    if (trem.getLayoutY() >= 395 && trem.getLayoutY() <= 473) {
      trem.setRotate(45);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 305 && trem.getLayoutY() <= 395) {
      trem.setRotate(0);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 252 && trem.getLayoutY() <= 305) {
      trem.setRotate(-45);
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    // ZONA CRITICA 2----------------------------------------------------------------------------------
    if (trem.getLayoutY() >= 250 && trem.getLayoutY() <= 252 && Principal.vez2 == 1) {
      trem.setRotate(-45);
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 230 && trem.getLayoutY() <= 250) {
      trem.setRotate(-45);
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 135 && trem.getLayoutY() <= 230) {
      trem.setRotate(0);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 133 && trem.getLayoutY() <= 135) {
      Principal.vez2 = 0;
      trem.setRotate(45);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    // FIM DA ZONA CRITICA 2 ------------------------------------------------------------------------
    if (trem.getLayoutY() >= 60 && trem.getLayoutY() <= 133) {
      trem.setRotate(45);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() > -40 && trem.getLayoutY() <= 60) {
      trem.setRotate(0);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= -40) {
      trem.setLayoutY(760);
      trem.setLayoutX(posInicialX);
    }
  }

  /************************************************************************
   * Metodo: vindoDeCimaEstritaAlternancia
   * Funcao: Movimenta o trem de cima para baixo utilizando a estrategia
   *         de estrita alternancia para controle de zona critica.
   * Parametros: nenhum
   * Retorno: nenhum
   *************************************************************************/
  public void vindoDeCimaEstritaAlternancia() {
    if (trem.getLayoutY() <= 55) {
      trem.setRotate(180);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 112 && trem.getLayoutY() >= 55) {
      trem.setRotate(-135);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
    }
    // ZONA CRITICA 2 ----------------------------------------------------------------------------------
    if (trem.getLayoutY() <= 114 && trem.getLayoutY() >= 112 && Principal.vez2 == 1) {
      trem.setRotate(-135);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 137 && trem.getLayoutY() >= 114) {
      trem.setRotate(-135);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 224 && trem.getLayoutY() >= 137) {
      trem.setRotate(180);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 226 && trem.getLayoutY() >= 224) {
      Principal.vez2 = 0;
      trem.setRotate(-230);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    // FIM DA ZONA CRITICA 2 ----------------------------------------------------------------------------------
    if (trem.getLayoutY() <= 300 && trem.getLayoutY() >= 226) {
      trem.setRotate(-230);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 399 && trem.getLayoutY() >= 300) {
      trem.setRotate(180);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 475 && trem.getLayoutY() >= 399) {
      trem.setRotate(-135);
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 450 && trem.getLayoutY() >= 475) {
      trem.setRotate(-135);
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    // ZONA CRITICA 1 ----------------------------------------------------
    if (trem.getLayoutY() <= 452 && trem.getLayoutY() >= 450 && Principal.vez1 == 1) {
      trem.setRotate(-135);
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 475 && trem.getLayoutY() >= 452) {
      trem.setRotate(-135);
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 560 && trem.getLayoutY() >= 475) {
      trem.setRotate(180);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 562 && trem.getLayoutY() >= 560) {
      Principal.vez1 = 0;
      trem.setRotate(-230);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    // FIM DA ZONA CRITICA 1 ----------------------------------------------------
    if (trem.getLayoutY() <= 650 && trem.getLayoutY() >= 562) {
      trem.setRotate(-230);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() < 770 && trem.getLayoutY() >= 650) {
      trem.setRotate(180);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 770) {
      trem.setLayoutY(-20);
      trem.setLayoutX(posInicialX);
    }
  }
  // __________________________FIM DA ESTRITA ALTERNANCIA________________________________________________//






  // ______________________________SOLUCAO DE PETERSON____________________________________________________//
  /* *****************************************************************************
   * Metodo: vindoDeBaixoSolucaoDePeterson
   * Funcao: Movimenta o trem de baixo para cima utilizando a estrategia de
   * Solucao de Peterson para controle de zona critica.
   * Parametros: nenhum
   * Retorno: nenhum
   ********************************************************************************/
  public void vindoDeBaixoSolucaoDePeterson() {
    if (trem.getLayoutY() >= 655) {
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 593 && trem.getLayoutY() <= 655) {
      trem.setRotate(-45);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
    }
    // ZONA CRITICA 1 ----------------------------------------------------------------------------------
    if (trem.getLayoutY() >= 592 && trem.getLayoutY() <= 593) {
      int outro;
      outro = 1 - processo;
      Principal.interesse1[processo] = true;
      Principal.vez1Peterson = processo;
      if (Principal.vez1Peterson == processo && Principal.interesse1[outro] == false) {
        trem.setRotate(-45);
        trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
        trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
      }
    }
    if (trem.getLayoutY() >= 570 && trem.getLayoutY() <= 592) {
      trem.setRotate(-45);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 475 && trem.getLayoutY() <= 570) {
      trem.setRotate(0);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 473 && trem.getLayoutY() <= 475) {
      Principal.interesse1[processo] = false;
      trem.setRotate(45);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    // FIM DA ZONA CRITICA 1--------------------------------------------------------------
    if (trem.getLayoutY() >= 395 && trem.getLayoutY() <= 473) {
      trem.setRotate(45);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 305 && trem.getLayoutY() <= 395) {
      trem.setRotate(0);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 252 && trem.getLayoutY() <= 305) {
      trem.setRotate(-45);
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    // ZONA CRITICA 2 ----------------------------------------------------------------------------------
    if (trem.getLayoutY() >= 250 && trem.getLayoutY() <= 252) {
      int outro;
      outro = 1 - processo;
      Principal.interesse2[processo] = true;
      Principal.vez2Peterson = processo;
      if (Principal.vez2Peterson == processo && Principal.interesse2[outro] == false) {
        trem.setRotate(-45);
        trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
        trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
      }
    }
    if (trem.getLayoutY() >= 230 && trem.getLayoutY() <= 250) {
      trem.setRotate(-45);
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 135 && trem.getLayoutY() <= 230) {
      trem.setRotate(0);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 133 && trem.getLayoutY() <= 135) {
      Principal.interesse2[processo] = false;
      trem.setRotate(45);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    // FIM DA ZONA CRITICA 2 ------------------------------------------------------------------------
    if (trem.getLayoutY() >= 60 && trem.getLayoutY() <= 133) {
      trem.setRotate(45);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() > -40 && trem.getLayoutY() <= 60) {
      trem.setRotate(0);
      trem.setLayoutY(trem.getLayoutY() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= -40) {
      trem.setLayoutY(760);
      trem.setLayoutX(posInicialX);
    }
  }

  /******************************************************************************
   * Metodo: vindoDeCimaSolucaoDePeterson
   * Funcao: Movimenta o trem de cima para baixo utilizando a estrategia de
   * Solucao de Peterson para controle de zona critica.
   * Parametros: nenhum
   * Retorno: nenhum
   ********************************************************************************/
  public void vindoDeCimaSolucaoDePeterson() {
    if (trem.getLayoutY() <= 55) {
      trem.setRotate(180);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 112 && trem.getLayoutY() >= 55) {
      trem.setRotate(-135);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
    }
    // ZONA CRITICA 2 ----------------------------------------------------------------------------------
    if (trem.getLayoutY() <= 114 && trem.getLayoutY() >= 112) {
      int outro;
      outro = 1 - processo;
      Principal.interesse2[processo] = true;
      Principal.vez2Peterson = processo;
      if (Principal.vez2Peterson == processo && Principal.interesse2[outro] == false) {
        trem.setRotate(-135);
        trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
        trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
      }
    }
    if (trem.getLayoutY() <= 137 && trem.getLayoutY() >= 114) {
      trem.setRotate(-135);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 224 && trem.getLayoutY() >= 137) {
      trem.setRotate(180);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 226 && trem.getLayoutY() >= 224) {
      Principal.interesse2[processo] = false;
      trem.setRotate(-230);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    // FIM DA ZONA CRITICA 2 ----------------------------------------------------------------------------------
    if (trem.getLayoutY() <= 300 && trem.getLayoutY() >= 226) {
      trem.setRotate(-230);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 399 && trem.getLayoutY() >= 300) {
      trem.setRotate(180);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 475 && trem.getLayoutY() >= 399) {
      trem.setRotate(-135);
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 450 && trem.getLayoutY() >= 475) {
      trem.setRotate(-135);
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    // ZONA CRITICA 1 ----------------------------------------------------
    if (trem.getLayoutY() <= 452 && trem.getLayoutY() >= 450) {
      int outro;
      outro = 1 - processo;
      Principal.interesse1[processo] = true;
      Principal.vez1Peterson = processo;
      System.out.println(Principal.interesse1[outro]);
      if (Principal.vez1Peterson == processo && Principal.interesse1[outro] == false) {
        trem.setRotate(-135);
        trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
        trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
      }
    }
    if (trem.getLayoutY() <= 475 && trem.getLayoutY() >= 452) {
      trem.setRotate(-135);
      trem.setLayoutX(trem.getLayoutX() - sliderTrem.getValue());
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 560 && trem.getLayoutY() >= 475) {
      trem.setRotate(180);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() <= 562 && trem.getLayoutY() >= 560) {
      Principal.interesse1[processo] = false;
      trem.setRotate(-230);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    // FIM DA ZONA CRITICA 1 ----------------------------------------------------
    if (trem.getLayoutY() <= 650 && trem.getLayoutY() >= 562) {
      trem.setRotate(-230);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
      trem.setLayoutX(trem.getLayoutX() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() < 770 && trem.getLayoutY() >= 650) {
      trem.setRotate(180);
      trem.setLayoutY(trem.getLayoutY() + sliderTrem.getValue());
    }
    if (trem.getLayoutY() >= 770) {
      trem.setLayoutY(-20);
      trem.setLayoutX(posInicialX);
    }
  }
// _______________________________FIM DA SOLUCAO DE PETERSON_________________________________________________//


  /****************************************************************
   * Metodo: pararTremGalinha
   * Funcao: Para o movimento do trem da Galinha
   * Parametros: nenhum
   * Retorno: nenhum
   ****************************************************************/
  public void pararTremGalinha() {
    if (emMovimento) {
      emMovimento = false;
    }
  }

  /*********************************************************************
   * Metodo: moverTremGalinha
   * Funcao: Inicia o movimento do trem da Galinha
   * Parametros: nenhum
   * Retorno: nenhum
   *********************************************************************/
  public void moverTremGalinha() {
    emMovimento = true;
  }

  /*****************************************************************************
   * Metodo: resetarTremGalinha
   * Funcao: Reseta a posicao e orientacao do trem da Galinha
   * Parametros: boolean porBaixo - indica se o trem deve ser resetado por baixo
   * Retorno: nenhum
   *****************************************************************************/
  public void resetarTremGalinha(boolean porBaixo) {
    pararTremGalinha();
    Principal.interesse1[0] = false;
    Principal.interesse1[1] = false;
    Principal.interesse2[0] = false;
    Principal.interesse2[1] = false;

    Principal.variavelDeTravamento1 = 0;
    Principal.variavelDeTravamento2 = 0;

    Principal.vez1 = 0;
    Principal.vez2 = 0;

    sliderTrem.setValue(0);
    trem.setLayoutX(posInicialX);
    trem.setLayoutY(posInicialY);
    emMovimento = true;
    if (porBaixo) {
      trem.setRotate(0);
    } else {
      trem.setRotate(180);
    }
  }

  /*****************************************************************************
   * Metodo: getSolucao
   * Funcao: Retorna a solucao.
   * Parametros: nenhum
   * Retorno: String - solucao de sincronizacao
   *****************************************************************************/
  public String getSolucao() {
    return solucao;
  }

  /*****************************************************************************
   * Metodo: setSolucao
   * Funcao: Define a solucao.
   * Parametros: String solucao - solucao a ser definida
   * Retorno: nenhum
   *****************************************************************************/
  public void setSolucao(String solucao) {
    this.solucao = solucao;
  }
}// Fim da classe TremGalinha
