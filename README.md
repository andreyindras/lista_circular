# 🚗 Sistema de Gerenciamento de Rodízio de Placas

Este projeto em Java simula um **sistema de controle de rodízio de veículos** com base no **dia da semana ou final da placa**, utilizando como estrutura de dados uma **lista encadeada circular**.

O sistema lê dados de um arquivo `.txt`, filtra conforme a escolha do usuário e permite a **navegação infinita entre os veículos filtrados**, graças à circularidade da lista.

---

## 🧠 Lógica do Funcionamento

### 1. **Modelagem dos dados com `No`**

Cada veículo é representado por um **objeto da classe `No`**, que atua como um **nó da lista circular**. Cada nó armazena:

- `placa`: Placa do veículo (ex: ABC-1234).
- `diaSemana`: Dia em que esse veículo está restrito.
- `horario`: Horário da restrição.
- `proximo`: Referência ao **próximo nó da lista**.

💡 **Importante**: Como se trata de uma lista circular, o último nó aponta de volta para o primeiro nó, **fechando o ciclo**.

---

Claro! Aqui está a seção **mesclada** e melhor explicada sobre a **leitura de dados e montagem da lista** com base no arquivo `rodizio_de_veiculos.txt`, já dentro do contexto da lista circular:

---

### 📄 2. Leitura dos Dados e Montagem da Lista Circular

Antes de montar a lista, o sistema realiza a leitura dos dados de um arquivo chamado `rodizio_de_veiculos.txt`. Cada linha desse arquivo representa um veículo e contém as informações separadas por ponto e vírgula `;`, no seguinte formato:

```text
PLACA;DIA_DA_SEMANA;HORÁRIO
```

#### 🧾 Exemplo de linha:
```
ABC-1234;Segunda-feira;07:00-10:00
```

O sistema percorre o arquivo linha por linha, realizando o **split da string** para extrair as partes:

```java
String[] partes = linha.split(";");
String placa = partes[0];
String diaSemana = partes[1];
String horario = partes[2];
```

Esses dados são então **filtrados de acordo com o critério definido pelo usuário**, que pode escolher:

- Um **dia da semana** (ex: “Terça-feira”), ou  
- Um **final de placa** (ex: veículos com placa terminando em "4").

Se a linha lida do arquivo corresponder ao filtro selecionado, o sistema cria um **novo nó (`No`) com essas informações** e o insere na **lista circular**.

---

#### 🔄 Como a lista é montada?

A cada veículo válido (isto é, que passou pelo filtro), o sistema:

1. Cria um novo objeto `No` com os dados do veículo.
2. Insere esse nó no final da lista circular.
3. Garante que o último nó da lista aponte de volta para o primeiro, mantendo a **circularidade**.

Esse processo é feito pela classe `ListaCircular`, que cuida da lógica de encadeamento dos nós.

💡 Esse encadeamento circular é importante para permitir a navegação contínua entre os veículos, sem interrupção.

---

### 🏗️ 3. Estrutura com `ListaCircular`

A lista circular funciona de forma que **o último elemento sempre aponta para o primeiro**, formando um ciclo. Essa estrutura é útil quando se deseja **navegar continuamente** entre os elementos, como neste sistema.

---

#### 🧱 Composição da estrutura

- `inicio`: Nó inicial da lista (primeiro veículo inserido).
- `atual`: Referência ao veículo sendo exibido no momento.
  
A lista permite:

- Inserção de novos nós no fim da lista.
- Exibição completa dos veículos filtrados.
- Navegação interativa, onde o usuário pode ir de um veículo ao próximo, indefinidamente (graças à circularidade).

---

### ➕ Como funciona a inserção?

A cada novo veículo que corresponde ao filtro, ele é inserido **no final da lista**, e o último nó aponta novamente para o primeiro:

```java
public void inserir(String placa, String diaSemana, String horario) {
    No novo = new No(placa, diaSemana, horario);

    if (inicio == null) {
        inicio = novo;
        inicio.proximo = inicio; // Lista aponta para si mesma (1 elemento)
        atual = inicio;
    } else {
        No temp = inicio;
        while (temp.proximo != inicio) {
            temp = temp.proximo;
        }
        temp.proximo = novo;
        novo.proximo = inicio; // Último aponta para o primeiro
    }
}
```

---

### 🔁 O que é uma **lista circular**?

Uma lista circular é uma **variação da lista encadeada**, onde **o último elemento não aponta para `null`**, mas sim para o **primeiro elemento**, formando um **laço contínuo**.

#### 🔄 Exemplo com 3 elementos:

```text
[Veículo 1] → [Veículo 2] → [Veículo 3]
      ↑                             ↓
      ← ← ← ← ← ← ← ← ← ← ← ← ← ← ←
```

Assim, é possível navegar infinitamente de um veículo ao próximo, sem nunca sair da lista.

---

### 🔍 4. Exibição da lista completa

O método `exibirLista` percorre a lista circular e exibe todos os veículos, **sem repetir**:

```java
No temp = inicio;
do {
    System.out.println("Placa: " + temp.placa);
    // ...
    temp = temp.proximo;
} while (temp != inicio);
```

---

### 🔂 5. Navegação interativa com `navegarLista`

O usuário pode navegar interativamente pela lista, **visualizando um veículo por vez**, com a opção de seguir para o próximo:

```text
Placa: ABC-1234
Dia: Segunda-feira
Horário: 07:00-10:00

Opções:
1 - Próximo veículo
0 - Sair
```

Como a lista é circular, o sistema nunca “acaba”. Após o último veículo, o próximo será novamente o primeiro.

---

### 🧑‍💻 7. Controle com `Main`

A classe `Main` orquestra o sistema:

1. Solicita ao usuário o critério de filtro:
   - Por dia da semana.
   - Por final de placa.
2. Lê a entrada do usuário.
3. Carrega os dados do arquivo com o filtro.
4. Permite:
   - Exibir todos os veículos de uma vez.
   - Navegar um por um (modo interativo).
5. Libera a lista ao final.

---

## 🔄 Fluxo Lógico Resumido

```text
Arquivo .txt
   ↓
Leitura dos dados com base em um filtro
   ↓
Criação de nós com os dados válidos
   ↓
Inserção em uma lista circular
   ↓
Navegação contínua entre os veículos
   ↓
Liberação da lista
```
