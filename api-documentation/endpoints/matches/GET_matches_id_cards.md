# Recurso: Jogos

## URI
    GET /matches/:id/cards
***

## Descrição
Obtém a lista de cartões de uma determinada partida.
***

## Parâmetros

- **clubType** _(optional)_ — Filtro que permite buscar os cartões filtrando pelo tipo de clube. Valores possíveis:
    - 'HOME_CLUB': Caso deseja-se obter somente os cartões da equipe mandante.
    - 'VISITOR_CLUB': Caso deseja-se obter somente os cartões da equipe visitante.
***

## Erros
- **INVALID_MATCH_ID Exception** — Caso o id da partida seja inválido, ou seja, não seja um número inteiro.
- **MATCH_NOT_FOUND Exception** — Caso não exista uma partida com o id especificado na requisição.
***

## Formato de retorno
Um objeto JSON com os dados dos cartões encontrados, em um array.
***

## Exemplo

    https://domain.com/matches/182/cards?clubType=VISITOR_CLUB

#### Retorno em caso de sucesso:
``` json
{
    "visitorClubCards": [{
        "cardOwner": {
            "id": 32,
            "displayName": "Carlos César",
            "positionAbbreviation": "ZAD",
            "number": 19
        },
        "minute": 8,
        "half": "SECOND_HALF",
        "cardColor": "YELLOW",
        "reason": "Amarelo bobo para o zagueiro, por discutir com o árbitro principal."
    }]
}
```
