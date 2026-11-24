# Board Game Engine

Game Engine genérica para jogos de tabuleiro, desenvolvida em Java como projeto da disciplina de **Linguagem de Programação Orientada a Objetos (LPOO)**.

O projeto tem como objetivo construir uma Engine capaz de interpretar arquivos de configuração JSON e, a partir deles, instanciar e executar diferentes jogos de tabuleiro, reutilizando os mesmos componentes e comportamentos sempre que possível.

## Equipe

- Djullyene da Silva Cordeiro
- Giulia Sousa Mendes

## Status do projeto

🚧 Em desenvolvimento — Checkpoint 2

| Etapa | Data | Status |
|---|---|---|
| Checkpoint 1 — Primeiro jogo | 14/10 | ✅ Concluído |
| Checkpoint 2 — Generalização da Engine | 18/11 | 🚧 Em andamento |
| Entrega final | 16/12 | ⏳ Não iniciada |

- **Checkpoint 1 — Primeiro jogo:** implementação de um jogo de tabuleiro funcional em Java, executado pelo terminal, com suas principais mecânicas e regras.
- **Checkpoint 2 — Generalização da Engine:** adaptação da implementação para executar pelo menos dois jogos diferentes a partir de arquivos JSON, compartilhando componentes.
- **Entrega final — Engine extensível:** conclusão da Engine, com configurações validadas e suporte a novos jogos que utilizem comportamentos já implementados, sem alterações no código-fonte.

## Índice

