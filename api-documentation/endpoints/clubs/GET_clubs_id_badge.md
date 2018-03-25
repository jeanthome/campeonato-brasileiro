
# Recurso: Clubes

## URI
    GET /clubs/:id/badge

## Descrição
Retorna o escudo de um clube específico. A imagem tem uma dimensão de 140x140 pixels e está no formato PNG.

## Parâmetros
Nenhum.

## Erros
- **CLUB_NOT_FOUND Exception** — Caso não exista um clube com o id especificado na requisição.

## Formato de retorno
Uma imagem de 140x140 pixels no formato PNG.

## Exemplo

    https://domain.com/clubs/78/badge

**Retorno**

![N|Solid](https://github.com/jeanthome/campeonato-brasileiro/blob/master/apicore/src/main/resources/images/clubs/flamengo/flamengo.png)
