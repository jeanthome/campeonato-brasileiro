
# Recurso: Jogos

## URI
    GET /matches/goalType
***

## Descrição
Retorna uma lista com os possíveis tipos de gol.
***

## Parâmetros
Nenhum.
***

## Formato de retorno
Um objeto JSON com os tipos de gol existentes, em um array.
***

## Exemplo

    https://domain.com/matches/goalType

#### Retorno em caso de sucesso:
``` json
[{
        "label": "Normal",
        "value": "NORMAL"
    },
    {
        "label": "Pênalti",
        "value": "PENALTY"
    },
    {
        "label": "Gol contra",
        "value": "OWN_GOAL"
    },
    {
        "label": "Gol de falta",
        "value": "FOUL"
    }
]
```

