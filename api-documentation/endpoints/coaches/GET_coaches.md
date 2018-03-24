
# Recurso: Técnicos

## URI 
    GET /coaches

## Descrição
Retorna todos os técnicos registrados.

## Parâmetros
Nenhum.

## Formato de retorno
Um objeto JSON com uma lista de técnicos existentes.

## Exemplo

    https://domain.com/coaches

**Retorno**
``` json
[{
    "identifier": 15,
    "displayName": "Mano Menezes",
    "age": 55,
    "links": [{
            "rel": "self",
            "href": "https://domain.com/coaches/15"
        },
        {
            "rel": "image",
            "href": "https://domain.com/coaches/15/image"
        },
        {
            "rel": "actualClub",
            "href": "https://domain.com/clubs/15"
        }
    ]
}, {
    "identifier": 13,
    "displayName": "Fábio Carille",
    "age": 44,
    "links": [{
            "rel": "self",
            "href": "https://domain.com/coaches/13"
        },
        {
            "rel": "image",
            "href": "https://domain.com/coaches/13/image"
        },
        {
            "rel": "actualClub",
            "href": "https://domain.com/clubs/13"
        }
    ]
}, {
    "identifier": 18,
    "displayName": "Renato Gaúcho",
    "age": 55,
    "links": [{
            "rel": "self",
            "href": "https://domain.com/coaches/18"
        },
        {
            "rel": "image",
            "href": "https://domain.com/coaches/18/image"
        },
        {
            "rel": "actualClub",
            "href": "https://domain.com/clubs/18"
        }
    ]
}]
```
