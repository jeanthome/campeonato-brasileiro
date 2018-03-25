
# Recurso: Jogos

## URI
    GET /matches/round/:roundNumber

## Descrição
Retorna uma lista com 10 jogos de um determinada rodada.

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
        "homeClubGoals": 0,
        "visitorClubGoals": 0,
        "stadiumName": "Arena Corinthians",
        "kickOff": "SAB 13/05/2017",
        "hour": "19:00",
        "finished": true,
        "links": []
    },
    {
        "identifier": 3,
        "roundNumber": 1,
        "homeClub": {
            "identifier": 52,
            "fullName": "Fluminense Football Club",
            "name": "Fluminense",
            "abbreviation": "FLU",
            "color": "#177d49",
            "links": []
        },
        "visitorClub": {
            "identifier": 56,
            "fullName": "Santos Futebol Clube",
            "name": "Santos",
            "abbreviation": "SAN",
            "color": "#000",
            "links": []
        },
        "homeClubGoals": 0,
        "visitorClubGoals": 0,
        "stadiumName": "Maracanã",
        "kickOff": "DOM 14/05/2017",
        "hour": "11:00",
        "finished": true,
        "links": []
    },
    {
        "identifier": 4,
        "roundNumber": 1,
        "homeClub": {
            "identifier": 54,
            "fullName": "Sociedade Esportiva Palmeiras",
            "name": "Palmeiras",
            "abbreviation": "PAL",
            "color": "#177d49",
            "links": []
        },
        "visitorClub": {
            "identifier": 59,
            "fullName": "Club de Regatas Vasco da Gama",
            "name": "Vasco",
            "abbreviation": "VAS",
            "color": "#000",
            "links": []
        },
        "homeClubGoals": 0,
        "visitorClubGoals": 0,
        "stadiumName": "Arena Palmeiras",
        "kickOff": "DOM 14/05/2017",
        "hour": "16:00",
        "finished": true,
        "links": []
    },
    {
        "identifier": 5,
        "roundNumber": 1,
        "homeClub": {
            "identifier": 50,
            "fullName": "Cruzeiro Esporte Clube",
            "name": "Cruzeiro",
            "abbreviation": "CRU",
            "color": "#09c",
            "links": []
        },
        "visitorClub": {
            "identifier": 57,
            "fullName": "São Paulo Futebol Clube",
            "name": "São Paulo",
            "abbreviation": "SAO",
            "color": "#a80000",
            "links": []
        },
        "homeClubGoals": 0,
        "visitorClubGoals": 0,
        "stadiumName": "Mineirão",
        "kickOff": "DOM 14/05/2017",
        "hour": "16:00",
        "finished": true,
        "links": []
    },
    {
        "identifier": 6,
        "roundNumber": 1,
        "homeClub": {
            "identifier": 45,
            "fullName": "Esporte Clube Bahia",
            "name": "Bahia",
            "abbreviation": "BAH",
            "color": "#104175",
            "links": []
        },
        "visitorClub": {
            "identifier": 43,
            "fullName": "Clube Atlético Paranaense",
            "name": "Atlético-PR",
            "abbreviation": "CAP",
            "color": "#a80000",
            "links": []
        },
        "homeClubGoals": 0,
        "visitorClubGoals": 0,
        "stadiumName": "Fonte Nova",
        "kickOff": "DOM 14/05/2017",
        "hour": "16:00",
        "finished": true,
        "links": []
    },
    {
        "identifier": 7,
        "roundNumber": 1,
        "homeClub": {
            "identifier": 55,
            "fullName": "Associação Atlética Ponte Preta",
            "name": "Ponte Preta",
            "abbreviation": "PON",
            "color": "#000",
            "links": []
        },
        "visitorClub": {
            "identifier": 58,
            "fullName": "Sport Club do Recife",
            "name": "Sport",
            "abbreviation": "SPO",
            "color": "#a80000",
            "links": []
        },
        "homeClubGoals": 0,
        "visitorClubGoals": 0,
        "stadiumName": "Moisés Lucarelli",
        "kickOff": "DOM 14/05/2017",
        "hour": "16:00",
        "finished": true,
        "links": []
    },
    {
        "identifier": 8,
        "roundNumber": 1,
        "homeClub": {
            "identifier": 44,
            "fullName": "Avaí Futebol Clube",
            "name": "Avaí",
            "abbreviation": "AVA",
            "color": "#09c",
            "links": []
        },
        "visitorClub": {
            "identifier": 60,
            "fullName": "Esporte Clube Vitória",
            "name": "Vitória",
            "abbreviation": "VIT",
            "color": "#a80000",
            "links": []
        },
        "homeClubGoals": 0,
        "visitorClubGoals": 0,
        "stadiumName": "Ressacada",
        "kickOff": "DOM 14/05/2017",
        "hour": "16:00",
        "finished": true,
        "links": []
    },
    {
        "identifier": 9,
        "roundNumber": 1,
        "homeClub": {
            "identifier": 53,
            "fullName": "Grêmio Foot-Ball Porto Alegrense",
            "name": "Grêmio",
            "abbreviation": "GRE",
            "color": "#09c",
            "links": []
        },
        "visitorClub": {
            "identifier": 46,
            "fullName": "Botafogo de Futebol e Regatas",
            "name": "Botafogo",
            "abbreviation": "BOT",
            "color": "#000",
            "links": []
        },
        "homeClubGoals": 0,
        "visitorClubGoals": 0,
        "stadiumName": "Arena do Grêmio",
        "kickOff": "DOM 14/05/2017",
        "hour": "19:00",
        "finished": true,
        "links": []
    },
    {
        "identifier": 10,
        "roundNumber": 1,
        "homeClub": {
            "identifier": 49,
            "fullName": "Coritiba Foot Ball Club",
            "name": "Coritiba",
            "abbreviation": "CFC",
            "color": "#177d49",
            "links": []
        },
        "visitorClub": {
            "identifier": 41,
            "fullName": "Atlético Clube Goianiense",
            "name": "Atlético-GO",
            "abbreviation": "ACG",
            "color": "#a80000",
            "links": []
        },
        "homeClubGoals": 0,
        "visitorClubGoals": 0,
        "stadiumName": "Couto Pereira",
        "kickOff": "SEG 15/05/2017",
        "hour": "20:00",
        "finished": true,
        "links": []
    }
]
```