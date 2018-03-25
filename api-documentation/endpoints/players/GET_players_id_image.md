
# Recurso: Jogadores

## URI
    GET /players/:id/image

## Descrição
Retorna uma imagem de um jogador específico. A imagem tem uma dimensão de 140x140 pixels e está no formato PNG.

## Parâmetros
Nenhum.

## Erros
- **PLAYER_NOT_FOUND Exception** — Caso não exista um jogador com o id especificado na requisição.

## Formato de retorno
Uma imagem de 140x140 pixels no formato PNG.

## Exemplo

    https://domain.com/players/59/image

**Retorno**

![N|Solid](https://github.com/jeanthome/campeonato-brasileiro/blob/master/apicore/src/main/resources/images/clubs/flamengo/guerrero.png)
