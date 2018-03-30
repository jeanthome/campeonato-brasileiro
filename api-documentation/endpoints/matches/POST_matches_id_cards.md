# Recurso: Jogos

## URI
    POST /matches/:id/cards
***

## Descrição
Atribui um cartão a um jogador de uma determinada partida.
***

## Parâmetros

- **clubType** _(required)_ — O clube do jogador que recebeu o cartão. Valores possíveis:
    - 'HOME_CLUB': Caso o jogador que recebeu o cartão seja da equipe mandante.
    - 'VISITOR_CLUB': Caso o jogador que recebeu o cartão seja da equipe visitante.
- **cardOwner** _(required)_ — O identificador do jogador que recebeu o cartão.
- **cardColor** _(required)_ — A cor do cartão que o jogador recebeu. Valores possíveis:
    - 'YELLOW': Caso o jogador recebeu um cartão amarelo.
    - 'RED': Caso o jogador recebeu um cartão vermelho.
- **half** _(required)_ — O tempo do jogo em que o jogador recebeu o cartão. Valores possíveis:
    - 'FIRST_HALF': Caso o jogador tenha recebido o cartão no primeiro tempo.
    - 'SECOND_HALF': Caso o jogador tenha recebido o cartão no segundo tempo
- **minute** _(required)_ — O minuto em que o jogador recebeu o cartão.
- **reason** _(required)_ — O motivo pelo qual o jogador que recebeu o cartão.
***

## Erros
- **MATCH_NOT_FOUND Exception** — Caso não exista uma partida com o id especificado na requisição.
***

## Formato de retorno
Um objeto JSON com os dados do cartão inserido.
***

## Exemplo

    https://domain.com/matches/182/cards

#### Body:
``` json
{
	"clubType" : "HOME_CLUB",
	"cardOwner": 28,
	"cardColor": "YELLOW",
	"half": "FIRST_HALF",
	"minute": 45,
	"reason" : "Amarelo bobo por discutir com o árbitro principal."
}
```

#### Retorno em caso de sucesso:
``` json
{
    "cardOwner": {
        "id": 28,
        "displayName": "Everton",
        "positionAbbreviation": "MEC",
        "number": 22
    },
    "minute": 45,
    "half": "FIRST_HALF",
    "cardColor": "YELLOW",
    "reason": "Amarelo bobo por discutir com o árbitro principal."
}
```