
# Recurso: Jogadores

## URI
    GET /players/:id

## Descrição
Retorna os dados de um jogador específico.

## Parâmetros
Nenhum.

## Erros
- **404 Not Found** — Caso não exista um jogador com o id especificado na requisição.

## Formato de retorno
Um objeto JSON com os dados do jogador encontrado. Caso não exista um jogador com o id especificado, 
é retornado um objeto JSON com todos os atributos com valores nulos (```null```).

## Exemplo

    https://domain.com/players/59

#### Retorno em caso de sucesso:
``` json
{
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
}
```
#### Retorno em caso de erro 404 (Not Found):
``` json
{
    "identifier": null,
    "displayName": null,
    "age": null,
    "position": null,
    "number": null
}
``` 
