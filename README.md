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

### 📄 2. Leitura dos dados com `LeitorArquivo`

Os dados dos veículos são lidos de um arquivo chamado `rodizio_de_veiculos.txt`, onde cada linha contém:

```text
PLACA;DIA_DA_SEMANA;HORÁRIO
```

Exemplo:

```
ABC-1234;Segunda-feira;07:00-10:00
DEF-5678;Terça-feira;17:00-20:00
```

O método `carregarVeiculos` filtra os dados com base em:

- Dia da semana (**ex: Segunda-feira**), ou
- Final da placa (**ex: final 4**).

Se a linha do arquivo corresponde ao filtro escolhido, um novo nó `No` é criado e **inserido na lista circular**.

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

### 🧹 6. Liberação de memória

Ao final, a lista é "desfeita" para liberar os nós:

```java
No temp = inicio;
do {
    No proximo = temp.proximo;
    temp.proximo = null; // Remove ligação
    temp = proximo;
} while (temp != inicio);

inicio = null;
atual = null;
```

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

---

## 🎯 Por que usar **lista circular**?

A lista circular foi escolhida porque:

- Permite **navegação contínua** entre os veículos, sem ponto de parada.
- Evita a necessidade de reiniciar o ponteiro manualmente.
- Simula bem situações cíclicas, como o rodízio semanal de veículos.
- Ajuda a entender melhor o conceito de listas encadeadas com comportamento dinâmico.
