# Recurso: Jogos

## URI
    PUT /matches/:id/goals
***

## Descrição
Insere um gol em uma partida específica.
***

## Parâmetros

- **clubType** _(required)_ — O clube que fez o gol. Valores possíveis:
    - 'HOME_CLUB': Caso em que o clube mandante fez o gol.
    - 'VISITOR_CLUB': Caso em que o clube visitante fez o gol.
- **goalOwner** _(required)_ — O identificador do jogador que fez o gol.
- **half** _(required)_ — O tempo do jogo em que o gol ocorreu. Valores possíveis:
    - 'FIRST_HALF': Caso o gol tenha ocorrido no primeiro tempo.
    - 'SECOND_HALF': Caso o gol tenha ocorrido no segundo tempo
- **minute** _(required)_ — O minuto em que o gol ocorreu.
- **goalType** _(required)_ — O tipo do gol. Valores possíveis:
    - 'NORMAL': Caso tenha sido um gol normal.
    - 'PENALTY': Caso tenha sido um gol de pênalti.
    - 'OWN_GOAL': Caso tenha sido gol contra.
    - 'FOUL': Caso tenha sido gol de falta.
- **title** _(required)_ — Um título para o lance do gol.
- **minute** _(required)_ — Uma descrição para o lance do gol.
***

## Erros
- **MATCH_NOT_FOUND Exception** — Caso não exista uma partida com o id especificado na requisição.
***

## Formato de retorno
Um objeto JSON com os dados do gol inserido.
***

## Exemplo

    https://domain.com/matches/182/goals

#### Body:
``` json
{
	"clubType" : "HOME_CLUB",
	"goalOwner": 24,
	"half": "SECOND_HALF",
	"minute": 16,
	"goalType": "NORMAL",
	"title": "GOLAÇO DE CABEÇA!",
	"description": "O zagueiro subiu no segundo andar para marcar um lindo gol de cabeça após cobrança de escanteio de Diego."
}
```

#### Retorno em caso de sucesso:
``` json
{
    "owner": {
        "id": 24,
        "displayName": "Réver",
        "positionAbbreviation": "ZAD",
        "number": 15
    },
    "minute": 16,
    "half": "SECOND_HALF",
    "goalType": "NORMAL",
    "title": "GOLAÇO DE CABEÇA!",
    "description": "O zagueiro subiu no segundo andar para marcar um lindo gol de cabeça após cobrança de escanteio de Diego."
}
```