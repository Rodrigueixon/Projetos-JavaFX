/* ******************************************************************************************************
* Autor............: Rodrigo Ferreira Bento Aguiar
* Matricula........: 202310594
* Inicio...........: 13/03/2024
* Ultima alteracao.: 27/03/2024
* Nome.............: Train Simulator
* Funcao...........: Este programa simula o movimento de dois trens em um trilho convergente, 
*                    oferecendo uma representacao visual do trajeto dos trens e a capacidade de ajustar 
*                    a velocidade de cada um.
******************************************************************************************************* */

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Orientation;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Principal extends Application {
  public static void main(String[] args) {
    launch(args); // Inicia a aplicacao JavaFX
  }// fim do main

  /* *********************************************************************
   * Metodo: start
   * Funcao: Inicializa a aplicacao JavaFX
   * Parametros: primaryStage = a janela principal da aplicacao (Stage)
   * Retorno: void
   **********************************************************************/ 
  @Override
  public void start(Stage primaryStage) throws Exception {

    Image imagemTelaInicial = new Image("imagens/telaInicial.png");// Carrega a imagem atraves do endereco
    ImageView imageViewTelaInicial = new ImageView(imagemTelaInicial);// Cria um ImageView e define a imagem nele
    imageViewTelaInicial.setFitHeight(900 * 0.83);// Define a altura da imagem
    imageViewTelaInicial.setFitWidth(900 * 0.83);// Define a largura da imagem
    AnchorPane layoutTelaInicial = new AnchorPane();// Cria um layout para conter o ImageView
    layoutTelaInicial.getChildren().add(imageViewTelaInicial); // Adiciona o ImageView ao layout

    // Carrega e define o icone da janela
    Image icone = new Image("imagens/icone.png");
    primaryStage.getIcons().add(icone);

    Scene telaInicial = new Scene(layoutTelaInicial);// Cria uma cena com o layout que contem o ImageView

    Button buttonStart = new Button();// Cria o botao Start

    // Define a imagem diretamente como o conteudo do botao
    Image imagemBotaoStart = new Image("imagens/botaoStart.png");
    buttonStart.setGraphic(new ImageView(imagemBotaoStart));
    layoutTelaInicial.getChildren().add(buttonStart); // Adiciona o botao ao layout

    // Posiciona o botao start na tela
    AnchorPane.setTopAnchor(buttonStart, 750.0 * 0.83);
    AnchorPane.setLeftAnchor(buttonStart, 100.0 * 0.83);

    final AnchorPane layoutTrilhos[] = new AnchorPane[1];// Declara um array de AnchorPane para armazenar layouts de trilhos

    buttonStart.setOnAction(start -> {
      Image imagemTelaDeEscolha = new Image("imagens/telaEscolhaDeDirecao.png");// Carrega a imagem da tela de escolha
      Image imagemTrilhos = new Image("imagens/trilhos.png");// Carrega a imagem dos trilhos

      // Criacao dos paths para os diferentes movimentos dos trens
      Path pathBaixoEsquerda = baixoEsquerda();
      Path pathBaixoDireita = baixoDireita();
      Path pathCimaEsquerda = cimaEsquerda();
      Path pathCimaDireita = cimaDireita();

      // Criacao dos sliders para controlar a velocidade dos trens
      Slider sliderVelocidadePorco = configurarSliderPorco();
      Slider sliderVelocidadeGalinha = configurarSliderGalinha();

      // Criacao dos velocimetro dos trens
      Label labelKmPorco = velocimetroPorco();
      Label labelKmGalinha = velocimetroGalinha();

      ImageView imageViewTelaDeEscolha = new ImageView(imagemTelaDeEscolha); // Cria um ImageView e define a imagem nele
      AnchorPane layoutTelaDeEscolha = new AnchorPane();// Cria um layout para conter o ImageView
      layoutTelaDeEscolha.getChildren().add(imageViewTelaDeEscolha);// Adiciona o ImageView ao layout
      Scene telaDeEscolha = new Scene(layoutTelaDeEscolha);// Cria uma cena com o layout que contem o ImageView

      imageViewTelaDeEscolha.setFitHeight(900 * 0.83);// Define a altura da imagem
      imageViewTelaDeEscolha.setFitWidth(900 * 0.83);// Define a largura da imagem

      primaryStage.setScene(telaDeEscolha);// Define a cena inicial como a tela de escolha de direcao

      // Define a imagem diretamente como o conteudo do botao
      Button buttonPartindoBaixoJuntos = new Button();
      Image imagemBotaoPartindoBaixoJuntos = new Image("imagens/partindoBaixoJuntos.png");

      // Define a imagem como grafico do botao e adiciona o botao ao layout da tela de escolha de direcao
      ImageView imageViewBotaoPartindoBaixoJuntos = new ImageView(imagemBotaoPartindoBaixoJuntos);
      buttonPartindoBaixoJuntos.setGraphic(imageViewBotaoPartindoBaixoJuntos);
      layoutTelaDeEscolha.getChildren().add(buttonPartindoBaixoJuntos);

      imageViewBotaoPartindoBaixoJuntos.setFitHeight(200 * 0.83);// Define a altura da imagem
      imageViewBotaoPartindoBaixoJuntos.setFitWidth(200 * 0.83);// Define a largura da imagem

      AnchorPane.setTopAnchor(buttonPartindoBaixoJuntos, 350.0 * 0.83);// Posicao do botao partindo de cima
      AnchorPane.setLeftAnchor(buttonPartindoBaixoJuntos, 100.0 * 0.83);// Posicao do botao partindo da esquerda

      buttonPartindoBaixoJuntos.setOnAction(partindoBaixoJuntos -> {
        // Cria uma ImageView para exibir a imagem dos trilhos
        ImageView imageViewTrilhos = new ImageView(imagemTrilhos);

        // Cria um AnchorPane para conter os elementos da cena dos trilhos e adiciona a imagem dos trilhos a ele
        layoutTrilhos[0] = new AnchorPane();
        layoutTrilhos[0].getChildren().add(imageViewTrilhos);

        // Cria um botao de volta e define uma imagem nele
        Button buttonTelaInicial = ButtonTelaInicial();
        layoutTrilhos[0].getChildren().add(buttonTelaInicial);

        // Cria a cena AnchorPane contendo a imagem dos trilhos e define o seu tamanho
        Scene trilhos = new Scene(layoutTrilhos[0]);
        imageViewTrilhos.setFitHeight(900 * 0.83);
        imageViewTrilhos.setFitWidth(900 * 0.83);

        // Define e configura um label para mostrar a velocidade do trem porco
        Label labelVelocidadePorco = fundoVelocimetroPorco();
        layoutTrilhos[0].getChildren().add(labelVelocidadePorco);// Adiciona o label ao layout

        // Cria e configura a imagem do trem porco
        ImageView imageViewTremPorco = configurarImageViewPorco(355, 865, -90);
        layoutTrilhos[0].getChildren().add(imageViewTremPorco);// Adiciona a imagem do trem ao layout

        // Configuracoes do slider de velocidade do trem porco
        configurarSliderPorco();

        // Posiciona o slider sobre o velocimetro
        AnchorPane.setBottomAnchor(sliderVelocidadePorco, 60.0 * 0.83);
        AnchorPane.setLeftAnchor(sliderVelocidadePorco, 23.0 * 0.83);
        layoutTrilhos[0].getChildren().add(sliderVelocidadePorco);// Adiciona o slider ao layout do trilho

        // Cria e configura um label para mostrar a velocidade do trem porco em Km/h
        velocimetroPorco();
        layoutTrilhos[0].getChildren().add(labelKmPorco);// Adiciona o label ao layout

        // Criacao do PathTransition para a animacao do trem porco
        PathTransition pathTransitionPorco = new PathTransition();

        // Configuracao do listener para o slider de velocidade do trem porco
        DoubleProperty velocidadePorco = new SimpleDoubleProperty(5);
        sliderVelocidadePorco.valueProperty().addListener((observable, oldValue, newValue) -> {
          velocidadePorco.set(newValue.doubleValue() + 0.0001);
          if (velocidadePorco.get() == 0) {
            pathTransitionPorco.pause(); // Pausa o PathTransition do porco
          } else {// Fim do if
            pathTransitionPorco.play(); // Da play no PathTransition do porco
            labelKmPorco.setText(String.format("%.2f Km/h", velocidadePorco.get()));// Atualiza o texto do label com a velocidade
          } // Fim do else
        });// Fim do Listener

        // Configuracao do PathTransition para o trem galinha
        pathTransitionPorco.rateProperty().bind(velocidadePorco);
        pathTransitionPorco.setDuration(Duration.seconds(50));
        pathTransitionPorco.setPath(pathBaixoEsquerda);
        pathTransitionPorco.setNode(imageViewTremPorco);
        pathTransitionPorco.setCycleCount(Timeline.INDEFINITE);
        pathTransitionPorco.setInterpolator(Interpolator.LINEAR);
        pathTransitionPorco.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        // Cria e configura a imagem do trem galinha
        ImageView imageViewTremGalinha = configurarImageViewGalinha(555, 865, -90);
        layoutTrilhos[0].getChildren().add(imageViewTremGalinha);// Adiciona a imagem do trem ao layout

        // Define e configura um label para mostrar a velocidade do trem galinha
        Label labelVelocidadeGalinha = fundoVelocimetroGalinha();
        layoutTrilhos[0].getChildren().add(labelVelocidadeGalinha);// Adiciona o label ao layout

        // Configuracoes do slider de velocidade do trem galinha
        configurarSliderGalinha();

        // Posiciona o slider sobre o velocimetro
        AnchorPane.setBottomAnchor(sliderVelocidadeGalinha, 60.0 * 0.83);
        AnchorPane.setLeftAnchor(sliderVelocidadeGalinha, 620.0 * 0.83);
        layoutTrilhos[0].getChildren().add(sliderVelocidadeGalinha);// Adiciona o slider ao layout

        // Cria e configura um label para mostrar a velocidade do trem galinha em Km/h
        velocimetroGalinha();
        layoutTrilhos[0].getChildren().add(labelKmGalinha);// Adiciona o label ao layout

        // Criacao do PathTransition para a animacao do trem galinha
        PathTransition pathTransitionGalinha = new PathTransition();

        // Configuracao do listener para o slider de velocidade do trem galinha
        DoubleProperty velocidadeGalinha = new SimpleDoubleProperty(5);
        sliderVelocidadeGalinha.valueProperty().addListener((observable, oldValue, newValue) -> {
          velocidadeGalinha.set(newValue.doubleValue() + 0.0001);
          if (velocidadeGalinha.get() == 0) {
            pathTransitionGalinha.pause();// Pausa o PathTransition da galinha
          } else {// Fim do if
            pathTransitionGalinha.play();// Da play no PathTransition da Galinha
            labelKmGalinha.setText(String.format("%.2f Km/h", velocidadeGalinha.get()));// Atualiza o texto do label com a velocidade
          } // Fim do else
        });// Fim do Listener

        // Configuracao do PathTransition para o trem galinha
        pathTransitionGalinha.rateProperty().bind(velocidadeGalinha);
        pathTransitionGalinha.setDuration(Duration.seconds(50));
        pathTransitionGalinha.setPath(pathBaixoDireita);
        pathTransitionGalinha.setNode(imageViewTremGalinha);
        pathTransitionGalinha.setCycleCount(Timeline.INDEFINITE);
        pathTransitionGalinha.setInterpolator(Interpolator.LINEAR);
        pathTransitionGalinha.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        Image imagemBotaoResetar = new Image("imagens/botaoResetar.png");// Define a imagem para o botao de resetar

        // Cria um botao e define a imagem como seu grafico
        Button buttonResetar = new Button();
        buttonResetar.setGraphic(new ImageView(imagemBotaoResetar));
        layoutTrilhos[0].getChildren().add(buttonResetar);

        // Adiciona o botao ao layout e define suas ancoras de posicionamento
        AnchorPane.setTopAnchor(buttonResetar, 10.0 * 0.83);
        AnchorPane.setLeftAnchor(buttonResetar, 630.0 * 0.83);

        // Define a acao a ser executada quando o botao de resetar eh clicado
        buttonResetar.setOnAction(event -> {
          pathTransitionPorco.stop();// Para o PathTransition
          pathTransitionGalinha.stop();// Para o PathTransition

          sliderVelocidadePorco.setValue(0.001); // Valor inicial
          sliderVelocidadeGalinha.setValue(0.001); // Valor inicial
          imageViewTremGalinha.setX(555 * 0.83);// Define a posicao x do trem
          imageViewTremGalinha.setY(865 * 0.83);// Define a posicao y do trem

          imageViewTremPorco.setX(355 * 0.83);// Define a posicao x do trem
          imageViewTremPorco.setY(865 * 0.83);// Define a posicao y do trem
        });// Fim do botao Resetar

        // Define a acao a ser executada quando o botao de voltar eh clicado
        buttonTelaInicial.setOnAction(event -> {
          pathTransitionPorco.stop();// Para o PathTransition
          pathTransitionGalinha.stop();// Para o PathTransition
          sliderVelocidadePorco.setValue(0.001); // Valor inicial
          sliderVelocidadeGalinha.setValue(0.001); // Valor inicial
          primaryStage.setScene(telaDeEscolha);// Define a cena de tela de escolha como a principal
        });// Fim do botao

        primaryStage.setScene(trilhos);// Define a cena inicial como a tela de Trilhos

      });// Fim do botao (partindo baixo juntos)

      // Define a imagem diretamente como o conteudo do botao
      Button buttonPartindoCimaJuntos = new Button(); // Cria o botao (partindo juntos por cima)
      Image imagemBotaoPartindoCimaJuntos = new Image("imagens/partindoCimaJuntos.png");// Carrega a imagem

      ImageView imageViewBotaoPartindoCimaJuntos = new ImageView(imagemBotaoPartindoCimaJuntos);
      buttonPartindoCimaJuntos.setGraphic(imageViewBotaoPartindoCimaJuntos);
      layoutTelaDeEscolha.getChildren().add(buttonPartindoCimaJuntos);

      imageViewBotaoPartindoCimaJuntos.setFitHeight(200 * 0.83);// Define a altura da imagem
      imageViewBotaoPartindoCimaJuntos.setFitWidth(200 * 0.83);// Define a largura da imagem

      AnchorPane.setTopAnchor(buttonPartindoCimaJuntos, 350.0 * 0.83);// Posicao do botao partindo de cima
      AnchorPane.setLeftAnchor(buttonPartindoCimaJuntos, 600.0 * 0.83);// Posicao do botao partindo da esquerda

      buttonPartindoCimaJuntos.setOnAction(partindoCimaJuntos -> {
        // Cria uma ImageView para exibir a imagem dos trilhos
        ImageView imageViewTrilhos = new ImageView(imagemTrilhos);

        // Cria um AnchorPane para conter os elementos da cena dos trilhos e adiciona a imagem dos trilhos a ele
        layoutTrilhos[0] = new AnchorPane();
        layoutTrilhos[0].getChildren().add(imageViewTrilhos);

        // Cria um botao de volta e define uma imagem nele
        Button buttonTelaInicial = ButtonTelaInicial();
        layoutTrilhos[0].getChildren().add(buttonTelaInicial);

        // Define e configura um label para mostrar a velocidade do trem porco
        Label labelVelocidadePorco = fundoVelocimetroPorco();
        layoutTrilhos[0].getChildren().add(labelVelocidadePorco);// Adiciona o label ao layout

        // Cria e configura a imagem do trem porco
        ImageView imageViewTremPorco = configurarImageViewPorco(348, 0, 90);
        layoutTrilhos[0].getChildren().add(imageViewTremPorco);

        // Configura o slider de velocidade do trem porco
        configurarSliderPorco();

        // Posiciona o slider sobre o velocimetro
        AnchorPane.setBottomAnchor(sliderVelocidadePorco, 60.0 * 0.83);
        AnchorPane.setLeftAnchor(sliderVelocidadePorco, 23.0 * 0.83);
        layoutTrilhos[0].getChildren().add(sliderVelocidadePorco);// Adiciona o slider ao layout do trilho

        // Cria e configura um label para mostrar a velocidade do trem porco em Km/h
        velocimetroPorco();
        layoutTrilhos[0].getChildren().add(labelKmPorco);// Adiciona o label ao layout

        // Criacao do PathTransition para a animacao do trem porco
        PathTransition pathTransitionPorco = new PathTransition();

        // Configuracao do listener para o slider de velocidade do trem porco
        DoubleProperty velocidadePorco = new SimpleDoubleProperty(5);
        sliderVelocidadePorco.valueProperty().addListener((observable, oldValue, newValue) -> {
          velocidadePorco.set(newValue.doubleValue() + 0.0001);
          if (velocidadePorco.get() == 0) {
            pathTransitionPorco.pause(); // Pausa o PathTransition do porco
          } else {// Fim do if
            pathTransitionPorco.play(); // Da play no PathTransition do porco
            labelKmPorco.setText(String.format("%.2f Km/h", velocidadePorco.get()));// Atualiza o texto do label com a velocidade
          } // Fim do else
        });// Fim do Listener

        // Configuracao do PathTransition para o trem porco
        pathTransitionPorco.rateProperty().bind(velocidadePorco);
        pathTransitionPorco.setDuration(Duration.seconds(50));
        pathTransitionPorco.setPath(pathCimaEsquerda);
        pathTransitionPorco.setNode(imageViewTremPorco);
        pathTransitionPorco.setCycleCount(Timeline.INDEFINITE);
        pathTransitionPorco.setInterpolator(Interpolator.LINEAR);
        pathTransitionPorco.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        // Cria e configura a imagem do trem galinha
        ImageView imageViewTremGalinha = configurarImageViewGalinha(545, -5, 90);
        layoutTrilhos[0].getChildren().add(imageViewTremGalinha);// Adiciona a imagem do trem ao layout

        // Define e configura um label para mostrar a velocidade do trem galinha
        Label labelVelocidadeGalinha = fundoVelocimetroGalinha();
        layoutTrilhos[0].getChildren().add(labelVelocidadeGalinha);// Adiciona o label ao layout

        // Configuracoes do slider de velocidade do trem galinha
        configurarSliderGalinha();

        // Posiciona o slider sobre o velocimetro
        AnchorPane.setBottomAnchor(sliderVelocidadeGalinha, 60.0 * 0.83);
        AnchorPane.setLeftAnchor(sliderVelocidadeGalinha, 620.0 * 0.83);
        layoutTrilhos[0].getChildren().add(sliderVelocidadeGalinha);// Adiciona o slider ao layout

        // Cria e configura um label para mostrar a velocidade do trem galinha em Km/h
        velocimetroGalinha();
        layoutTrilhos[0].getChildren().add(labelKmGalinha);// Adiciona o label ao layout

        // Criacao do PathTransition para a animacao do trem galinha
        PathTransition pathTransitionGalinha = new PathTransition();

        // Configuracao do listener para o slider de velocidade do trem galinha
        DoubleProperty velocidadeGalinha = new SimpleDoubleProperty(5);
        sliderVelocidadeGalinha.valueProperty().addListener((observable, oldValue, newValue) -> {
          velocidadeGalinha.set(newValue.doubleValue() + 0.0001);
          if (velocidadeGalinha.get() == 0) {
            pathTransitionGalinha.pause();// Pausa o PathTransition da galinha
          } else {// Fim do if
            pathTransitionGalinha.play();// Da play no PathTransition da Galinha
            labelKmGalinha.setText(String.format("%.2f Km/h", velocidadeGalinha.get()));// Atualiza o texto do label com a velocidade
          } // Fim do else
        });// Fim do Listener

        // Configuracao do PathTransition para o trem galinha
        pathTransitionGalinha.rateProperty().bind(velocidadeGalinha);
        pathTransitionGalinha.setDuration(Duration.seconds(50));
        pathTransitionGalinha.setPath(pathCimaDireita);
        pathTransitionGalinha.setNode(imageViewTremGalinha);
        pathTransitionGalinha.setCycleCount(Timeline.INDEFINITE);
        pathTransitionGalinha.setInterpolator(Interpolator.LINEAR);
        pathTransitionGalinha.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        // Define a acao a ser executada quando o botao de voltar eh clicado
        buttonTelaInicial.setOnAction(event -> {
          pathTransitionPorco.stop();// Para o PathTransition
          pathTransitionGalinha.stop();// Para o PathTransition
          sliderVelocidadePorco.setValue(0.001); // Valor inicial
          sliderVelocidadeGalinha.setValue(0.001); // Valor inicial
          primaryStage.setScene(telaDeEscolha);// Define a cena de tela de escolha como a principal
        });// Fim do botao

        Scene trilhos = new Scene(layoutTrilhos[0]);// Cria uma cena com o layout que contem o ImageView
        imageViewTrilhos.setFitHeight(900 * 0.83);// Define a altura da imagem
        imageViewTrilhos.setFitWidth(900 * 0.83);// Define a largura da imagem

        Image imagemBotaoResetar = new Image("imagens/botaoResetar.png");// Define a imagem para o botao de resetar

        // Cria um botao e define a imagem como seu grafico
        Button buttonResetar = new Button();
        buttonResetar.setGraphic(new ImageView(imagemBotaoResetar));
        layoutTrilhos[0].getChildren().add(buttonResetar);

        // Adiciona o botao ao layout e define suas ancoras de posicionamento
        AnchorPane.setTopAnchor(buttonResetar, 10.0 * 0.83);
        AnchorPane.setLeftAnchor(buttonResetar, 630.0 * 0.83);

        // Define a acao a ser executada quando o botao de resetar eh clicado
        buttonResetar.setOnAction(event -> {
          pathTransitionPorco.stop();// Para o PathTransition
          pathTransitionGalinha.stop();// Para o PathTransition

          sliderVelocidadePorco.setValue(0.001); // Valor inicial
          sliderVelocidadeGalinha.setValue(0.001); // Valor inicial
          imageViewTremGalinha.setX(545 * 0.83);// Define a posicao x do trem
          imageViewTremGalinha.setY(-5 * 0.83);// Define a posicao y do trem

          imageViewTremPorco.setX(348 * 0.83);// Define a posicao x do trem
          imageViewTremPorco.setY(0 * 0.83);// Define a posicao y do trem
        });// Fim do botao Resetar
        primaryStage.setScene(trilhos);// Define a cena inicial como a tela de Trilhos
      });// Fim do botao (partindo cima juntos)

      // Define a imagem diretamente como o conteudo do botao
      Button buttonPartindoCimaBaixo = new Button();
      Image imagemBotaoPartindoCimaBaixo = new Image("imagens/partindoCimaBaixo.png");// Carrega a imagem atraves do
                                                                                    // endereco
      ImageView imageViewBotaoPartindoCimaBaixo = new ImageView(imagemBotaoPartindoCimaBaixo);
      buttonPartindoCimaBaixo.setGraphic(imageViewBotaoPartindoCimaBaixo);
      layoutTelaDeEscolha.getChildren().add(buttonPartindoCimaBaixo);

      imageViewBotaoPartindoCimaBaixo.setFitHeight(200 * 0.83);// Define a altura da imagem
      imageViewBotaoPartindoCimaBaixo.setFitWidth(200 * 0.83);// Define a largura da imagem

      AnchorPane.setTopAnchor(buttonPartindoCimaBaixo, 600.0 * 0.83);// Posicao do botao partindo de cima
      AnchorPane.setLeftAnchor(buttonPartindoCimaBaixo, 100.0 * 0.83);// Posicao do botao partindo da esquerda

      buttonPartindoCimaBaixo.setOnAction(partindobuttonPartindoCimaBaixo -> {
        // Cria uma ImageView para exibir a imagem dos trilhos
        ImageView imageViewTrilhos = new ImageView(imagemTrilhos);

        // Cria um AnchorPane para conter os elementos da cena dos trilhos e adiciona a imagem dos trilhos a ele
        layoutTrilhos[0] = new AnchorPane();
        layoutTrilhos[0].getChildren().add(imageViewTrilhos);

        // Cria um botao de volta e define uma imagem nele
        Button buttonTelaInicial = ButtonTelaInicial();
        layoutTrilhos[0].getChildren().add(buttonTelaInicial);

        // Define e configura um label para mostrar a velocidade do trem porco
        Label labelVelocidadePorco = fundoVelocimetroPorco();
        layoutTrilhos[0].getChildren().add(labelVelocidadePorco);// Adiciona o label ao layout

        // Cria e configura a imagem do trem porco
        ImageView imageViewTremPorco = configurarImageViewPorco(555, 865, -90);
        layoutTrilhos[0].getChildren().add(imageViewTremPorco);

        // Configuracoes do slider de velocidade do trem porco
        configurarSliderPorco();

        // Posiciona o slider sobre o velocimetro
        AnchorPane.setBottomAnchor(sliderVelocidadePorco, 60.0 * 0.83);
        AnchorPane.setLeftAnchor(sliderVelocidadePorco, 23.0 * 0.83);
        layoutTrilhos[0].getChildren().add(sliderVelocidadePorco);// Adiciona o slider ao layout do trilho

        // Cria e configura um label para mostrar a velocidade do trem porco em Km/h
        velocimetroPorco();
        layoutTrilhos[0].getChildren().add(labelKmPorco);// Adiciona o label ao layout

        // Criacao do PathTransition para a animacao do trem porco
        PathTransition pathTransitionPorco = new PathTransition();

        // Configuracao do listener para o slider de velocidade do trem porco
        DoubleProperty velocidadePorco = new SimpleDoubleProperty(5);
        sliderVelocidadePorco.valueProperty().addListener((observable, oldValue, newValue) -> {
          velocidadePorco.set(newValue.doubleValue() + 0.0001);
          if (velocidadePorco.get() == 0) {
            pathTransitionPorco.pause(); // Pausa o PathTransition do porco
          } else {// Fim do if
            pathTransitionPorco.play(); // Da play no PathTransition do porco
            labelKmPorco.setText(String.format("%.2f Km/h", velocidadePorco.get()));// Atualiza o texto do label com a velocidade
          } // Fim do else
        });// Fim do Listener

        // Configuracao do PathTransition para o trem porco
        pathTransitionPorco.rateProperty().bind(velocidadePorco);
        pathTransitionPorco.setDuration(Duration.seconds(50));
        pathTransitionPorco.setPath(pathBaixoDireita);
        pathTransitionPorco.setNode(imageViewTremPorco);
        pathTransitionPorco.setCycleCount(Timeline.INDEFINITE);
        pathTransitionPorco.setInterpolator(Interpolator.LINEAR);
        pathTransitionPorco.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        // Cria e configura a imagem do trem galinha
        ImageView imageViewTremGalinha = configurarImageViewGalinha(348, 0, 90);
        layoutTrilhos[0].getChildren().add(imageViewTremGalinha);// Adiciona a imagem do trem ao layout

        // Define e configura um label para mostrar a velocidade do trem galinha
        Label labelVelocidadeGalinha = fundoVelocimetroGalinha();
        layoutTrilhos[0].getChildren().add(labelVelocidadeGalinha);// Adiciona o label ao layout

        // Configuracoes do slider de velocidade do trem galinha
        configurarSliderGalinha();

        // Posiciona o slider sobre o velocimetro
        AnchorPane.setBottomAnchor(sliderVelocidadeGalinha, 60.0 * 0.83);
        AnchorPane.setLeftAnchor(sliderVelocidadeGalinha, 620.0 * 0.83);
        layoutTrilhos[0].getChildren().add(sliderVelocidadeGalinha);// Adiciona o slider ao layout

        // Cria e configura um label para mostrar a velocidade do trem galinha em Km/h
        velocimetroGalinha();
        layoutTrilhos[0].getChildren().add(labelKmGalinha);// Adiciona o label ao layout

        // Criacao do PathTransition para a animacao do trem galinha
        PathTransition pathTransitionGalinha = new PathTransition();

        // Configuracao do listener para o slider de velocidade do trem galinha
        DoubleProperty velocidadeGalinha = new SimpleDoubleProperty(5);
        sliderVelocidadeGalinha.valueProperty().addListener((observable, oldValue, newValue) -> {
          velocidadeGalinha.set(newValue.doubleValue() + 0.0001);
          if (velocidadeGalinha.get() == 0) {
            pathTransitionGalinha.pause();// Pausa o PathTransition da galinha
          } else {// Fim do if
            pathTransitionGalinha.play();// Da play no PathTransition da Galinha
            labelKmGalinha.setText(String.format("%.2f Km/h", velocidadeGalinha.get()));// Atualiza o texto do label com a velocidade
          } // Fim do else
        });// Fim do Listener

        // Configuracao do PathTransition para o trem galinha
        pathTransitionGalinha.rateProperty().bind(velocidadeGalinha);
        pathTransitionGalinha.setDuration(Duration.seconds(50));
        pathTransitionGalinha.setPath(pathCimaEsquerda);
        pathTransitionGalinha.setNode(imageViewTremGalinha);
        pathTransitionGalinha.setCycleCount(Timeline.INDEFINITE);
        pathTransitionGalinha.setInterpolator(Interpolator.LINEAR);
        pathTransitionGalinha.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        // Define a acao a ser executada quando o botao de voltar eh clicado
        buttonTelaInicial.setOnAction(event -> {
          pathTransitionPorco.stop();// Para o PathTransition
          pathTransitionGalinha.stop();// Para o PathTransition
          sliderVelocidadePorco.setValue(0.001); // Valor inicial
          sliderVelocidadeGalinha.setValue(0.001); // Valor inicial
          primaryStage.setScene(telaDeEscolha);// Define a cena de tela de escolha como a principal
        });// Fim do botao

        Scene trilhos = new Scene(layoutTrilhos[0]);// Cria uma cena com o layout que contem o ImageView
        imageViewTrilhos.setFitHeight(900 * 0.83);// Define a altura da imagem
        imageViewTrilhos.setFitWidth(900 * 0.83);// Define a largura da imagem

        Image imagemBotaoResetar = new Image("imagens/botaoResetar.png");// Define a imagem para o botao de resetar

        // Cria um botao e define a imagem como seu grafico
        Button buttonResetar = new Button();
        buttonResetar.setGraphic(new ImageView(imagemBotaoResetar));
        layoutTrilhos[0].getChildren().add(buttonResetar);

        // Adiciona o botao ao layout e define suas ancoras de posicionamento
        AnchorPane.setTopAnchor(buttonResetar, 10.0 * 0.83);
        AnchorPane.setLeftAnchor(buttonResetar, 630.0 * 0.83);

        // Define a acao a ser executada quando o botao de resetar eh clicado
        buttonResetar.setOnAction(event -> {
          pathTransitionPorco.stop();// Para o PathTransition
          pathTransitionGalinha.stop();// Para o PathTransition

          sliderVelocidadePorco.setValue(0.001); // Valor inicial
          sliderVelocidadeGalinha.setValue(0.001); // Valor inicial
          imageViewTremGalinha.setX(348 * 0.83);// Define a posicao x do trem
          imageViewTremGalinha.setY(0);// Define a posicao y do trem

          imageViewTremPorco.setX(555 * 0.83);// Define a posicao x do trem
          imageViewTremPorco.setY(865 * 0.83);// Define a posicao y do trem
        });// Fim do botao Resetar

        primaryStage.setScene(trilhos);// Define a cena inicial como a tela de Trilhos
      });// Fim do botao (partindo direcoes opostas)

      // Define a imagem diretamente como o conteudo do botao
      Button buttonPartindoBaixoCima = new Button();
      Image imagemBotaoPartindoBaixoCima = new Image("imagens/partindoBaixoCima.png");// Carrega a imagem atraves do endereco
      ImageView imageViewBotaoPartindoBaixoCima = new ImageView(imagemBotaoPartindoBaixoCima);
      buttonPartindoBaixoCima.setGraphic(imageViewBotaoPartindoBaixoCima);
      layoutTelaDeEscolha.getChildren().add(buttonPartindoBaixoCima);

      imageViewBotaoPartindoBaixoCima.setFitHeight(200 * 0.83);// Define a altura da imagem
      imageViewBotaoPartindoBaixoCima.setFitWidth(200 * 0.83);// Define a largura da imagem

      AnchorPane.setTopAnchor(buttonPartindoBaixoCima, 600.0 * 0.83);// Posicao do botao partindo de cima
      AnchorPane.setLeftAnchor(buttonPartindoBaixoCima, 600.0 * 0.83);// Posicao do botao partindo da esquerda

      buttonPartindoBaixoCima.setOnAction(partindobuttonPartindoBaixoCima -> {
        // Cria uma ImageView para exibir a imagem dos trilhos
        ImageView imageViewTrilhos = new ImageView(imagemTrilhos);

        // Cria um AnchorPane para conter os elementos da cena dos trilhos e adiciona a imagem dos trilhos a ele
        layoutTrilhos[0] = new AnchorPane();
        layoutTrilhos[0].getChildren().add(imageViewTrilhos);

        // Cria um botao de volta e define uma imagem nele
        Button buttonTelaInicial = ButtonTelaInicial();
        layoutTrilhos[0].getChildren().add(buttonTelaInicial);

        // Define e configura um label para mostrar a velocidade do trem porco
        Label labelVelocidadePorco = fundoVelocimetroPorco();
        layoutTrilhos[0].getChildren().add(labelVelocidadePorco);// Adiciona o label ao layout

        // Cria e configura a imagem do porco
        ImageView imageViewTremPorco = configurarImageViewPorco(545, -5, 90);
        layoutTrilhos[0].getChildren().add(imageViewTremPorco);

        // Configuracoes do slider de velocidade do trem porco
        configurarSliderPorco();

        // Posiciona o slider sobre o velocimetro
        AnchorPane.setBottomAnchor(sliderVelocidadePorco, 60.0 * 0.83);
        AnchorPane.setLeftAnchor(sliderVelocidadePorco, 23.0 * 0.83);
        layoutTrilhos[0].getChildren().add(sliderVelocidadePorco);// Adiciona o slider ao layout do trilho

        // Cria e configura um label para mostrar a velocidade do trem porco em Km/h
        velocimetroPorco();
        layoutTrilhos[0].getChildren().add(labelKmPorco);// Adiciona o label ao layout

        // Criacao do PathTransition para a animacao do trem porco
        PathTransition pathTransitionPorco = new PathTransition();

        // Configuracao do listener para o slider de velocidade do trem porco
        DoubleProperty velocidadePorco = new SimpleDoubleProperty(5);
        sliderVelocidadePorco.valueProperty().addListener((observable, oldValue, newValue) -> {
          velocidadePorco.set(newValue.doubleValue() + 0.0001);
          if (velocidadePorco.get() == 0) {
            pathTransitionPorco.pause(); // Pausa o PathTransition do porco
          } else {// Fim do if
            pathTransitionPorco.play(); // Da play no PathTransition do porco
            labelKmPorco.setText(String.format("%.2f Km/h", velocidadePorco.get()));// Atualiza o texto do label com a velocidade
          } // Fim do else
        });// Fim do Listener

        // Configuracao do PathTransition para o trem porco
        pathTransitionPorco.rateProperty().bind(velocidadePorco);
        pathTransitionPorco.setDuration(Duration.seconds(50));
        pathTransitionPorco.setPath(pathCimaDireita);
        pathTransitionPorco.setNode(imageViewTremPorco);
        pathTransitionPorco.setCycleCount(Timeline.INDEFINITE);
        pathTransitionPorco.setInterpolator(Interpolator.LINEAR);
        pathTransitionPorco.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        // Cria e configura a imagem do trem galinha
        ImageView imageViewTremGalinha = configurarImageViewGalinha(355, 865, -90);
        layoutTrilhos[0].getChildren().add(imageViewTremGalinha);// Adiciona a imagem do trem ao layout

        // Define e configura um label para mostrar a velocidade do trem galinha
        Label labelVelocidadeGalinha = fundoVelocimetroGalinha();
        layoutTrilhos[0].getChildren().add(labelVelocidadeGalinha);// Adiciona o label ao layout

        // Configuracoes do slider de velocidade do trem galinha
        configurarSliderGalinha();

        // Posiciona o slider sobre o velocimetro
        AnchorPane.setBottomAnchor(sliderVelocidadeGalinha, 60.0 * 0.83);
        AnchorPane.setLeftAnchor(sliderVelocidadeGalinha, 620.0 * 0.83);
        layoutTrilhos[0].getChildren().add(sliderVelocidadeGalinha);// Adiciona o slider ao layout

        // Cria e configura um label para mostrar a velocidade do trem galinha em Km/h
        velocimetroGalinha();
        layoutTrilhos[0].getChildren().add(labelKmGalinha);// Adiciona o label ao layout

        // Criacao do PathTransition para a animacao do trem galinha
        PathTransition pathTransitionGalinha = new PathTransition();

        // Configuracao do listener para o slider de velocidade do trem galinha
        DoubleProperty velocidadeGalinha = new SimpleDoubleProperty(5);
        sliderVelocidadeGalinha.valueProperty().addListener((observable, oldValue, newValue) -> {
          velocidadeGalinha.set(newValue.doubleValue() + 0.0001);
          if (velocidadeGalinha.get() == 0) {
            pathTransitionGalinha.pause();// Pausa o PathTransition da galinha
          } else {// Fim do if
            pathTransitionGalinha.play();// Da play no PathTransition da Galinha
            labelKmGalinha.setText(String.format("%.2f Km/h", velocidadeGalinha.get()));// Atualiza o texto do label com a velocidade
          } // Fim do else
        });// Fim do Listener

        // Configuracao do PathTransition para o trem galinha
        pathTransitionGalinha.rateProperty().bind(velocidadeGalinha);
        pathTransitionGalinha.setDuration(Duration.seconds(50));
        pathTransitionGalinha.setPath(pathBaixoEsquerda);
        pathTransitionGalinha.setNode(imageViewTremGalinha);
        pathTransitionGalinha.setCycleCount(Timeline.INDEFINITE);
        pathTransitionGalinha.setInterpolator(Interpolator.LINEAR);
        pathTransitionGalinha.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        Image imagemBotaoResetar = new Image("imagens/botaoResetar.png");// Define a imagem para o botao de resetar

        // Cria um botao e define a imagem como seu grafico
        Button buttonResetar = new Button();
        buttonResetar.setGraphic(new ImageView(imagemBotaoResetar));
        layoutTrilhos[0].getChildren().add(buttonResetar);

        // Adiciona o botao ao layout e define suas ancoras de posicionamento
        AnchorPane.setTopAnchor(buttonResetar, 10.0 * 0.83);
        AnchorPane.setLeftAnchor(buttonResetar, 630.0 * 0.83);

        // Define a acao a ser executada quando o botao de resetar eh clicado
        buttonResetar.setOnAction(event -> {
          pathTransitionPorco.stop();// Para o PathTransition
          pathTransitionGalinha.stop();// Para o PathTransition

          sliderVelocidadePorco.setValue(0.001); // Valor inicial
          sliderVelocidadeGalinha.setValue(0.001); // Valor inicial
          imageViewTremGalinha.setX(355 * 0.83);// Define a posicao x do trem
          imageViewTremGalinha.setY(865 * 0.83);// Define a posicao y do trem

          imageViewTremPorco.setX(545 * 0.83);// Define a posicao x do trem
          imageViewTremPorco.setY(-5 * 0.83);// Define a posicao y do trem
        });// Fim do botao

        // Define a acao a ser executada quando o botao de voltar eh clicado
        buttonTelaInicial.setOnAction(event -> {
          pathTransitionPorco.stop();// Para o PathTransition
          pathTransitionGalinha.stop();// Para o PathTransition
          sliderVelocidadePorco.setValue(0.001); // Valor inicial
          sliderVelocidadeGalinha.setValue(0.001); // Valor inicial
          primaryStage.setScene(telaDeEscolha);// Define a cena de tela de escolha como a principal
        });// Fim do botao

        Scene trilhos = new Scene(layoutTrilhos[0]);// Cria uma cena com o layout que contem o ImageView
        imageViewTrilhos.setFitHeight(900 * 0.83);// Define a altura da imagem
        imageViewTrilhos.setFitWidth(900 * 0.83);// Define a largura da imagem

        primaryStage.setScene(trilhos);// Define a cena inicial como a tela de Trilhos
      });// Fim do botao partindo direcoes opostas (INVERSO)

    });// Fim da tela de escolha de Direcao

    primaryStage.setScene(telaInicial);// cena definida na janela principal
    primaryStage.setTitle("Train Simulator");// titulo do Programa
    primaryStage.show();// Exibe a janela
    primaryStage.setResizable(false);// impede o usuario de redimensionar a janela
  }// Fim do metodo Start

  /** ***************************************************************
   * Metodo: ButtonTelaInicial
   * Funcao: Configura e retorna um botao com a imagem de voltar a tela inicial
   * Retorno: Um botao com a imagem configurada e posicionado
   **********************************************************************/
  private Button ButtonTelaInicial() {
    Button botao = new Button();
    Image imagemButtonTelaInicial = new Image("imagens/botaoVoltar.png");
    botao.setGraphic(new ImageView(imagemButtonTelaInicial));

    // Adiciona o botao de volta ao AnchorPane e define suas coordenadas
    AnchorPane.setTopAnchor(botao, 10.0 * 0.83);
    AnchorPane.setLeftAnchor(botao, 10.0 * 0.83);

    return botao;
  }

  /** ***************************************************************
   * Metodo: fundoVelocimetroGalinha
   * Funcao: Configura e retorna um Label com o fundo do velocimetro da galinha
   * Retorno: Um Label contendo o fundo do velocimetro da galinha configurado
   **********************************************************************/
  private Label fundoVelocimetroGalinha() {
    Label label = new Label();// Define um label
    Image imagemVelocidadeGalinha = new Image("imagens/velocidadeGalinha.png");// Carrega a imagem do velocimetro da galinha

    label.setGraphic(new ImageView(imagemVelocidadeGalinha));// define a imagem ao label
    label.setLayoutX(600 * 0.83); // Define a posicao x do Label
    label.setLayoutY(650 * 0.83); // Define a posicao y do Label

    return label;
  }

  /*****************************************************************
   * Metodo: fundoVelocimetroPorco
   * Funcao: Configura e retorna um Label com o fundo do velocimetro do porco
   * Retorno: Um Label contendo o fundo do velocimetro do porco configurado
   **********************************************************************/
  private Label fundoVelocimetroPorco() {
    Label label = new Label();
    Image imagemVelocidadePorco = new Image("imagens/velocidadePorco.png"); // Carrega a imagem do velocimetro do porco

    label.setGraphic(new ImageView(imagemVelocidadePorco));// Define a imagem ao label
    label.setLayoutX(10 * 0.83);// Define a posicao x do Label
    label.setLayoutY(650 * 0.83);// Define a posicao y do Label

    return label;
  }

  /** ***************************************************************
   * Metodo: ConfigurarImageViewGalinha
   * Funcao: Configura e retorna um ImageView para representar o trem da galinha
   * Parametros:
   * - x: posicao x do trem (int)
   * - y: posicao y do trem (int)
   * - rotacao: angulo de rotacao do trem (int)
   * Retorno: ImageView configurado
   **********************************************************************/
  private ImageView configurarImageViewGalinha(int x, int y, int rotacao) {
    Image imagemTremGalinha = new Image("imagens/galinhaTrem.png");// Carrega a imagem do trem da galinha
    ImageView imageView = new ImageView(imagemTremGalinha);

    imageView.setFitHeight(30.0 * 0.83);// Define a altura do trem
    imageView.setFitWidth(50.0 * 0.83);// Define a largura do trem
    imageView.setX(x * 0.83);// Define a posicao x do trem
    imageView.setY(y * 0.83);// Define a posicao y do trem
    imageView.setRotate(rotacao);// Rotaciona a imagem do trem

    return imageView;
  }

  /** ***************************************************************
   * Metodo: ConfigurarImageViewPorco
   * Funcao: Configura e retorna um ImageView para representar o trem do porco
   * Parametros:
   * - x: posicao x do trem (int)
   * - y: posicao y do trem (int)
   * - rotacao: angulo de rotacao do trem (int)
   * Retorno: ImageView configurado
   **********************************************************************/
  private ImageView configurarImageViewPorco(int x, int y, int rotacao) {
    Image imagemTremPorco = new Image("imagens/porcoTrem.png");// Carrega a imagem do trem do porco
    ImageView imageView = new ImageView(imagemTremPorco);

    imageView.setFitHeight(30.0 * 0.83);// Define a altura do trem
    imageView.setFitWidth(50.0 * 0.83);// Define a largura do trem
    imageView.setX(x * 0.83);// Define a posicao x do trem
    imageView.setY(y * 0.83);// Define a posicao y do trem
    imageView.setRotate(rotacao);// Rotaciona a imagem do trem

    return imageView;
  }

  /** ***************************************************************
   * Metodo: velocimetroGalinha
   * Funcao: Cria e configura um label para exibir a velocidade da galinha
   * Retorno: label configurado
   **********************************************************************/
  private Label velocimetroGalinha() {
    Label label = new Label();

    label.setFont(Font.font("Arial", FontWeight.BOLD, 25 * 0.83));// Define a fonte e o tamanho do texto
    label.setTextFill(Color.BLACK);// Define a cor do texto
    label.setLayoutX(680 * 0.83);// Define a posicao x do Label
    label.setLayoutY(680 * 0.83);// Define a posicao y do Label
    label.setText(String.format(Integer.toString(0) + " Km/s"));// Define o texto inicial do label

    return label;
  }

  /** ***************************************************************
   * Metodo: velocimetroPorco
   * Funcao: Cria e configura um label para exibir a velocidade do porco
   * Retorno: label configurado
   **********************************************************************/
  private Label velocimetroPorco() {
    Label label = new Label();
    label.setFont(Font.font("Arial", FontWeight.BOLD, 25 * 0.83));// Define a fonte e o tamanho do texto
    label.setTextFill(Color.BLACK);// Define a cor do texto
    label.setLayoutX(90 * 0.83);// Define a posicao x do Label
    label.setLayoutY(680 * 0.83);// Define a posicao y do Label
    label.setText(String.format(Integer.toString(0) + " Km/s"));// Define o texto inicial do label

    return label;
  }

  /** ***************************************************************
   * Metodo: configurarSliderPorco
   * Funcao: Configura um slider para controlar a velocidade da porco
   * Retorno: slider configurado
   **********************************************************************/
  private Slider configurarSliderPorco() {
    Slider slider = new Slider();

    slider.setMin(0);// Valor minimo
    slider.setMax(20);// Valor maximo
    slider.setValue(0); // Valor inicial
    slider.setPrefWidth(220); // Define a largura do slider
    slider.setMajorTickUnit(1); // Define a unidade principal dos ticks do slider
    slider.setBlockIncrement(1);// Define o incremento do valor ao mover o slider
    slider.setOrientation(Orientation.HORIZONTAL);// Define a orientacao do slider como horizontal

    return slider;
  }

  /* ***************************************************************
   * Metodo: configurarSliderGalinha
   * Funcao: Configura um slider para controlar a velocidade da galinha
   * Retorno: slider configurado
   **********************************************************************/
  private Slider configurarSliderGalinha() {
    Slider slider2 = new Slider();

    slider2.setMin(0);// Valor minimo
    slider2.setMax(20);// Valor maximo
    slider2.setValue(0); // Valor inicial
    slider2.setPrefWidth(220); // Define a largura do slider
    slider2.setMajorTickUnit(1);// Define a unidade principal dos ticks do slider
    slider2.setBlockIncrement(1); // Define o incremento do valor ao mover o slider
    slider2.setOrientation(Orientation.HORIZONTAL);// Define a orientacao do slider como horizontal

    return slider2;
  }

  /** **********************************************************************
   * Metodo: baixoEsquerda
   * Funcao: Define o caminho para a animacao do trem na direcao baixo-esquerda
   * Retorno: Um objeto Path representando o caminho
   **********************************************************************/
  private Path baixoEsquerda() {
    Path path = new Path();

    path.getElements().add(new MoveTo(381 * 0.83, 883 * 0.83));
    path.getElements().add(new LineTo(381 * 0.83, 803 * 0.83));

    path.getElements().add(new MoveTo(381 * 0.83, 803 * 0.83));
    path.getElements().add(new LineTo(475 * 0.83, 704 * 0.83));

    path.getElements().add(new MoveTo(475 * 0.83, 704 * 0.83));
    path.getElements().add(new LineTo(475 * 0.83, 600 * 0.83));

    path.getElements().add(new MoveTo(475 * 0.83, 600 * 0.83));
    path.getElements().add(new LineTo(381 * 0.83, 498 * 0.83));

    path.getElements().add(new MoveTo(381 * 0.83, 498 * 0.83));
    path.getElements().add(new LineTo(381 * 0.83, 403 * 0.83));

    path.getElements().add(new MoveTo(381 * 0.83, 403 * 0.83));
    path.getElements().add(new LineTo(475 * 0.83, 300 * 0.83));

    path.getElements().add(new MoveTo(475 * 0.83, 300 * 0.83));
    path.getElements().add(new LineTo(475 * 0.83, 196 * 0.83));

    path.getElements().add(new MoveTo(475 * 0.83, 196 * 0.83));
    path.getElements().add(new LineTo(381 * 0.83, 94 * 0.83));

    path.getElements().add(new MoveTo(381 * 0.83, 94 * 0.83));
    path.getElements().add(new LineTo(381 * 0.83, -20 * 0.83));

    return path;
  }

  /** **********************************************************************
   * Metodo: baixoDireita
   * Funcao: Define o caminho para a animacao do trem na direcao baixo-Direita
   * Retorno: Um objeto Path representando o caminho
   **********************************************************************/
  private Path baixoDireita() {
    Path path2 = new Path();

    path2.getElements().add(new MoveTo(579 * 0.83, 883 * 0.83));
    path2.getElements().add(new LineTo(579 * 0.83, 803 * 0.83));

    path2.getElements().add(new MoveTo(579 * 0.83, 803 * 0.83));
    path2.getElements().add(new LineTo(475 * 0.83, 689 * 0.83));

    path2.getElements().add(new MoveTo(475 * 0.83, 689 * 0.83));
    path2.getElements().add(new LineTo(475 * 0.83, 600 * 0.83));

    path2.getElements().add(new MoveTo(475 * 0.83, 600 * 0.83));
    path2.getElements().add(new LineTo(579 * 0.83, 498 * 0.83));

    path2.getElements().add(new MoveTo(579 * 0.83, 498 * 0.83));
    path2.getElements().add(new LineTo(579 * 0.83, 403 * 0.83));

    path2.getElements().add(new MoveTo(579 * 0.83, 403 * 0.83));
    path2.getElements().add(new LineTo(475 * 0.83, 300 * 0.83));

    path2.getElements().add(new MoveTo(475 * 0.83, 300 * 0.83));
    path2.getElements().add(new LineTo(475 * 0.83, 196 * 0.83));

    path2.getElements().add(new MoveTo(475 * 0.83, 196 * 0.83));
    path2.getElements().add(new LineTo(579 * 0.83, 94 * 0.83));

    path2.getElements().add(new MoveTo(579 * 0.83, 94 * 0.83));
    path2.getElements().add(new LineTo(579 * 0.83, -20 * 0.83));
    return path2;
  }

  /** **********************************************************************
   * Metodo: cimaEsquerda
   * Funcao: Define o caminho para a animacao do trem na direcao cima-esquerda
   * Retorno: Um objeto Path representando o caminho
   **********************************************************************/
  private Path cimaEsquerda() {
    Path path3 = new Path();

    path3.getElements().add(new MoveTo(372 * 0.83, 16 * 0.83));
    path3.getElements().add(new LineTo(372 * 0.83, 92 * 0.83));

    path3.getElements().add(new MoveTo(372 * 0.83, 92 * 0.83));
    path3.getElements().add(new LineTo(472 * 0.83, 201 * 0.83));

    path3.getElements().add(new MoveTo(472 * 0.83, 201 * 0.83));
    path3.getElements().add(new LineTo(472 * 0.83, 303 * 0.83));

    path3.getElements().add(new MoveTo(472 * 0.83, 303 * 0.83));
    path3.getElements().add(new LineTo(377 * 0.83, 402 * 0.83));

    path3.getElements().add(new MoveTo(377 * 0.83, 402 * 0.83));
    path3.getElements().add(new LineTo(377 * 0.83, 500 * 0.83));

    path3.getElements().add(new MoveTo(377 * 0.83, 500 * 0.83));
    path3.getElements().add(new LineTo(472 * 0.83, 600 * 0.83));

    path3.getElements().add(new MoveTo(472 * 0.83, 600 * 0.83));
    path3.getElements().add(new LineTo(472 * 0.83, 705 * 0.83));

    path3.getElements().add(new MoveTo(472 * 0.83, 705 * 0.83));
    path3.getElements().add(new LineTo(381 * 0.83, 800 * 0.83));

    path3.getElements().add(new MoveTo(381 * 0.83, 800 * 0.83));
    path3.getElements().add(new LineTo(381 * 0.83, 900 * 0.83));

    return path3;
  }

  /** **********************************************************************
   * Metodo: cimaDireita
   * Funcao: Define o caminho para a animacao do trem na direcao cima-direita
   * Retorno: Um objeto Path representando o caminho
   **********************************************************************/
  private Path cimaDireita() {
    Path path4 = new Path();

    path4.getElements().add(new MoveTo(570 * 0.83, 10 * 0.83));
    path4.getElements().add(new LineTo(570 * 0.83, 94 * 0.83));

    path4.getElements().add(new MoveTo(570 * 0.83, 94 * 0.83));
    path4.getElements().add(new LineTo(472 * 0.83, 197 * 0.83));

    path4.getElements().add(new MoveTo(472 * 0.83, 197 * 0.83));
    path4.getElements().add(new LineTo(472 * 0.83, 300 * 0.83));

    path4.getElements().add(new MoveTo(472 * 0.83, 300 * 0.83));
    path4.getElements().add(new LineTo(573 * 0.83, 400 * 0.83));

    path4.getElements().add(new MoveTo(573 * 0.83, 400 * 0.83));
    path4.getElements().add(new LineTo(573 * 0.83, 500 * 0.83));

    path4.getElements().add(new MoveTo(573 * 0.83, 500 * 0.83));
    path4.getElements().add(new LineTo(477 * 0.83, 604 * 0.83));

    path4.getElements().add(new MoveTo(477 * 0.83, 604 * 0.83));
    path4.getElements().add(new LineTo(477 * 0.83, 702 * 0.83));

    path4.getElements().add(new MoveTo(477 * 0.83, 702 * 0.83));
    path4.getElements().add(new LineTo(577 * 0.83, 802 * 0.83));

    path4.getElements().add(new MoveTo(577 * 0.83, 802 * 0.83));
    path4.getElements().add(new LineTo(577 * 0.83, 910 * 0.83));

    return path4;
  }
}