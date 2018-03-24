
# Recurso: Técnicos

    GET /coaches/:id

## Descrição
Retorna os dados de um técnico específico.

## Parâmetros
Nenhum.

## Erros
- **404 Not Found** — Caso não exista um técnico com o id especificado na requisição.

## Formato de retorno
Um objeto JSON com os dados do técnico encontrado.

## Exemplo

    https://domain.com/coaches/13

**Retorno**
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
