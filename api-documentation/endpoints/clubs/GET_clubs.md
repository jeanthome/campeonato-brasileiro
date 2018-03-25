
# Recurso: Clubes

## URI 
    GET /clubs

## Descrição
Retorna todos os clubes participantes da temporada atual do campeonato.

## Parâmetros
Nenhum.

## Formato de retorno
Um objeto JSON com uma lista de clubes encontrados.

## Exemplo

    https://domain.com/clubs

**Retorno**
``` json
[{
    "identifier": 51,
    "fullName": "Clube de Regatas do Flamengo",
    "name": "Flamengo",
    "abbreviation": "FLA",
    "color": "#a80000",
    "links": [{
            "rel": "self",
            "href": "https://domain.com/clubs/51"
        },
        {
            "rel": "badge",
            "href": "https://domain.com/clubs/51/badge"
        },
        {
            "rel": "coach",
            "href": "https://domain.com/clubs/51/coach"
        },
        {
            "rel": "players",
            "href": "https://domain.com/clubs/51/players"
        }
    ]
}, {
    "identifier": 48,
    "fullName": "Sport Club Corinthians Paulista",
    "name": "Corinthians",
    "abbreviation": "COR",
    "color": "#000",
    "links": [{
            "rel": "self",
            "href": "https://domain.com/clubs/48"
        },
        {
            "rel": "badge",
            "href": "https://domain.com/clubs/48/badge"
        },
        {
            "rel": "coach",
            "href": "https://domain.com/clubs/48/coach"
        },
        {
            "rel": "players",
            "href": "https://domain.com/clubs/48/players"
        }
    ]
}, {
    "identifier": 54,
    "fullName": "Sociedade Esportiva Palmeiras",
    "name": "Palmeiras",
    "abbreviation": "PAL",
    "color": "#177d49",
    "links": [{
            "rel": "self",
            "href": "https://domain.com/clubs/54"
        },
        {
            "rel": "badge",
            "href": "https://domain.com/clubs/54/badge"
        },
        {
            "rel": "coach",
            "href": "https://domain.com/clubs/54/coach"
        },
        {
            "rel": "players",
            "href": "https://domain.com/clubs/54/players"
        }
    ]
}]
```
