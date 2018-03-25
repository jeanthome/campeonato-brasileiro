
# Recurso: Técnicos

## URI
    GET /coaches/:id/image

## Descrição
Retorna uma imagem de um técnico específico. A imagem tem uma dimensão de 140x140 pixels e está no formato PNG.

## Parâmetros
Nenhum.

## Erros
- **COACH_NOT_FOUND Exception** — Caso não exista um técnico com o id especificado na requisição.

## Formato de retorno
Uma imagem de 140x140 pixels no formato PNG.

## Exemplo

    https://domain.com/coaches/13/image

**Retorno**

![N|Solid](https://github.com/jeanthome/campeonato-brasileiro/blob/master/apicore/src/main/resources/images/clubs/corinthians/carille.png)
