# Board Game Engine

Projeto da disciplina LPOO — implementação de uma Game Engine genérica para jogos de tabuleiro, capaz de carregar a descrição de um jogo a partir de um arquivo de configuração (JSON) e, a partir dela, construir e executar a partida.

## Equipe

- Djullyene da Silva Cordeiro
- Giulia Sousa Mendes

## Status do projeto

🚧 Em desenvolvimento — Checkpoint 1

| Etapa | Data | Status |
|---|---|---|
| Checkpoint 1 — Primeiro jogo | 14/10 | 🚧 Em andamento |
| Checkpoint 2 — Da implementação para a Engine | 16/11 | ⏳ Não iniciado |
| Apresentação / Entrega final | 16/12 | ⏳ Não iniciado |

**Jogo do Checkpoint 1:** liga pontos.

## Visão geral

O objetivo final do projeto é uma Game Engine que **não conhece nenhum jogo específico em seu código-fonte**. Toda a definição de um jogo (tabuleiro, regras de movimento, condições de vitória/empate) vive em arquivos de configuração `.json`, e a Engine interpreta esses arquivos para montar e rodar a partida. A ideia é que os diferentes jogos suportados compartilhem os mesmos componentes da Engine, diferenciando-se principalmente pela configuração, pelos objetos instanciados e pelo uso de polimorfismo — não por código duplicado.

No Checkpoint 1, o foco é ter uma primeira versão **funcional e concreta** de um único jogo, rodando via terminal, já com uma modelagem orientada a objetos pensada para facilitar a generalização nas etapas seguintes.

## Como executar

> ⏳ Instruções serão adicionadas assim que a primeira versão do jogo estiver funcional.

```bash
# Somente nessa fase inicial:
javac -d bin src/**/*.java
java -cp bin Main
```

## Estrutura inicial (checkpoint 1)

```
src/
├── Board.java
├── GameManager.java
├── Main.java
├── Player.java
├── Rules.java
└── UiManager.java

```

## Estrutura do projeto

```
src/
├── engine/
│   ├── Game.java
│   ├── Board.java
│   ├── Player.java
│   ├── Piece.java
│   └── ...
│
├── rules/
│   ├── WinCondition.java
│   ├── AlignmentWinCondition.java
│   └── ...
│
├── config/
│   ├── GameConfig.java
│   └── GameLoader.java
│
└── Main.java

games/
├── jogo1.json
└── jogo2.json
```

> Estrutura inicial de referência da disciplina. Pode evoluir conforme o projeto avança — atualizar esta seção quando isso acontecer.

## Funcionalidades do jogo (Checkpoint 1)

O jogo implementado deve possuir:

- [ ] Tabuleiro
- [ ] Jogadores
- [ ] Controle de turnos
- [ ] Peças / marcações
- [ ] Validação de jogadas
- [ ] Condição de vitória
- [ ] Condição de empate (quando aplicável)
- [ ] Encerramento correto da partida

## Roadmap

- **Checkpoint 1 (14/10):** jogo único, funcional, em Java, executado pelo terminal.
- **Checkpoint 2 (16/11):** generalização para uma Engine capaz de rodar pelo menos dois jogos diferentes a partir de arquivos de configuração JSON, compartilhando efetivamente os componentes da Engine.
- **Entrega final (16/12):** Engine completa e extensível, capaz de interpretar novas configurações que usem comportamentos já suportados, sem alteração de código.

## Diagrama de classes

> A ser adicionado (`docs/diagrama-classes.png` ou similar).

## Referências

- [Board Game Engine — mnbroatch](https://github.com/mnbroatch/board-game-engine)
- [Tabletop Games Framework (TAG)](https://tabletopgames.ai/)
- [boardgame.io](https://github.com/boardgameio/boardgame.io)
