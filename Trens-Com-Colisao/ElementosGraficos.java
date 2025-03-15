/* ******************************************************************************************************
* Autor: Rodrigo Ferreira Bento Aguiar
* Matricula: 202310594
* Inicio: 16/04/2024
* Ultima alteracao: 21/05/2024
* Nome: ElementosGraficos
* Descricao: Esta classe eh responsavel por configurar e fornecer os elementos graficos utilizados na aplicacao, 
*            incluindo botoes, radio buttons, sliders e labels com imagens.
******************************************************************************************************* */

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ElementosGraficos {

  public RadioButton radioButtonVariavelDeTravamento(ToggleGroup grupoSolucoes) {
    RadioButton radioButton = new RadioButton("Variavel de Travamento");
    radioButton.setToggleGroup(grupoSolucoes);

    AnchorPane.setTopAnchor(radioButton, 100.0);
    AnchorPane.setLeftAnchor(radioButton, 30.0);
    radioButton.setStyle("-fx-font-weight: bold;");
    
    return radioButton;
  }

  public RadioButton radioButtonEstritaAlternancia(ToggleGroup grupoSolucoes) {
    RadioButton radioButton = new RadioButton("Estrita Alternancia");
    radioButton.setToggleGroup(grupoSolucoes);

    AnchorPane.setTopAnchor(radioButton, 140.0);
    AnchorPane.setLeftAnchor(radioButton, 30.0);
    radioButton.setStyle("-fx-font-weight: bold;");

    return radioButton;
  }

  public RadioButton radioButtonSolucaoDePeterson(ToggleGroup grupoSolucoes) {
    RadioButton radioButton = new RadioButton("Solucao de Peterson");
    radioButton.setToggleGroup(grupoSolucoes);

    AnchorPane.setTopAnchor(radioButton, 180.0);
    AnchorPane.setLeftAnchor(radioButton, 30.0);
    radioButton.setStyle("-fx-font-weight: bold;");

    return radioButton;
  }

  /*****************************************************************
   * Metodo: ButtonTelaInicial
   * Funcao: Configura e retorna um botao com a imagem de voltar a tela inicial
   * Retorno: Um botao com a imagem configurada e posicionado
   **********************************************************************/
  public Button ButtonTelaInicial() {
    Button botao = new Button();
    Image imagemButtonTelaInicial = new Image("imagens/botaoVoltar.png");
    botao.setGraphic(new ImageView(imagemButtonTelaInicial));

    // Adiciona o botao de volta ao AnchorPane e define suas coordenadas
    AnchorPane.setTopAnchor(botao, 8.3);
    AnchorPane.setLeftAnchor(botao, 8.3);

    return botao;
  }

  /*****************************************************************
   * Metodo: fundoVelocimetroGalinha
   * Funcao: Configura e retorna um Label com o fundo do velocimetro da galinha
   * Retorno: Um Label contendo o fundo do velocimetro da galinha configurado
   **********************************************************************/
  public Label fundoVelocimetroGalinha() {
    Label label = new Label();// Define um label
    Image imagemVelocidadeGalinha = new Image("imagens/velocidadeGalinha.png");// Carrega a imagem do velocimetro da
                                                                               // galinha

    label.setGraphic(new ImageView(imagemVelocidadeGalinha));// define a imagem ao label
    label.setLayoutX(500); // Define a posicao x do Label
    label.setLayoutY(610); // Define a posicao y do Label

    return label;
  }

  /*****************************************************************
   * Metodo: fundoVelocimetroPorco
   * Funcao: Configura e retorna um Label com o fundo do velocimetro do porco
   * Retorno: Um Label contendo o fundo do velocimetro do porco configurado
   **********************************************************************/
  public Label fundoVelocimetroPorco() {
    Label label = new Label();
    Image imagemVelocidadePorco = new Image("imagens/velocidadePorco.png"); // Carrega a imagem do velocimetro do porco

    label.setGraphic(new ImageView(imagemVelocidadePorco));// Define a imagem ao label
    label.setLayoutX(8.3);// Define a posicao x do Label
    label.setLayoutY(610);// Define a posicao y do Label

    return label;
  }

  /*****************************************************************
   * Metodo: configurarSliderPorco
   * Funcao: Configura um slider para controlar a velocidade da porco
   * Retorno: slider configurado
   **********************************************************************/
  public Slider configurarSliderPorco() {
    Slider slider = new Slider();

    slider.setMin(0);// Valor minimo
    slider.setMax(2);// Valor maximo
    slider.setValue(0); // Valor inicial
    slider.setPrefWidth(220); // Define a largura do slider
    slider.setOrientation(Orientation.HORIZONTAL);// Define a orientacao do slider como horizontal

    return slider;
  }

  /****************************************************************
   * Metodo: configurarSliderGalinha
   * Funcao: Configura um slider para controlar a velocidade da galinha
   * Retorno: slider configurado
   **********************************************************************/
  public Slider configurarSliderGalinha() {
    Slider slider = new Slider();

    slider.setMin(0);// Valor minimo
    slider.setMax(2);// Valor maximo
    slider.setValue(0); // Valor inicial
    slider.setPrefWidth(220); // Define a largura do slider
    slider.setOrientation(Orientation.HORIZONTAL);// Define a orientacao do slider como horizontal

    return slider;
  }
}
