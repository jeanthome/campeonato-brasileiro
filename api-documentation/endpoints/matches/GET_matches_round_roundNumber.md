
# Recurso: Jogos

## URI
    GET /matches/round/:roundNumber

## Descrição
Retorna uma lista com os 10 jogos de uma determinada rodada.

## Parâmetros
Nenhum.

## Erros
- **INVALID_ROUND_NUMBER Exception** — Caso o número da rodada especificado na requisição seja inválido,
ou seja, não pertença ao intervalo [1-38].

## Formato de retorno
Um objeto JSON com os dados dos jogos encontrados, em um array. Caso não existam jogos associados à 
rodada especificada, é retornado um objeto JSON representando um array vazio.

## Exemplo

    https://domain.com/matches/round/1

#### Retorno em caso de sucesso:
``` json
[{
        "identifier": 182,
        "roundNumber": 1,
        "homeClub": {
            "identifier": 51,
            "fullName": "Clube de Regatas do Flamengo",
            "name": "Flamengo",
            "abbreviation": "FLA",
            "color": "#a80000",
            "links": []
        },
        "visitorClub": {
            "identifier": 42,
            "fullName": "Clube Atlético Mineiro",
            "name": "Atlético-MG",
            "abbreviation": "CAM",
            "color": "#000",
            "links": []
        },
        "homeClubGoals": 2,
        "visitorClubGoals": 1,
        "stadiumName": "Maracanã",
        "kickOff": "SAB 13/05/2017",
        "hour": "16:00",
        "finished": true,
        "links": []
    },
    {
        "identifier": 2,
        "roundNumber": 1,
        "homeClub": {
            "identifier": 48,
            "fullName": "Sport Club Corinthians Paulista",
            "name": "Corinthians",
            "abbreviation": "COR",
            "color": "#000",
            "links": []
        },
        "visitorClub": {
            "identifier": 47,
            "fullName": "Associação Chapecoense de Futebol",
            "name": "Chapecoense",
            "abbreviation": "CHA",
            "color": "#177d49",
            "links": []
        },
        "homeClubGoals": 1,
        "visitorClubGoals": 1,
        "stadiumName": "Arena Corinthians",
        "kickOff": "SAB 13/05/2017",
        "hour": "19:00",
        "finished": true,
        "links": []
    },
    {},
    {},
    {},
    {},
    {},
    {},
    {},
    {}
]
```