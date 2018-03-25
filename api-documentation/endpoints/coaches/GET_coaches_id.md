
# Recurso: Técnicos

## URI
    GET /coaches/:id
***

## Descrição
Retorna os dados de um técnico específico.
***

## Parâmetros
Nenhum.
***

## Erros
- **404 Not Found** — Caso não exista um técnico com o id especificado na requisição.
***

## Formato de retorno
Um objeto JSON com os dados do técnico encontrado. Caso não exista um técnico com o id especificado, 
é retornado um objeto JSON com todos os atributos com valores nulos (```null```).
***

## Exemplo

    https://domain.com/coaches/13

#### Retorno em caso de sucesso:
``` json
{
    "identifier": 13,
    "displayName": "Fábio Carille",
    "age": 44,
    "_links": {
        "self": {
            "href": "https://domain.com/coaches/13"
        },
        "image": {
            "href": "https://domain.com/coaches/13/image"
        },
        "actualClub": {
            "href": "https://domain.com/clubs/48"
        }
    }
}
```
#### Retorno em caso de erro 404 (Not Found):
``` json
{
    "identifier": null,
    "displayName": null,
    "age": null
}
``` 
