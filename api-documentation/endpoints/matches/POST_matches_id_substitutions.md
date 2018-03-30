# Recurso: Jogos

## URI
    POST /matches/:id/substitutions
***

## Descrição
Insere uma substituição de jogadores em uma partida específica.
***

## Parâmetros

- **clubType** _(required)_ — O clube que efetuou a substituição. Valores possíveis:
    - 'HOME_CLUB': Caso em que o clube mandante fez a substituição.
    - 'VISITOR_CLUB': Caso em que o clube visitante fez a substituição.
- **playerWhoLeaves** _(required)_ — O identificador do jogador que saiu da partida.
- **playerWhoEnters** _(required)_ — O identificador do jogador que entrou na partida.
- **half** _(required)_ — O tempo do jogo em que a substituição foi efetuada. Valores possíveis:
    - 'FIRST_HALF': Caso a substituição tenha sido efetuada no primeiro tempo.
    - 'SECOND_HALF': Caso a substituição tenha sido efetuada no segundo tempo
- **minute** _(required)_ — O minuto em que a substituição foi efetuada.
***

## Erros
- **MATCH_NOT_FOUND Exception** — Caso não exista uma partida com o id especificado na requisição.
***

## Formato de retorno
Um objeto JSON com os dados da substituição inserida.
***

## Exemplo

    https://domain.com/matches/182/substitutions

#### Body:
``` json
{
    "clubType": "HOME_CLUB",
    "playerWhoLeaves": 28,
    "playerWhoEnters": 93,
    "half": "SECOND_HALF",
    "minute": 34
}
```

#### Retorno em caso de sucesso:
``` json
{
    "playerWhoLeaves": {
        "id": 28,
        "displayName": "Everton",
        "positionAbbreviation": "MEC",
        "number": 22
    },
    "playerWhoEnters": {
        "id": 93,
        "displayName": "Felipe Vizeu",
        "positionAbbreviation": "ATA",
        "number": 47
    },
    "minute": 34,
    "half": "SECOND_HALF"
}
```