- [Visão geral](#visão-geral)
- [Como executar](#como-executar)
- [Como a Engine funciona](#como-a-engine-funciona)
- [Principais componentes](#principais-componentes)
- [Configurando um novo jogo](#configurando-um-novo-jogo)
- [Estrutura do projeto](#estrutura-do-projeto)
- [Histórico de desenvolvimento](#histórico-de-desenvolvimento)
- [Roadmap](#roadmap)
- [Diagrama de classes](#diagrama-de-classes)
- [Referências](#referências)

---

## Visão geral

O objetivo da Board Game Engine é permitir que diferentes jogos de tabuleiro sejam executados sem que seja necessário implementar um conjunto de classes específico para cada jogo.

A definição de cada partida estará em um arquivo de configuração `.json`, que descreve características como:

- Tipo e dimensões do tabuleiro;
- Quantidade de jogadores e seus símbolos;
- Tipos de peças e suas posições iniciais;
- Tipos de movimentos permitidos;
- Regras de movimentação e posicionamento;
- Regras de captura, quando aplicáveis;
- Funcionamento dos turnos;
- Condições de vitória;
- Condições de empate e encerramento.

A Engine interpreta essas informações e instancia os componentes necessários para executar a partida.

**Requisito fundamental:** um novo jogo que utilize comportamentos já suportados pela Engine deverá poder ser adicionado apenas por meio de um novo arquivo de configuração, sem necessidade de alterar o código-fonte.

Isso significa que os jogos compartilham componentes e comportamentos, diferenciando-se pelas configurações e pelas implementações de regras utilizadas.

---

## Como executar

A Engine será executada pelo terminal.

Ao iniciar o programa, será exibido um menu com os jogos encontrados na pasta `games/`. O usuário poderá selecionar qual partida deseja jogar ou encerrar o programa.

O menu deverá identificar automaticamente os arquivos `.json` disponíveis, sem exigir que os nomes dos jogos sejam cadastrados manualmente no código.

Após selecionar um jogo, a Engine carregará e validará sua configuração, criará os componentes necessários e iniciará a partida.

Ao terminar, o usuário poderá voltar ao menu para selecionar outro jogo.

*Os comandos de compilação e execução serão adicionados após a definição da estrutura final de dependências.*

---

## Como a Engine funciona

A arquitetura separa a configuração do jogo, a criação dos objetos e a execução da partida.

O fluxo geral previsto para a versão final é:

```text
       INÍCIO DO PROGRAMA
               |
               v
       Procurar arquivos JSON
           em games/
               |
               v
        Exibir menu de jogos
               |
               v
        Selecionar configuração
               |
               v
           GameLoader
               |
               v
           GameConfig
               |
               v
       Validar configuração
               |
               v
           GameFactory
               |
               v
      Criar componentes do jogo
               |
               v
          GameManager
               |
               v
       Executar partida
               |
               v
       Encerrar ou voltar
          ao menu
```

### 1. Seleção do jogo

O programa procura os arquivos `.json` existentes na pasta `games/` e apresenta as opções encontradas.

Dessa forma, novos jogos podem ser disponibilizados sem alterações no menu.

### 2. Leitura da configuração

O `GameLoader` é responsável por carregar o arquivo JSON e convertê-lo em um objeto `GameConfig`.

Essa etapa permite que a Engine trabalhe com os dados do jogo por meio de objetos Java, em vez de manipular diretamente o conteúdo textual do arquivo.

### 3. Validação

A configuração deve ser verificada antes da criação da partida.

Entre as verificações previstas estão:

- Presença dos campos obrigatórios;
- Valores válidos para as dimensões do tabuleiro;
- Quantidade e identificação dos jogadores;
- Existência dos tipos de tabuleiro, peças e movimentos solicitados;
- Reconhecimento das regras configuradas;
- Compatibilidade entre os componentes escolhidos;
- Validade das posições iniciais.

Caso a configuração seja inválida, a Engine deverá apresentar uma mensagem que identifique o problema.

### 4. Instanciação dos componentes

A `GameFactory` será responsável por criar os objetos necessários para a partida com base nas informações validadas.

Isso inclui o tabuleiro, os jogadores, as peças, os movimentos e as regras correspondentes.

A escolha dos componentes deverá respeitar a compatibilidade entre eles. Por exemplo, um jogo baseado na colocação de paredes deverá utilizar um tabuleiro e um tipo de movimento que suportem essa mecânica.

### 5. Execução da partida

O `GameManager` será responsável por coordenar a partida.

De maneira geral, o fluxo de uma jogada será:

1. Identificar o jogador da vez;
2. Receber uma ação pela interface;
3. Verificar se o movimento é válido;
4. Aplicar o movimento ao tabuleiro;
5. Processar os efeitos da jogada, como capturas ou pontuação;
6. Verificar as condições de vitória e encerramento;
7. Determinar o próximo jogador.

As regras configuradas determinam como cada etapa se comporta em cada jogo.

### 6. Encerramento

A partida termina quando a condição de encerramento configurada é satisfeita.

A Engine deverá identificar o resultado, apresentar as informações correspondentes e permitir que o usuário volte ao menu.

---

## Principais componentes

A Engine utiliza abstrações e polimorfismo para permitir que diferentes jogos compartilhem uma estrutura comum sem perder suas particularidades.

### Tabuleiro — Board

Representa a estrutura do tabuleiro e define as operações comuns aos seus tipos.

Exemplos:

- `GridBoard`: tabuleiro organizado em células;
- `WallsBoard`: tabuleiro que permite a colocação de paredes entre pontos.

Cada tipo de tabuleiro deverá trabalhar com os movimentos compatíveis com sua estrutura.

### Posição — Position

Representa a localização de uma célula no tabuleiro.

Possui coordenadas de linha (`row`) e coluna (`column`) e será uma classe imutável.

A posição não representa uma ação: ela identifica apenas um lugar no tabuleiro.

### Peça — Piece

Representa uma peça que pode ser movimentada durante a partida.

A classe será abstrata, permitindo a criação de subclasses para representar diferentes tipos de peças.

Cada peça possui:

- Símbolo;
- Jogador proprietário.

O tabuleiro será responsável por armazenar as peças e suas posições.

Em jogos com evolução de peças, como Damas, uma peça poderá ser substituída por outra de tipo diferente. Por exemplo, uma `NormalPiece` poderá ser substituída por uma `KingPiece`, preservando sua posição e seu jogador proprietário.

### Movimento — Movement

Representa uma ação realizada no tabuleiro.

Será uma classe abstrata, com implementações específicas para diferentes tipos de ação.

Entre os tipos previstos estão:

- `PlacementMovement`: posicionamento de uma peça;
- `RelocationMovement`: deslocamento de uma peça entre duas posições;
- `WallMovement`: colocação de uma parede em um lado de uma célula.

O polimorfismo permitirá que cada tipo de movimento forneça as informações necessárias para sua operação.

O movimento não será responsável por validar as regras do jogo. Essa responsabilidade pertence às regras de movimentação.

### Regras

As regras definem os comportamentos específicos de cada jogo.

A arquitetura prevê interfaces para separar responsabilidades:

| Componente | Responsabilidade |
|---|---|
| `MoveRule` | Validar e aplicar movimentos |
| `TurnRule` | Determinar o próximo jogador |
| `CaptureRule` | Definir e processar capturas |
| `WinCondition` | Identificar o vencedor |
| `EndCondition` | Determinar se a partida terminou |

As implementações dessas regras poderão ser reutilizadas por diferentes jogos.

### Configuração — GameConfig

Representa os dados carregados do JSON.

Sua estrutura deverá permitir descrever os componentes e comportamentos necessários para montar diferentes jogos sem depender de classes específicas de cada partida.

### Criação do jogo — GameFactory

Responsável por transformar a configuração validada em objetos concretos da Engine.

A fábrica deverá selecionar os tipos de tabuleiro, movimentos e regras compatíveis com o jogo.

---

## Configurando um novo jogo

Os jogos são descritos por arquivos JSON armazenados no diretório `games/`.

Para adicionar um jogo, a configuração deverá utilizar os tipos de tabuleiro, movimentos e regras já suportados pela Engine.

### 1. Criar o arquivo JSON

Crie um arquivo com o nome do jogo no diretório `games/`.

Exemplo: `jogo_da_velha.json`.

O arquivo será identificado automaticamente pelo menu quando o programa for iniciado.

### 2. Definir as características do jogo

A configuração atual do Jogo da Velha utiliza o seguinte formato:

```json
{
  "game": "Jogo da Velha",
  "board": {
    "rows": 3,
    "columns": 3
  },
  "players": {
    "count": 2,
    "symbols": ["X", "O"]
  },
  "rules": {
    "placement": "EMPTY_POSITION",
    "turn": "ALTERNATE",
    "winCondition": {
      "type": "ALIGNMENT",
      "quantity": 3
    },
    "endCondition": "WIN_OR_BOARD_FULL"
  }
}
```

### 3. Entendendo os campos

As tabelas abaixo documentam os campos e valores previstos para a configuração dos jogos.

Os valores marcados com **(WIP)** são planejados, mas ainda não estão disponíveis na implementação. A marcação poderá ser removida quando o respectivo recurso estiver implementado.

#### Identificação e tabuleiro

| Campo | Descrição | Valores / opções |
|---|---|---|
| `game` | Nome do jogo. | Texto livre. |
| `board` | Configuração do tabuleiro. | Objeto de configuração. |
| `board.type` | Tipo de tabuleiro. | `GRID` (WIP), `WALLS` (WIP). |
| `board.rows` | Quantidade de linhas. | Número inteiro. |
| `board.columns` | Quantidade de colunas. | Número inteiro. |

#### Jogadores e peças

| Campo | Descrição | Valores / opções |
|---|---|---|
| `players` | Configuração dos jogadores. | Objeto de configuração. |
| `players.count` | Quantidade de jogadores. | Número inteiro. |
| `players.symbols` | Símbolos utilizados pelos jogadores. | Lista de textos. |
| `pieces` | Definição das peças do jogo. | Lista de peças (WIP). |
| `pieces.type` | Tipo de peça. | `NORMAL` (WIP), `KING` (WIP). |
| `pieces.symbol` | Símbolo da peça. | Texto. |
| `pieces.player` | Jogador proprietário da peça. | Identificador do jogador (WIP). |
| `pieces.initialPositions` | Posições iniciais das peças. | Lista de posições (WIP). |

#### Movimentos

| Campo | Descrição | Valores / opções |
|---|---|---|
| `movement` | Configuração dos movimentos disponíveis. | Lista de movimentos (WIP). |
| `movement.type` | Tipo de ação realizada no tabuleiro. | `PLACEMENT` (WIP), `RELOCATION` (WIP), `WALL` (WIP), `COLUMN_DROP` (WIP). |

- `PLACEMENT`: posicionamento de uma peça em uma célula.
- `RELOCATION`: deslocamento de uma peça entre duas posições.
- `WALL`: colocação de uma parede em um lado de uma célula.
- `COLUMN_DROP`: escolha de uma coluna para posicionar uma peça na posição mais baixa disponível, como no Connect Four.

#### Regras

| Campo | Descrição | Valores / opções |
|---|---|---|
| `rules` | Conjunto de regras do jogo. | Objeto de configuração. |
| `rules.placement` | Regra de posicionamento. | `EMPTY_POSITION`. |
| `rules.move` | Regra de movimentação. | Tipos de validação de movimento (WIP). |
| `rules.capture` | Regra de captura. | Tipos de captura (WIP). |
| `rules.turn` | Regra de alternância dos turnos. | `ALTERNATE`, `EXTRA_TURN_ON_SCORE`. |
| `rules.winCondition` | Condição de vitória. | Objeto de configuração. |
| `rules.winCondition.type` | Critério utilizado para determinar o vencedor. | `ALIGNMENT`, `MOST_POINTS`. |
| `rules.endCondition` | Condição de encerramento da partida. | `WIN_OR_BOARD_FULL`, `BOARD_FULL`. |

Os valores de regras representam os comportamentos previstos para os jogos já considerados no projeto:

- `EMPTY_POSITION`: permite posicionar uma peça somente em uma célula vazia.
- `ALTERNATE`: alterna os jogadores a cada jogada.
- `EXTRA_TURN_ON_SCORE`: concede outra jogada ao jogador que pontuar.
- `ALIGNMENT`: verifica se um jogador alcançou um alinhamento da quantidade configurada.
- `MOST_POINTS`: determina o vencedor pela maior pontuação.
- `WIN_OR_BOARD_FULL`: encerra a partida quando alguém vence ou quando o tabuleiro fica cheio.
- `BOARD_FULL`: encerra a partida quando o tabuleiro fica cheio.

Os tipos de captura e as regras específicas de movimentação ainda serão definidos conforme a implementação dos jogos que os utilizam.

### 4. Configurar as mecânicas

A configuração deve representar as mecânicas do jogo.

Por exemplo, no Jogo da Velha:

- O tabuleiro é uma grade;
- A jogada consiste em posicionar uma peça em uma célula vazia;
- Os jogadores alternam seus turnos;
- A vitória ocorre quando um jogador alinha três peças;
- A partida termina quando alguém vence ou quando o tabuleiro fica cheio.

No Liga dos Pontos:

- O tabuleiro permite a colocação de paredes;
- A jogada identifica uma célula e um dos seus lados;
- O jogador recebe outra jogada quando fecha uma caixinha;
- A vitória é determinada pela maior pontuação;
- A partida termina quando o tabuleiro está cheio.

### 5. Verificar a compatibilidade

Não basta que os nomes das regras estejam presentes no JSON.

Os componentes escolhidos precisam ser compatíveis entre si.

Por exemplo, uma configuração que utiliza `WallMovement` deverá instanciar um tabuleiro capaz de armazenar e processar paredes.

### 6. Executar o jogo

Salve o arquivo JSON na pasta `games/` e execute a Engine.

O jogo deverá aparecer automaticamente no menu, desde que sua configuração seja válida e utilize recursos suportados.

> **Importante:** o exemplo do Jogo da Velha representa o formato atualmente utilizado. O formato completo será atualizado para incluir os dados necessários aos demais jogos, como tipos de tabuleiro, peças, movimentos e posições iniciais.

---

## Estrutura do projeto

### Estrutura inicial — Checkpoint 1

Estrutura utilizada na primeira implementação, antes da separação em pacotes:

```text
src/
├── Board.java
├── GameManager.java
├── Main.java
├── Movement.java
├── Player.java
├── Rules.java
└── UiManager.java
```

**Compilação — Checkpoint 1:**

```bash
javac -d bin src/*.java
java -cp bin Main
```

### Estrutura atual — Checkpoint 2

```text
giuliasme-boardgame_engine/
├── README.md
├── docs/                  ← diagrama e planejamento
├── games/                 ← jogo_da_velha.json e liga_pontos.json
└── src/
    ├── Main.java
    ├── config/            ← GameConfig e GameLoader
    ├── engine/
    │   ├── board/         ← Board, GridBoard e WallsBoard
    │   ├── core/          ← GameManager, Movement e Player
    │   ├── exceptions/    ← exceções da Engine
    │   └── rules/         ← regras de movimento, turno, vitória e fim
    └── ui/                ← UiManager
```

**Compilação — Checkpoint 2 (PowerShell):**

```powershell
javac -d bin -cp lib\gson-2.11.0.jar (Get-ChildItem -Path src -Recurse -Filter *.java).FullName
java -cp "bin;lib\gson-2.11.0.jar" Main
```

### Estrutura planejada — Entrega final

```text
giuliasme-boardgame_engine/
├── README.md
├── docs/                  ← diagrama e planejamento
├── games/                 ← JSON dos quatro jogos previstos
└── src/
    ├── Main.java
    ├── config/            ← GameConfig, GameLoader, GameFactory e ConfigValidator
    ├── engine/
    │   ├── board/         ← Board, GridBoard e WallsBoard
    │   ├── core/          ← GameManager, GameState, Player e Position
    │   ├── piece/         ← abstração Piece e tipos específicos
    │   ├── movement/      ← Movement e tipos específicos de ação
    │   ├── rules/
    │   │   ├── move/      ← regras de movimentação
    │   │   ├── turn/      ← regras de turno
    │   │   ├── capture/   ← regras de captura
    │   │   ├── win/       ← condições de vitória
    │   │   └── end/       ← condições de encerramento
    │   └── exceptions/    ← exceções da Engine
    └── ui/                ← UiManager
```

A estrutura planejada é uma referência e poderá ser ajustada conforme as responsabilidades das classes forem definidas durante a implementação.

---

## Histórico de desenvolvimento

### Checkpoint 1 — Primeiro jogo

**Data:** 14/10

**Jogo implementado:** Liga dos Pontos.

O primeiro checkpoint teve como objetivo desenvolver um jogo funcional em Java, executado pelo terminal, utilizando os conceitos de orientação a objetos estudados na disciplina.

Funcionalidades desenvolvidas:

- [x] Tabuleiro;
- [x] Jogadores;
- [x] Controle de turnos;
- [x] Marcações e paredes;
- [x] Validação de jogadas;
- [x] Condição de vitória;
- [x] Condição de encerramento;
- [x] Execução pelo terminal.

A implementação inicial utilizava classes diretamente relacionadas à execução do Liga dos Pontos.

### Checkpoint 2 — Generalização da Engine

**Data:** 18/11

O segundo checkpoint tem como objetivo evoluir a implementação inicial para uma Engine capaz de executar pelo menos dois jogos diferentes a partir de arquivos JSON, compartilhando componentes.

Jogos utilizados como referência:

- Liga dos Pontos;
- Jogo da Velha.

Principais mudanças planejadas e iniciadas:

- Separação entre tabuleiro e regras;
- Criação de interfaces para diferentes comportamentos;
- Generalização do controle de turnos;
- Configuração dos jogos por JSON;
- Criação de componentes reutilizáveis;
- Uso de polimorfismo para permitir diferentes implementações.

A arquitetura está sendo reorganizada para que as particularidades de cada jogo sejam representadas por componentes específicos, em vez de ficarem concentradas no `GameManager`.

### Entrega final — Engine completa

A etapa final tem como objetivo concluir uma Engine extensível, capaz de interpretar novas configurações que utilizem comportamentos já suportados, sem alteração do código-fonte.

Entre os objetivos estão:

- [ ] Configuração completa dos jogos por JSON;
- [ ] Validação das configurações;
- [ ] Instanciação dos componentes a partir da configuração;
- [ ] Suporte a diferentes tipos de tabuleiro e movimentos;
- [ ] Suporte a regras de captura;
- [ ] Suporte a condições configuráveis de vitória e encerramento;
- [ ] Menu dinâmico para seleção dos jogos;
- [ ] Interface genérica pelo terminal;
- [ ] Mensagens de erro amigáveis;
- [ ] Testes com diferentes configurações;
- [ ] Demonstração de extensibilidade com um jogo surpresa.

---

## Roadmap

Jogos previstos para orientar a evolução da Engine:

- Liga dos Pontos;
- Jogo da Velha;
- Connect Four;
- Damas.

A implementação desses jogos deverá aproveitar os componentes existentes sempre que suas mecânicas forem compatíveis.

O objetivo não é criar uma implementação específica para cada jogo, mas ampliar o conjunto de comportamentos suportados pela Engine.

---

## Diagrama de classes

O diagrama de classes será atualizado conforme a arquitetura da Engine for consolidada.

Arquivo previsto: `docs/diagrama/`.

---

## Referências

- [Board Game Engine — mnbroatch](https://github.com/mnbroatch/board-game-engine)
- [Tabletop Games Framework (TAG)](https://tabletopgames.ai/)
- [boardgame.io](https://github.com/boardgameio/boardgame.io)