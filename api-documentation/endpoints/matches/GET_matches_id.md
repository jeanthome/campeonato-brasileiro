
# Recurso: Jogos

## URI
    GET /matches/:id
***

## Descrição
Retorna os dados de um jogo específico.
***

## Parâmetros
Nenhum.
***

## Erros
- **404 Not Found** — Caso não exista uma partida com o id especificado na requisição.
***

## Formato de retorno
Um objeto JSON com os dados da partida encontrada. Caso não exista uma partida com o id especificado, 
é retornado um objeto JSON com todos os atributos com valores nulos (```null```).
***

## Exemplo

    https://domain.com/matches/182

#### Retorno em caso de sucesso:
``` json
{
    "identifier": 182,
    "roundNumber": 1,
    "homeClub": {
        "identifier": 51,
        "fullName": "Clube de Regatas do Flamengo",
        "name": "Flamengo",
        "abbreviation": "FLA",
        "color": "#a80000"
    },
    "visitorClub": {
        "identifier": 42,
        "fullName": "Clube Atlético Mineiro",
        "name": "Atlético-MG",
        "abbreviation": "CAM",
        "color": "#000"
    },
    "homeClubStartingPlayers": [{
            "id": 23,
            "displayName": "Alex Muralha",
            "positionAbbreviation": "GOL",
            "number": 38
        },
        {
            "id": 19,
            "displayName": "Pará",
            "positionAbbreviation": "LAD",
            "number": 21
        },
        {
            "id": 24,
            "displayName": "Réver",
            "positionAbbreviation": "ZAD",
            "number": 15
        },
        {
            "id": 18,
            "displayName": "Rafael Vaz",
            "positionAbbreviation": "ZAE",
            "number": 33
        },
        {
            "id": 25,
            "displayName": "Miguel Trauco",
            "positionAbbreviation": "LAE",
            "number": 13
        },
        {
            "id": 20,
            "displayName": "Willian Arão",
            "positionAbbreviation": "VOL",
            "number": 5
        },
        {
            "id": 26,
            "displayName": "Márcio Araújo",
            "positionAbbreviation": "VOL",
            "number": 8
        },
        {
            "id": 21,
            "displayName": "Matheus Sávio",
            "positionAbbreviation": "MEC",
            "number": 42
        },
        {
            "id": 28,
            "displayName": "Everton",
            "positionAbbreviation": "MEC",
            "number": 22
        },
        {
            "id": 22,
            "displayName": "Berrío",
            "positionAbbreviation": "ATA",
            "number": 28
        },
        {
            "id": 27,
            "displayName": "Guerrero",
            "positionAbbreviation": "ATA",
            "number": 9
        }
    ],
    "visitorClubStartingPlayers": [{
            "id": 29,
            "displayName": "Victor",
            "positionAbbreviation": "GOL",
            "number": 1
        },
        {
            "id": 31,
            "displayName": "Felipe Santana",
            "positionAbbreviation": "ZAD",
            "number": 26
        },
        {
            "id": 32,
            "displayName": "Carlos César",
            "positionAbbreviation": "ZAD",
            "number": 19
        },
        {
            "id": 30,
            "displayName": "Gabriel",
            "positionAbbreviation": "ZAE",
            "number": 30
        },
        {
            "id": 33,
            "displayName": "Fábio Santos",
            "positionAbbreviation": "ZAE",
            "number": 6
        },
        {
            "id": 34,
            "displayName": "Rafael Carioca",
            "positionAbbreviation": "VOL",
            "number": 5
        },
        {
            "id": 36,
            "displayName": "Elias",
            "positionAbbreviation": "VOL",
            "number": 8
        },
        {
            "id": 37,
            "displayName": "Adilson",
            "positionAbbreviation": "VOL",
            "number": 21
        },
        {
            "id": 35,
            "displayName": "Otero",
            "positionAbbreviation": "MEC",
            "number": 11
        },
        {
            "id": 38,
            "displayName": "Robinho",
            "positionAbbreviation": "ATA",
            "number": 7
        },
        {
            "id": 39,
            "displayName": "Fred",
            "positionAbbreviation": "ATA",
            "number": 9
        }
    ],
    "homeClubSubstitutePlayers": [{
            "id": 85,
            "displayName": "Thiago",
            "positionAbbreviation": "GOL",
            "number": 30
        },
        {
            "id": 87,
            "displayName": "Leo Duarte",
            "positionAbbreviation": "GOL",
            "number": 43
        },
        {
            "id": 95,
            "displayName": "Rodinei",
            "positionAbbreviation": "LAD",
            "number": 2
        },
        {
            "id": 86,
            "displayName": "Juan",
            "positionAbbreviation": "ZAD",
            "number": 4
        },
        {
            "id": 88,
            "displayName": "Renê",
            "positionAbbreviation": "LAE",
            "number": 6
        },
        {
            "id": 89,
            "displayName": "Ronaldo",
            "positionAbbreviation": "VOL",
            "number": 21
        },
        {
            "id": 96,
            "displayName": "Cuéllar",
            "positionAbbreviation": "VOL",
            "number": 26
        },
        {
            "id": 90,
            "displayName": "Ederson",
            "positionAbbreviation": "MEC",
            "number": 10
        },
        {
            "id": 91,
            "displayName": " Lucas Paquetá",
            "positionAbbreviation": "MEC",
            "number": 39
        },
        {
            "id": 92,
            "displayName": "Leandro Damião",
            "positionAbbreviation": "ATA",
            "number": 18
        },
        {
            "id": 93,
            "displayName": "Felipe Vizeu",
            "positionAbbreviation": "ATA",
            "number": 47
        },
        {
            "id": 94,
            "displayName": "Vinicius Junior",
            "positionAbbreviation": "ATA",
            "number": 20
        }
    ],
    "visitorClubSubstitutePlayers": [{
            "id": 97,
            "displayName": "Giovanni",
            "positionAbbreviation": "GOL",
            "number": 20
        },
        {
            "id": 98,
            "displayName": "Rodrigão",
            "positionAbbreviation": "ZAE",
            "number": 16
        },
        {
            "id": 99,
            "displayName": "Yago",
            "positionAbbreviation": "VOL",
            "number": 25
        },
        {
            "id": 100,
            "displayName": "Ralph",
            "positionAbbreviation": "VOL",
            "number": 15
        },
        {
            "id": 101,
            "displayName": "Marlone",
            "positionAbbreviation": "MEC",
            "number": 92
        }
    ],
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
        }
    ],
    "homeClubCards": [{
            "cardOwner": {
                "id": 19,
                "displayName": "Pará",
                "positionAbbreviation": "LAD",
                "number": 21
            },
            "minute": 40,
            "half": "FIRST_HALF",
            "cardColor": "YELLOW",
            "reason": "Entrada dura em Robinho, impedindo o que seria o primeiro gol do jogo. Pênalti para o Atlético."
        }
    ],
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
    }],
    "homeClubSubstitutions": [{
            "playerWhoLeaves": {
                "id": 23,
                "displayName": "Alex Muralha",
                "positionAbbreviation": "GOL",
                "number": 38
            },
            "playerWhoEnters": {
                "id": 85,
                "displayName": "Thiago",
                "positionAbbreviation": "GOL",
                "number": 30
            },
            "minute": 34,
            "half": "FIRST_HALF"
        },
        {
            "playerWhoLeaves": {
                "id": 27,
                "displayName": "Guerrero",
                "positionAbbreviation": "ATA",
                "number": 9
            },
            "playerWhoEnters": {
                "id": 93,
                "displayName": "Felipe Vizeu",
                "positionAbbreviation": "ATA",
                "number": 47
            },
            "minute": 17,
            "half": "SECOND_HALF"
        }
    ],
    "visitorClubSubstitutions": [{
        "playerWhoLeaves": {
            "id": 29,
            "displayName": "Victor",
            "positionAbbreviation": "GOL",
            "number": 1
        },
        "playerWhoEnters": {
            "id": 97,
            "displayName": "Giovanni",
            "positionAbbreviation": "GOL",
            "number": 20
        },
        "minute": 12,
        "half": "SECOND_HALF"
    }]
}
```
#### Retorno em caso de erro 404 (Not Found):
``` json
{
    "identificator": null,
    "roundNumber": null,
    "homeClub": null,
    "visitorClub": null,
    "homeClubStartingPlayers": null,
    "visitorClubStartingPlayers": null,
    "homeClubSubstitutePlayers": null,
    "visitorClubSubstitutePlayers": null,
    "homeClubGoals": null,
    "visitorClubGoals": null,
    "homeClubCards": null,
    "visitorClubCards": null,
    "homeClubSubstitutions": null,
    "visitorClubSubstitutions": null
}
``` 
