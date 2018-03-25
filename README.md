# API Brasileirão

 **```ESTE É UM PROJETO PESSOAL SEM FINS COMERCIAIS, MANTIDO SOMENTE (E APENAS) PARA FINS EDUCACIONAIS```**

A ideia do projeto é desenvolver uma [REST API](http://en.wikipedia.org/wiki/Representational_State_Transfer "RESTful") 
que provê informações de uma temporada do Campeonato Brasileiro, tais como:
- Lista de clubes;
- Lista de jogadores;
- Lista de técnicos;
- Lista de jogos;
- Detalhes de cada jogo:
  - Gols;
  - Cartões;
  - Substituições.
- Tabela de classificação do Campeonato;
  
O projeto está sendo desenvolvido em Java com o auxílio do [Spring Boot](https://projects.spring.io/spring-boot/) e todo seu 
universo de funcionalidades. Para a persistência dos dados, foi escolhido utilizar um banco de dados relacional MySQL em parceria com
o [Hibernate](http://hibernate.org/orm/).

A seguir, você encontrará uma lista de chamadas disponíveis atualmente na API com suas respectivas 
documentações. Essa lista pode sofrer alterações conforme novas funcionalidades são adicionadas.

## Endpoints

#### Recurso: Jogos

- **[<code>GET</code> /matches/:id](https://github.com/jeanthome/campeonato-brasileiro/blob/master/api-documentation/endpoints/matches/GET_matches_id.md)**
- **[<code>GET</code> /matches/round/:roundNumber](https://github.com/jeanthome/campeonato-brasileiro/blob/master/api-documentation/endpoints/matches/GET_matches_round_roundNumber.md)**
- **[<code>GET</code> /matches/stadiums](https://github.com/jeanthome/campeonato-brasileiro/blob/master/api-documentation/endpoints/matches/GET_matches_stadiums.md)**
- **[<code>GET</code> /matches/goalType](https://github.com/jeanthome/campeonato-brasileiro/blob/master/api-documentation/endpoints/matches/GET_matches_goalType.md)**
- **[<code>PUT</code> /matches/:id/goals](https://github.com/jeanthome/campeonato-brasileiro/blob/master/api-documentation/endpoints/matches/PUT_matches_id_goals.md)**
- **[<code>PUT</code> /matches/:id/cards](https://github.com/jeanthome/campeonato-brasileiro/blob/master/api-documentation/endpoints/matches/PUT_matches_id_cards.md)**
- **[<code>PUT</code> /matches/:id/substitutions](https://github.com/jeanthome/campeonato-brasileiro/blob/master/api-documentation/endpoints/matches/PUT_matches_id_substitutions.md)**


#### Recurso: Clubes

- **[<code>GET</code> /clubs](https://github.com/jeanthome/campeonato-brasileiro/blob/master/api-documentation/endpoints/clubs/GET_clubs.md)**
- **[<code>GET</code> /clubs/:id](https://github.com/jeanthome/campeonato-brasileiro/blob/master/api-documentation/endpoints/clubs/GET_clubs_id.md)**
- **[<code>GET</code> /clubs/:id/players](https://github.com/jeanthome/campeonato-brasileiro/blob/master/api-documentation/endpoints/clubs/GET_clubs_id_players.md)**
- **[<code>GET</code> /clubs/:id/coach](https://github.com/jeanthome/campeonato-brasileiro/blob/master/api-documentation/endpoints/clubs/GET_clubs_id_coach.md)**
- **[<code>GET</code> /clubs/:id/badge](https://github.com/jeanthome/campeonato-brasileiro/blob/master/api-documentation/endpoints/clubs/GET_clubs_id_badge.md)**


#### Recurso: Jogadores

- **[<code>GET</code> /players](https://github.com/jeanthome/campeonato-brasileiro/blob/master/api-documentation/endpoints/players/GET_players.md)**
- **[<code>GET</code> /players/:id](https://github.com/jeanthome/campeonato-brasileiro/blob/master/api-documentation/endpoints/players/GET_players_id.md)**
- **[<code>GET</code> /players/:id/image](https://github.com/jeanthome/campeonato-brasileiro/blob/master/api-documentation/endpoints/players/GET_players_id_image.md)**


#### Recurso: Técnicos

- **[<code>GET</code> /coaches](https://github.com/jeanthome/campeonato-brasileiro/blob/master/api-documentation/endpoints/coaches/GET_coaches.md)**
- **[<code>GET</code> /coaches/:id](https://github.com/jeanthome/campeonato-brasileiro/blob/master/api-documentation/endpoints/coaches/GET_coaches_id.md)**
- **[<code>GET</code> /coaches/:id/image](https://github.com/jeanthome/campeonato-brasileiro/blob/master/api-documentation/endpoints/coaches/GET_coaches_id_image.md)**
