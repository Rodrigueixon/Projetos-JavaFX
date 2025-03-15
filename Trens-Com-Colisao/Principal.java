/* ******************************************************************************************************
* Autor: Rodrigo Ferreira Bento Aguiar
* Matricula: 202310594
* Inicio: 16/05/2024
* Ultima alteracao: 26/05/2024
* Nome: Principal
* Descricao: O objetivo deste trabalho eh implementar um sistema de controle de trens utilizando tecnicas
*            de programacao concorrente para evitar colisoes entre dois trens em uma secao critica da pista.
******************************************************************************************************* */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Principal extends Application {
  public static void main(String[] args) {
    launch(args); // Inicia a aplicacao JavaFX
  }// fim do main

  //Variaveis de Travamento
  public static volatile int variavelDeTravamento1 = 0;
  public static volatile int variavelDeTravamento2 = 0;
  
  //Variaveis da Estrita Alternancia
  public static volatile int vez1 = 0;
  public static volatile int vez2 = 0;
  
  //Variaveis da Solucao de Peterson
  public static volatile int vez1Peterson;
  public static volatile int vez2Peterson;
  public static volatile boolean[] interesse1 = {false,false};
  public static volatile boolean[] interesse2 = {false,false};
  
  /**********************************************************************
   * Metodo: start
   * Funcao: Inicializa a aplicacao JavaFX
   * Parametros: primaryStage = a janela principal da aplicacao (Stage)
   * Retorno: void
   **********************************************************************/
  @Override
  public void start(Stage primaryStage) throws Exception {

    Image imagemTelaInicial = new Image("imagens/telaInicial.png");// Carrega a imagem atraves do endereco
    ImageView imageViewTelaInicial = new ImageView(imagemTelaInicial);// Cria um ImageView e define a imagem nele
    imageViewTelaInicial.setFitHeight(750);// Define a altura da imagem
    imageViewTelaInicial.setFitWidth(750);// Define a largura da imagem
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
    AnchorPane.setTopAnchor(buttonStart, 625.0);
    AnchorPane.setLeftAnchor(buttonStart, 83.3);

    final AnchorPane layoutTrilhos[] = new AnchorPane[1];// Declara um array de AnchorPane para armazenar layouts de trilhos


    buttonStart.setOnAction(start -> {
      Image imagemTelaDeEscolha = new Image("imagens/telaEscolhaDeDirecao.png");// Carrega a imagem da tela de escolha
      Image imagemTrilhos = new Image("imagens/trilhos.png");// Carrega a imagem dos trilhos

      // Criacao dos sliders para controlar a velocidade dos trens
      ElementosGraficos elementosGraficos = new ElementosGraficos();
      Slider sliderVelocidadePorco = elementosGraficos.configurarSliderPorco();
      Slider sliderVelocidadeGalinha = elementosGraficos.configurarSliderGalinha();

      ImageView imageViewTelaDeEscolha = new ImageView(imagemTelaDeEscolha); // Cria um ImageView e define a imagem nele
      AnchorPane layoutTelaDeEscolha = new AnchorPane();// Cria um layout para conter o ImageView
      layoutTelaDeEscolha.getChildren().add(imageViewTelaDeEscolha);// Adiciona o ImageView ao layout
      Scene telaDeEscolha = new Scene(layoutTelaDeEscolha);// Cria uma cena com o layout que contem o ImageView

      imageViewTelaDeEscolha.setFitHeight(750);// Define a altura da imagem
      imageViewTelaDeEscolha.setFitWidth(750);// Define a largura da imagem

      primaryStage.setScene(telaDeEscolha);// Define a cena inicial como a tela de escolha de direcao

      // Define a imagem diretamente como o conteudo do botao
      Button buttonPartindoBaixoJuntos = new Button();
      Image imagemBotaoPartindoBaixoJuntos = new Image("imagens/partindoBaixoJuntos.png");

      // Define a imagem como grafico do botao e adiciona o botao ao layout da tela de escolha de direcao
      ImageView imageViewBotaoPartindoBaixoJuntos = new ImageView(imagemBotaoPartindoBaixoJuntos);
      buttonPartindoBaixoJuntos.setGraphic(imageViewBotaoPartindoBaixoJuntos);
      layoutTelaDeEscolha.getChildren().add(buttonPartindoBaixoJuntos);

      imageViewBotaoPartindoBaixoJuntos.setFitHeight(167.0);// Define a altura da imagem
      imageViewBotaoPartindoBaixoJuntos.setFitWidth(167.0);// Define a largura da imagem

      AnchorPane.setTopAnchor(buttonPartindoBaixoJuntos, 292.0);// Posicao do botao partindo de cima
      AnchorPane.setLeftAnchor(buttonPartindoBaixoJuntos, 83.3);// Posicao do botao partindo da esquerda


//______________________________AMBOS PARTINDO POR BAIXO_____________________________________________________________
      buttonPartindoBaixoJuntos.setOnAction(partindoBaixoJuntos -> {
        // Cria uma ImageView para exibir a imagem dos trilhos
        ImageView imageViewTrilhos = new ImageView(imagemTrilhos);

        // Cria um AnchorPane para conter os elementos da cena dos trilhos e adiciona a imagem dos trilhos a ele
        layoutTrilhos[0] = new AnchorPane();
        layoutTrilhos[0].getChildren().add(imageViewTrilhos);

        // Cria um botao de volta e define uma imagem nele
        Button buttonTelaInicial = elementosGraficos.ButtonTelaInicial();
        layoutTrilhos[0].getChildren().add(buttonTelaInicial);

        // Cria a cena AnchorPane contendo a imagem dos trilhos e define o seu tamanho
        Scene trilhos = new Scene(layoutTrilhos[0]);
        imageViewTrilhos.setFitHeight(750);
        imageViewTrilhos.setFitWidth(750);

        // Define e configura um label para mostrar a velocidade do trem porco
        Label labelVelocidadePorco = elementosGraficos.fundoVelocimetroPorco();
        layoutTrilhos[0].getChildren().add(labelVelocidadePorco);// Adiciona o label ao layout

        // Cria e configura a imagem do trem porco
        Image imagemTremPorco = new Image("imagens/porcoTrem.png");// Carrega a imagem do trem do porco
        ImageView imageViewTremPorco = new ImageView(imagemTremPorco);
        layoutTrilhos[0].getChildren().add(imageViewTremPorco);// Adiciona a imagem do trem ao layout
        
        // Configuracoes do slider de velocidade do trem porco
        elementosGraficos.configurarSliderPorco();
        
        //instancia o objeto tremPorco da classe com Thread TremPorco
        TremPorco tremPorco = new TremPorco(imageViewTremPorco, sliderVelocidadePorco, 306, 710, true);
        tremPorco.start();

        // Posiciona o slider sobre o velocimetro
        AnchorPane.setBottomAnchor(sliderVelocidadePorco, 50.0);
        AnchorPane.setLeftAnchor(sliderVelocidadePorco, 19.0);
        layoutTrilhos[0].getChildren().add(sliderVelocidadePorco);// Adiciona o slider ao layout do trilho

        // Cria e configura a imagem do trem Galinha
        Image imagemTremGalinha = new Image("imagens/galinhaTrem.png");// Carrega a imagem do trem do Galinha
        ImageView imageViewTremGalinha = new ImageView(imagemTremGalinha);
        layoutTrilhos[0].getChildren().add(imageViewTremGalinha);

        // Define e configura um label para mostrar a velocidade do trem galinha
        Label labelVelocidadeGalinha = elementosGraficos.fundoVelocimetroGalinha();
        layoutTrilhos[0].getChildren().add(labelVelocidadeGalinha);// Adiciona o label ao layout

        // Configuracoes do slider de velocidade do trem galinha
        elementosGraficos.configurarSliderGalinha();

        //instancia o objeto tremGalinha da classe com Thread TremGalinha
        TremGalinha tremGalinha = new TremGalinha(imageViewTremGalinha, sliderVelocidadeGalinha, 470, 710, true);
        tremGalinha.start();

        // Posiciona o slider sobre o velocimetro
        AnchorPane.setBottomAnchor(sliderVelocidadeGalinha, 50.0);
        AnchorPane.setLeftAnchor(sliderVelocidadeGalinha, 517.0);
        layoutTrilhos[0].getChildren().add(sliderVelocidadeGalinha);// Adiciona o slider ao layout

        Image imagemBotaoResetar = new Image("imagens/botaoResetar.png");// Define a imagem para o botao de resetar

        // Cria um botao e define a imagem como seu grafico
        Button buttonResetar = new Button();
        buttonResetar.setGraphic(new ImageView(imagemBotaoResetar));
        layoutTrilhos[0].getChildren().add(buttonResetar);

        // Adiciona o botao ao layout e define suas ancoras de posicionamento
        AnchorPane.setTopAnchor(buttonResetar, 8.3);
        AnchorPane.setLeftAnchor(buttonResetar, 525.0);

        // Define a acao a ser executada quando o botao de resetar eh clicado
        buttonResetar.setOnAction(event -> {
          tremPorco.resetarTremPorco(true);
          tremGalinha.resetarTremGalinha(true);
        });// Fim do botao Resetar

        // Define a acao a ser executada quando o botao de voltar eh clicado
        buttonTelaInicial.setOnAction(event -> {
          tremPorco.resetarTremPorco(true);
          tremGalinha.resetarTremGalinha(true);
          primaryStage.setScene(telaDeEscolha);// Define a cena de tela de escolha como a principal
        });// Fim do botao

        // Criação do ToggleGroup
        ToggleGroup grupoSolucoes = new ToggleGroup();

        // Criação e configuração dos novos RadioButtons
        RadioButton radioButtonVariavelDeTravamento = elementosGraficos.radioButtonVariavelDeTravamento(grupoSolucoes);
        RadioButton radioButtonEstritaAlternancia = elementosGraficos.radioButtonEstritaAlternancia(grupoSolucoes);
        RadioButton radioButtonSolucaoDePeterson = elementosGraficos.radioButtonSolucaoDePeterson(grupoSolucoes);

        radioButtonVariavelDeTravamento.setOnAction(event -> {
          tremPorco.setSolucao("variavelDeTravamento");
          tremGalinha.setSolucao("variavelDeTravamento");
          tremPorco.resetarTremPorco(true);
          tremGalinha.resetarTremGalinha(true);
          
        });
        layoutTrilhos[0].getChildren().add(radioButtonVariavelDeTravamento);
        radioButtonVariavelDeTravamento.fire();
        
        radioButtonEstritaAlternancia.setOnAction(event ->{
          tremPorco.setSolucao("estritaAlternancia");
          tremGalinha.setSolucao("estritaAlternancia");
          tremPorco.resetarTremPorco(true);
          tremGalinha.resetarTremGalinha(true);
        });
        layoutTrilhos[0].getChildren().add(radioButtonEstritaAlternancia);
        
        radioButtonSolucaoDePeterson.setOnAction(event -> {
          tremPorco.setSolucao("solucaoDePeterson");
          tremGalinha.setSolucao("solucaoDePeterson");
          tremPorco.resetarTremPorco(true);
          tremGalinha.resetarTremGalinha(true);
        });
        layoutTrilhos[0].getChildren().add(radioButtonSolucaoDePeterson);

        primaryStage.setScene(trilhos);// Define a cena inicial como a tela de Trilhos
      });// Fim do botao (partindo baixo juntos)

      // Define a imagem diretamente como o conteudo do botao
      Button buttonPartindoCimaJuntos = new Button(); // Cria o botao (partindo juntos por cima)
      Image imagemBotaoPartindoCimaJuntos = new Image("imagens/partindoCimaJuntos.png");// Carrega a imagem

      ImageView imageViewBotaoPartindoCimaJuntos = new ImageView(imagemBotaoPartindoCimaJuntos);
      buttonPartindoCimaJuntos.setGraphic(imageViewBotaoPartindoCimaJuntos);
      layoutTelaDeEscolha.getChildren().add(buttonPartindoCimaJuntos);

      imageViewBotaoPartindoCimaJuntos.setFitHeight(167.0);// Define a altura da imagem
      imageViewBotaoPartindoCimaJuntos.setFitWidth(167.0);// Define a largura da imagem

      AnchorPane.setTopAnchor(buttonPartindoCimaJuntos, 292.0);// Posicao do botao partindo de cima
      AnchorPane.setLeftAnchor(buttonPartindoCimaJuntos, 500.0);// Posicao do botao partindo da esquerda


      
      
// ____________________________________AMBOS PARTINDO POR CIMA_______________________________________________________
      buttonPartindoCimaJuntos.setOnAction(partindoCimaJuntos -> {
        // Cria uma ImageView para exibir a imagem dos trilhos
        ImageView imageViewTrilhos = new ImageView(imagemTrilhos);

        // Cria um AnchorPane para conter os elementos da cena dos trilhos e adiciona a
        // imagem dos trilhos a ele
        layoutTrilhos[0] = new AnchorPane();
        layoutTrilhos[0].getChildren().add(imageViewTrilhos);

        // Cria um botao de volta e define uma imagem nele
        Button buttonTelaInicial = elementosGraficos.ButtonTelaInicial();
        layoutTrilhos[0].getChildren().add(buttonTelaInicial);

        // Define e configura um label para mostrar a velocidade do trem porco
        Label labelVelocidadePorco = elementosGraficos.fundoVelocimetroPorco();
        layoutTrilhos[0].getChildren().add(labelVelocidadePorco);// Adiciona o label ao layout

        // Cria e configura a imagem do trem porco
        Image imagemTremPorco = new Image("imagens/porcoTrem.png");// Carrega a imagem do trem do porco
        ImageView imageViewTremPorco = new ImageView(imagemTremPorco);
        layoutTrilhos[0].getChildren().add(imageViewTremPorco);

        // Configura o slider de velocidade do trem porco
        elementosGraficos.configurarSliderPorco();

        //instancia o objeto tremPorco da classe com Thread TremPorco
        TremPorco tremPorco = new TremPorco(imageViewTremPorco, sliderVelocidadePorco, 306, 0, false);
        tremPorco.start();

        // Posiciona o slider sobre o velocimetro
        AnchorPane.setBottomAnchor(sliderVelocidadePorco, 50.0);
        AnchorPane.setLeftAnchor(sliderVelocidadePorco, 19.0);
        layoutTrilhos[0].getChildren().add(sliderVelocidadePorco);// Adiciona o slider ao layout do trilho

        // Cria e configura a imagem do trem Galinha
        Image imagemTremGalinha = new Image("imagens/galinhaTrem.png");// Carrega a imagem do trem do Galinha
        ImageView imageViewTremGalinha = new ImageView(imagemTremGalinha);
        layoutTrilhos[0].getChildren().add(imageViewTremGalinha);

        // Define e configura um label para mostrar a velocidade do trem galinha
        Label labelVelocidadeGalinha = elementosGraficos.fundoVelocimetroGalinha();
        layoutTrilhos[0].getChildren().add(labelVelocidadeGalinha);// Adiciona o label ao layout

        // Configuracoes do slider de velocidade do trem galinha
        elementosGraficos.configurarSliderGalinha();

        //instancia o objeto tremGalinha da classe com Thread TremGalinha
        TremGalinha tremGalinha = new TremGalinha(imageViewTremGalinha, sliderVelocidadeGalinha, 470, 0, false);
        tremGalinha.start();

        // Posiciona o slider sobre o velocimetro
        AnchorPane.setBottomAnchor(sliderVelocidadeGalinha, 50.0);
        AnchorPane.setLeftAnchor(sliderVelocidadeGalinha, 517.0);
        layoutTrilhos[0].getChildren().add(sliderVelocidadeGalinha);// Adiciona o slider ao layout

        // Define a acao a ser executada quando o botao de voltar eh clicado
        buttonTelaInicial.setOnAction(event -> {
          tremPorco.pararTremPorco();
          tremPorco.resetarTremPorco(false);
          tremGalinha.pararTremGalinha();
          tremGalinha.resetarTremGalinha(false);
          primaryStage.setScene(telaDeEscolha);// Define a cena de tela de escolha como a principal
        });// Fim do botao

        Scene trilhos = new Scene(layoutTrilhos[0]);// Cria uma cena com o layout que contem o ImageView
        imageViewTrilhos.setFitHeight(750);// Define a altura da imagem
        imageViewTrilhos.setFitWidth(750);// Define a largura da imagem

        Image imagemBotaoResetar = new Image("imagens/botaoResetar.png");// Define a imagem para o botao de resetar

        // Cria um botao e define a imagem como seu grafico
        Button buttonResetar = new Button();
        buttonResetar.setGraphic(new ImageView(imagemBotaoResetar));
        layoutTrilhos[0].getChildren().add(buttonResetar);

        // Adiciona o botao ao layout e define suas ancoras de posicionamento
        AnchorPane.setTopAnchor(buttonResetar, 8.3);
        AnchorPane.setLeftAnchor(buttonResetar, 525.0);

        // Define a acao a ser executada quando o botao de resetar eh clicado
        buttonResetar.setOnAction(event -> {
          tremPorco.resetarTremPorco(false);
          tremGalinha.resetarTremGalinha(false);
        });// Fim do botao Resetar

        ToggleGroup grupoSolucoes = new ToggleGroup();

        // Criação e configuração dos novos RadioButtons
        RadioButton radioButtonVariavelDeTravamento = elementosGraficos.radioButtonVariavelDeTravamento(grupoSolucoes);
        RadioButton radioButtonEstritaAlternancia = elementosGraficos.radioButtonEstritaAlternancia(grupoSolucoes);
        RadioButton radioButtonSolucaoDePeterson = elementosGraficos.radioButtonSolucaoDePeterson(grupoSolucoes);

        radioButtonVariavelDeTravamento.setOnAction(event -> {
          tremPorco.setSolucao("variavelDeTravamento");
          tremGalinha.setSolucao("variavelDeTravamento");
          tremPorco.resetarTremPorco(false);
          tremGalinha.resetarTremGalinha(false);
        });
        layoutTrilhos[0].getChildren().add(radioButtonVariavelDeTravamento);
        radioButtonVariavelDeTravamento.fire();
        
        radioButtonEstritaAlternancia.setOnAction(event ->{
          tremPorco.setSolucao("estritaAlternancia");
          tremGalinha.setSolucao("estritaAlternancia");
          tremPorco.resetarTremPorco(false);
          tremGalinha.resetarTremGalinha(false);
        });
        layoutTrilhos[0].getChildren().add(radioButtonEstritaAlternancia);
        
        radioButtonSolucaoDePeterson.setOnAction(event -> {
          tremPorco.setSolucao("solucaoDePeterson");
          tremGalinha.setSolucao("solucaoDePeterson");
          tremPorco.resetarTremPorco(false);
          tremGalinha.resetarTremGalinha(false);
        });
        layoutTrilhos[0].getChildren().add(radioButtonSolucaoDePeterson);

        primaryStage.setScene(trilhos);// Define a cena inicial como a tela de Trilhos

      });// Fim do botao (partindo cima juntos)

      // Define a imagem diretamente como o conteudo do botao
      Button buttonPartindoCimaBaixo = new Button();
      Image imagemBotaoPartindoCimaBaixo = new Image("imagens/partindoCimaBaixo.png");// Carrega a imagem atraves do
                                                                                      // endereco
      ImageView imageViewBotaoPartindoCimaBaixo = new ImageView(imagemBotaoPartindoCimaBaixo);
      buttonPartindoCimaBaixo.setGraphic(imageViewBotaoPartindoCimaBaixo);
      layoutTelaDeEscolha.getChildren().add(buttonPartindoCimaBaixo);

      imageViewBotaoPartindoCimaBaixo.setFitHeight(167.0);// Define a altura da imagem
      imageViewBotaoPartindoCimaBaixo.setFitWidth(167.0);// Define a largura da imagem

      AnchorPane.setTopAnchor(buttonPartindoCimaBaixo, 500.0);// Posicao do botao partindo de cima
      AnchorPane.setLeftAnchor(buttonPartindoCimaBaixo, 83.3);// Posicao do botao partindo da esquerda

// __________________________________ UM TREM POR CIMA E OUTRO POR BAIXO ____________________________________________________
      buttonPartindoCimaBaixo.setOnAction(partindobuttonPartindoCimaBaixo -> {
        // Cria uma ImageView para exibir a imagem dos trilhos
        ImageView imageViewTrilhos = new ImageView(imagemTrilhos);

        // Cria um AnchorPane para conter os elementos da cena dos trilhos e adiciona a
        // imagem dos trilhos a ele
        layoutTrilhos[0] = new AnchorPane();
        layoutTrilhos[0].getChildren().add(imageViewTrilhos);

        // Cria um botao de volta e define uma imagem nele
        Button buttonTelaInicial = elementosGraficos.ButtonTelaInicial();
        layoutTrilhos[0].getChildren().add(buttonTelaInicial);

        // Define e configura um label para mostrar a velocidade do trem porco
        Label labelVelocidadePorco = elementosGraficos.fundoVelocimetroPorco();
        layoutTrilhos[0].getChildren().add(labelVelocidadePorco);// Adiciona o label ao layout

        // Cria e configura a imagem do trem porco
        Image imagemTremPorco = new Image("imagens/porcoTrem.png");// Carrega a imagem do trem do porco
        ImageView imageViewTremPorco = new ImageView(imagemTremPorco);
        layoutTrilhos[0].getChildren().add(imageViewTremPorco);

        // Configuracoes do slider de velocidade do trem porco
        elementosGraficos.configurarSliderPorco();

        //instancia o objeto tremPorco da classe com Thread TremPorco
        TremPorco tremPorco = new TremPorco(imageViewTremPorco, sliderVelocidadePorco, 306, 0, false);
        tremPorco.start();

        // Posiciona o slider sobre o velocimetro
        AnchorPane.setBottomAnchor(sliderVelocidadePorco, 50.0);
        AnchorPane.setLeftAnchor(sliderVelocidadePorco, 19.0);
        layoutTrilhos[0].getChildren().add(sliderVelocidadePorco);// Adiciona o slider ao layout do trilho

        // Cria e configura a imagem do trem Galinha
        // ImageView imageViewTremGalinha = elementosGraficos.configurarImageViewGalinha();
        Image imagemTremGalinha = new Image("imagens/galinhaTrem.png");// Carrega a imagem do trem do Galinha
        ImageView imageViewTremGalinha = new ImageView(imagemTremGalinha);
        layoutTrilhos[0].getChildren().add(imageViewTremGalinha);

        // Define e configura um label para mostrar a velocidade do trem galinha
        Label labelVelocidadeGalinha = elementosGraficos.fundoVelocimetroGalinha();
        layoutTrilhos[0].getChildren().add(labelVelocidadeGalinha);// Adiciona o label ao layout

        // Configuracoes do slider de velocidade do trem galinha
        elementosGraficos.configurarSliderGalinha();

        //instancia o objeto tremGalinha da classe com Thread TremGalinha
        TremGalinha tremGalinha = new TremGalinha(imageViewTremGalinha, sliderVelocidadeGalinha, 470, 710, true);
        tremGalinha.start();

        // Posiciona o slider sobre o velocimetro
        AnchorPane.setBottomAnchor(sliderVelocidadeGalinha, 50.0);
        AnchorPane.setLeftAnchor(sliderVelocidadeGalinha, 517.0);
        layoutTrilhos[0].getChildren().add(sliderVelocidadeGalinha);// Adiciona o slider ao layout

        // Define a acao a ser executada quando o botao de voltar eh clicado
        buttonTelaInicial.setOnAction(event -> {
          tremPorco.resetarTremPorco(false);
          tremGalinha.resetarTremGalinha(true);
          primaryStage.setScene(telaDeEscolha);// Define a cena de tela de escolha como a principal
        });// Fim do botao

        Scene trilhos = new Scene(layoutTrilhos[0]);// Cria uma cena com o layout que contem o ImageView
        imageViewTrilhos.setFitHeight(750);// Define a altura da imagem
        imageViewTrilhos.setFitWidth(750);// Define a largura da imagem

        Image imagemBotaoResetar = new Image("imagens/botaoResetar.png");// Define a imagem para o botao de resetar

        // Cria um botao e define a imagem como seu grafico
        Button buttonResetar = new Button();
        buttonResetar.setGraphic(new ImageView(imagemBotaoResetar));
        layoutTrilhos[0].getChildren().add(buttonResetar);

        // Adiciona o botao ao layout e define suas ancoras de posicionamento
        AnchorPane.setTopAnchor(buttonResetar, 8.3);
        AnchorPane.setLeftAnchor(buttonResetar, 525.0);

        // Define a acao a ser executada quando o botao de resetar eh clicado
        buttonResetar.setOnAction(event -> {
          tremPorco.resetarTremPorco(false);
          tremGalinha.resetarTremGalinha(true);
        });// Fim do botao Resetar

        ToggleGroup grupoSolucoes = new ToggleGroup();

        // Criação e configuração dos novos RadioButtons
        RadioButton radioButtonVariavelDeTravamento = elementosGraficos.radioButtonVariavelDeTravamento(grupoSolucoes);
        RadioButton radioButtonEstritaAlternancia = elementosGraficos.radioButtonEstritaAlternancia(grupoSolucoes);
        RadioButton radioButtonSolucaoDePeterson = elementosGraficos.radioButtonSolucaoDePeterson(grupoSolucoes);

        radioButtonVariavelDeTravamento.setOnAction(event -> {
          tremPorco.setSolucao("variavelDeTravamento");
          tremGalinha.setSolucao("variavelDeTravamento");
          tremPorco.resetarTremPorco(false);
          tremGalinha.resetarTremGalinha(true);
          
        });
        layoutTrilhos[0].getChildren().add(radioButtonVariavelDeTravamento);
        radioButtonVariavelDeTravamento.fire();
        
        radioButtonEstritaAlternancia.setOnAction(event ->{
          tremPorco.setSolucao("estritaAlternancia");
          tremGalinha.setSolucao("estritaAlternancia");
          tremPorco.resetarTremPorco(false);
          tremGalinha.resetarTremGalinha(true);
        });
        layoutTrilhos[0].getChildren().add(radioButtonEstritaAlternancia);
        
        radioButtonSolucaoDePeterson.setOnAction(event -> {
          tremPorco.setSolucao("solucaoDePeterson");
          tremGalinha.setSolucao("solucaoDePeterson");
          tremPorco.resetarTremPorco(false);
          tremGalinha.resetarTremGalinha(true);
        });
        layoutTrilhos[0].getChildren().add(radioButtonSolucaoDePeterson);

        primaryStage.setScene(trilhos);// Define a cena inicial como a tela de Trilhos
      });// Fim do botao (partindo direcoes opostas)

      // Define a imagem diretamente como o conteudo do botao
      Button buttonPartindoBaixoCima = new Button();
      Image imagemBotaoPartindoBaixoCima = new Image("imagens/partindoBaixoCima.png");// Carrega a imagem atraves do
                                                                                      // endereco
      ImageView imageViewBotaoPartindoBaixoCima = new ImageView(imagemBotaoPartindoBaixoCima);
      buttonPartindoBaixoCima.setGraphic(imageViewBotaoPartindoBaixoCima);
      layoutTelaDeEscolha.getChildren().add(buttonPartindoBaixoCima);

      imageViewBotaoPartindoBaixoCima.setFitHeight(167.0);// Define a altura da imagem
      imageViewBotaoPartindoBaixoCima.setFitWidth(167.0);// Define a largura da imagem

      AnchorPane.setTopAnchor(buttonPartindoBaixoCima, 500.0);// Posicao do botao partindo de cima
      AnchorPane.setLeftAnchor(buttonPartindoBaixoCima, 500.0);// Posicao do botao partindo da esquerda

// _______________________________UM TREM POR BAIXO E OUTRO POR CIMA______________________________________________________ 
      buttonPartindoBaixoCima.setOnAction(partindobuttonPartindoBaixoCima -> {
        // Cria uma ImageView para exibir a imagem dos trilhos
        ImageView imageViewTrilhos = new ImageView(imagemTrilhos);

        // Cria um AnchorPane para conter os elementos da cena dos trilhos e adiciona a
        // imagem dos trilhos a ele
        layoutTrilhos[0] = new AnchorPane();
        layoutTrilhos[0].getChildren().add(imageViewTrilhos);

        // Cria um botao de volta e define uma imagem nele
        Button buttonTelaInicial = elementosGraficos.ButtonTelaInicial();
        layoutTrilhos[0].getChildren().add(buttonTelaInicial);

        // Define e configura um label para mostrar a velocidade do trem porco
        Label labelVelocidadePorco = elementosGraficos.fundoVelocimetroPorco();
        layoutTrilhos[0].getChildren().add(labelVelocidadePorco);// Adiciona o label ao layout

        // Cria e configura a imagem do porco
        Image imagemTremPorco = new Image("imagens/porcoTrem.png");// Carrega a imagem do trem do porco
        ImageView imageViewTremPorco = new ImageView(imagemTremPorco);
        layoutTrilhos[0].getChildren().add(imageViewTremPorco);

        // Configuracoes do slider de velocidade do trem porco
        elementosGraficos.configurarSliderPorco();

        //instancia o objeto tremPorco da classe com Thread TremPorco
        TremPorco tremPorco = new TremPorco(imageViewTremPorco, sliderVelocidadePorco, 306, 710, true);
        tremPorco.start();

        // Posiciona o slider sobre o velocimetro
        AnchorPane.setBottomAnchor(sliderVelocidadePorco, 50.0);
        AnchorPane.setLeftAnchor(sliderVelocidadePorco, 19.0);
        layoutTrilhos[0].getChildren().add(sliderVelocidadePorco);// Adiciona o slider ao layout do trilho

        // Cria e configura a imagem do trem Galinha
        Image imagemTremGalinha = new Image("imagens/galinhaTrem.png");// Carrega a imagem do trem do Galinha
        ImageView imageViewTremGalinha = new ImageView(imagemTremGalinha);
        layoutTrilhos[0].getChildren().add(imageViewTremGalinha);

        // Define e configura um label para mostrar a velocidade do trem galinha
        Label labelVelocidadeGalinha = elementosGraficos.fundoVelocimetroGalinha();
        layoutTrilhos[0].getChildren().add(labelVelocidadeGalinha);// Adiciona o label ao layout

        // Configuracoes do slider de velocidade do trem galinha
        elementosGraficos.configurarSliderGalinha();

        //instancia o objeto tremGalinha da classe com Thread TremGalinha
        TremGalinha tremGalinha = new TremGalinha(imageViewTremGalinha, sliderVelocidadeGalinha, 470, 0, false);
        tremGalinha.start();

        // Posiciona o slider sobre o velocimetro
        AnchorPane.setBottomAnchor(sliderVelocidadeGalinha, 50.0);
        AnchorPane.setLeftAnchor(sliderVelocidadeGalinha, 517.0);
        layoutTrilhos[0].getChildren().add(sliderVelocidadeGalinha);// Adiciona o slider ao layout

        Image imagemBotaoResetar = new Image("imagens/botaoResetar.png");// Define a imagem para o botao de resetar

        // Cria um botao e define a imagem como seu grafico
        Button buttonResetar = new Button();
        buttonResetar.setGraphic(new ImageView(imagemBotaoResetar));
        layoutTrilhos[0].getChildren().add(buttonResetar);

        // Adiciona o botao ao layout e define suas ancoras de posicionamento
        AnchorPane.setTopAnchor(buttonResetar, 8.3);
        AnchorPane.setLeftAnchor(buttonResetar, 525.0);

        // Define a acao a ser executada quando o botao de resetar eh clicado
        buttonResetar.setOnAction(event -> {
          tremPorco.resetarTremPorco(true);
          tremGalinha.resetarTremGalinha(false);
        });// Fim do botao resetar

        ToggleGroup grupoSolucoes = new ToggleGroup();

        // Criação e configuração dos novos RadioButtons
        RadioButton radioButtonVariavelDeTravamento = elementosGraficos.radioButtonVariavelDeTravamento(grupoSolucoes);
        RadioButton radioButtonEstritaAlternancia = elementosGraficos.radioButtonEstritaAlternancia(grupoSolucoes);
        RadioButton radioButtonSolucaoDePeterson = elementosGraficos.radioButtonSolucaoDePeterson(grupoSolucoes);

        radioButtonVariavelDeTravamento.setOnAction(event -> {
          tremPorco.setSolucao("variavelDeTravamento");
          tremGalinha.setSolucao("variavelDeTravamento");
          tremPorco.resetarTremPorco(true);
          tremGalinha.resetarTremGalinha(false);
        });
        layoutTrilhos[0].getChildren().add(radioButtonVariavelDeTravamento);
        radioButtonVariavelDeTravamento.fire();
        
        radioButtonEstritaAlternancia.setOnAction(event ->{
          tremPorco.setSolucao("estritaAlternancia");
          tremGalinha.setSolucao("estritaAlternancia");
          tremPorco.resetarTremPorco(true);
          tremGalinha.resetarTremGalinha(false);
        });
        layoutTrilhos[0].getChildren().add(radioButtonEstritaAlternancia);
        
        radioButtonSolucaoDePeterson.setOnAction(event -> {
          tremPorco.setSolucao("solucaoDePeterson");
          tremGalinha.setSolucao("solucaoDePeterson");
          tremPorco.resetarTremPorco(true);
          tremGalinha.resetarTremGalinha(false);
        });
        layoutTrilhos[0].getChildren().add(radioButtonSolucaoDePeterson);

        // Define a acao a ser executada quando o botao de voltar eh clicado
        buttonTelaInicial.setOnAction(event -> {
          tremPorco.pararTremPorco();
          tremPorco.resetarTremPorco(true);
          tremGalinha.pararTremGalinha();
          tremGalinha.resetarTremGalinha(false);
          primaryStage.setScene(telaDeEscolha);// Define a cena de tela de escolha como a principal
        });// Fim do botao tela inicial

        Scene trilhos = new Scene(layoutTrilhos[0]);// Cria uma cena com o layout que contem o ImageView
        imageViewTrilhos.setFitHeight(750);// Define a altura da imagem
        imageViewTrilhos.setFitWidth(750);// Define a largura da imagem

        primaryStage.setScene(trilhos);// Define a cena inicial como a tela de Trilhos
      });// Fim do botao partindo direcoes opostas (INVERSO)

    });// Fim da tela de escolha de Direcao
    primaryStage.setScene(telaInicial);// cena definida na janela principal
    primaryStage.setTitle("Simulador de Trens");// titulo do Programa
    primaryStage.show();// Exibe a janela
    primaryStage.setResizable(false);// impede o usuario de redimensionar a janela
    primaryStage.sizeToScene();
    primaryStage.setOnCloseRequest(e -> {
      System.exit(0);
    });
  }// Fim do metodo Start
}