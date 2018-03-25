
# Recurso: Clubes

## URI
    GET /clubs/:id/players
***

## Descrição
Retorna a lista de jogadores de um clube específico.
***

## Parâmetros
Nenhum.
***

## Erros
- **CLUB_NOT_FOUND Exception** — Caso não exista um clube com o id especificado na requisição.
***

## Formato de retorno
Um objeto JSON com os dados dos jogadores encontrados, em um array. Caso não existam jogadores associados ao 
clube com o id especificado, é retornado um objeto JSON representando um array vazio.
***

## Exemplo

    https://domain.com/clubs/48/players

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
    "_links": {
        "self": {
            "href": "https://domain.com/players/59"
        },
        "image": {
            "href": "https://domain.com/players/59/image"
        },
        "actualClub": {
            "href": "https://domain.com/clubs/51"
        }
    }
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
    "identifier": 58,
    "displayName": "Diego",
    "age": 33,
    "position": {
        "name": "Meia Central",
        "abbreviation": "MEC"
    },
    "number": 35,
    "links": [{
            "rel": "self",
            "href": "https://domain.com/players/58"
        },
        {
            "rel": "image",
            "href": "https://domain.com/players/58/image"
        },
        {
            "rel": "actualClub",
            "href": "https://domain.com/clubs/51"
        }
    ]
}]
```
