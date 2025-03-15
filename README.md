# 🚆💡 Projetos JavaFX - Programação Concorrente

Bem-vindo ao repositório de **Projetos JavaFX aplicados à Programação Concorrente**! Aqui você encontrará simulações de problemas clássicos da computação concorrente, implementadas em **Java** com **JavaFX**. Estes projetos podem ser úteis para entender conceitos como **zonas críticas, exclusão mútua, sincronização de threads e escalonamento**.  

Fique à vontade para explorar o código e rodar as simulações! 😊  

## 📌 Projetos Disponíveis

### 🚄 1. Trem Com Colisão  
**Descrição:** Simulação de dois trens que percorrem trilhos compartilhados, onde há **duas zonas críticas**. Para evitar colisões, os trens precisam de um mecanismo de controle de acesso a essas áreas.  

**Funcionalidades:**  
✅ Ajustar a **velocidade** dos trens  
✅ Escolher **a direção inicial** de cada trem (4 opções)  
✅ **Resetar** a simulação e iniciar novamente  

---

### 🚦 2. Trem Sem Colisão  
**Descrição:** Versão aprimorada do primeiro projeto, onde são implementadas estratégias de **exclusão mútua** para evitar colisões nas zonas críticas. São usadas as seguintes técnicas:  
- **Variável de Travamento**  
- **Estrita Alternância**  
- **Solução de Peterson**  

**Funcionalidades:**  
✅ Ajustar a **velocidade** dos trens  
✅ Escolher **a direção inicial** de cada trem (4 opções)  
✅ **Resetar** a simulação e iniciar novamente  

---

### 💈 3. Barbeiro Dorminhoco  
**Descrição:** Implementação do problema clássico do **Barbeiro Dorminhoco**, que envolve um barbeiro e cinco clientes. O problema modela a sincronização entre threads de clientes e o barbeiro, resolvendo questões como espera e atendimento concorrente.  

**Funcionalidades:**  
✅ Ajustar a **velocidade do barbeiro cortando o cabelo**  
✅ Ajustar a **frequência de chegada dos clientes**  
✅ **Resetar** a simulação e iniciar novamente  

---

### 🚗 4. Problema do Trânsito Automatizado  
**Descrição:** Simulação de uma cidade com várias quadras e **8 carros** que seguem um percurso predefinido sem colisões. O projeto usa **threads** para modelar o movimento dos carros, garantindo que eles respeitem o fluxo e evitem acidentes.  

**Funcionalidades:**  
✅ Ajustar a **velocidade dos carros**  
✅ Exibir a **rota de cada carro**  
✅ **Resetar e reiniciar** a simulação  

---

## 🔧 Como Rodar os Projetos?  
### Pré-requisitos  
- **Java JDK** (versão 8 ou superior) instalado  
- **JavaFX** configurado no seu projeto (para Java 8, já vem embutido)

### Executando  
1. Clone o repositório:  
   ```sh
   git clone https://github.com/Rodrigueixon/Projetos-JavaFX.git
2. Abra o projeto no VS Code ou IntelliJ
3. Compile e execute o código

## ⭐ Gostou do Projeto?  
Se você gostou deste projeto, por favor, considere dar uma estrela ⭐ no repositório para ajudar outros a encontrá-lo! Seu apoio é muito importante e motivador.  

Obrigado e bom código! 🚀
