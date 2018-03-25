
# Recurso: Clubes

## URI
    GET /clubs/:id
***

## Descrição
Retorna os dados de um clube específico.
***

## Parâmetros
Nenhum.
***

## Erros
- **404 Not Found** — Caso não exista um clube com o id especificado na requisição.
***

## Formato de retorno
Um objeto JSON com os dados do clube encontrado. Caso não exista um clube com o id especificado, 
é retornado um objeto JSON com todos os atributos com valores nulos (```null```).
***

## Exemplo

    https://domain.com/clubs/48

#### Retorno em caso de sucesso:
``` json
{
    "identifier": 48,
    "fullName": "Sport Club Corinthians Paulista",
    "name": "Corinthians",
    "abbreviation": "COR",
    "color": "#000",
    "_links": {
        "self": {
            "href": "https://domain.com/clubs/48"
        },
        "badge": {
            "href": "https://domain.com/clubs/48/badge"
        },
        "coach": {
            "href": "https://domain.com/clubs/48/coach"
        },
        "players": {
            "href": "https://domain.com/clubs/48/players"
        }
    }
}
```
#### Retorno em caso de erro 404 (Not Found):
``` json
{
    "identifier": null,
    "fullName": null,
    "name": null,
    "abbreviation": null,
    "color": null
}
``` 
