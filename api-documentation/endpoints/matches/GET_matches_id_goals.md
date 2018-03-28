# Recurso: Jogos

## URI
    GET /matches/:id/goals
***

## Descrição
Obtém a lista de gols de uma determinada partida.
***

## Parâmetros

- **clubType** _(optional)_ — Filtro que permite buscar os gols filtrando pelo tipo de clube. Valores possíveis:
    - 'HOME_CLUB': Caso deseja-se obter somente os gols da equipe mandante.
    - 'VISITOR_CLUB': Caso deseja-se obter somente os gols da equipe visitante.
***

## Erros
- **INVALID_MATCH_ID Exception** — Caso o id da partida seja inválido, ou seja, não seja um número inteiro.
- **MATCH_NOT_FOUND Exception** — Caso não exista uma partida com o id especificado na requisição.
***

## Formato de retorno
Um objeto JSON com os dados dos gols encontrados, em um array.
***

## Exemplo

    https://domain.com/matches/182/goals

#### Retorno em caso de sucesso:
``` json
{
    "homeClubGoals": [{
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
        },
        {
            "owner": {
                "id": 27,
                "displayName": "Guerrero",
                "positionAbbreviation": "ATA",
                "number": 9
            },
            "minute": 37,
            "half": "SECOND_HALF",
            "goalType": "NORMAL",
            "title": "Que frieza!",
            "description": "Em contra-ataque da equipe Rubro-Negra, Everton Riveiro lança para Guerrero que, cara a cara com o goleiro, teve calma para encobrir o goleiro atleticano."
        },
    ],
    "visitorClubGoals": [{
        "owner": {
            "id": 36,
            "displayName": "Elias",
            "positionAbbreviation": "VOL",
            "number": 8
        },
        "minute": 42,
        "half": "FIRST_HALF",
        "goalType": "PENALTY",
        "title": "Guardou.",
        "description": "Elias marca para o time visitante, em cobrança de pênalti que não deu chances ao goleiro Rubro-Negro. 0x1 no placar."
    }]
}
```