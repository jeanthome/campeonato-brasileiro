
# Recurso: Jogadores

## URI 
    GET /players
***

## Descrição
Retorna todos os jogadores registrados..
***

## Parâmetros
Nenhum.
***

## Formato de retorno
Um objeto JSON com uma lista de jogadores existentes.
***

## Exemplo

    https://domain.com/players

#### Retorno em caso de sucesso:
``` json
[{
    "identifier": 59,
    "displayName": "Guerrero",
    "age": 34,
    "position": {
        "name": "Atacante",
        "abbreviation": "ATA"
    },
    "number": 9,
    "links": [{
            "rel": "self",
            "href": "https://domain.com/players/59"
        },
        {
            "rel": "image",
            "href": "https://domain.com/players/59/image"
        },
        {
            "rel": "actualClub",
            "href": "https://domain.com/clubs/51"
        }
    ]
}, {
    "identifier": 48,
    "displayName": "Diego Alves",
    "age": 32,
    "position": {
        "name": "Goleiro",
        "abbreviation": "GOL"
    },
    "number": 1,
    "links": [{
            "rel": "self",
            "href": "https://domain.com/players/48"
        },
        {
            "rel": "image",
            "href": "https://domain.com/players/48/image"
        },
        {
            "rel": "actualClub",
            "href": "https://domain.com/clubs/51"
        }
    ]
}, {
    "identifier": 57,
    "displayName": "Everton Ribeiro",
    "age": 28,
    "position": {
        "name": "Meia Central",
        "abbreviation": "MEC"
    },
    "number": 7,
    "links": [{
            "rel": "self",
            "href": "https://domain.com/players/57"
        },
        {
            "rel": "image",
            "href": "https://domain.com/players/57/image"
        },
        {
            "rel": "actualClub",
            "href": "https://domain.com/clubs/51"
        }
    ]
}]
```
