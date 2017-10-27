package brasileirao.api.service;

import brasileirao.api.dto.CardDto;
import brasileirao.api.dto.input.CardInputDto;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.persistence.Card;

import java.util.List;

/**
 * Interface da classe de serviços da entidade {@link Card};
 */
public interface CardService {

   /**
    * Persiste no banco uma instância da entidade {@link Card}.
    *
    * @param card Instância a ser persistida.
    * @return Instância persistida.
    */
   Card save(Card card);

   /**
    * Converte uma instância de CardInputDto em uma instâncai de Card.
    *
    * @param cardInputDto Instância a ser convertida.
    * @return Instância de {@link Card}.
    * @throws ServiceException Lançada ao não encontrar PlayerInMatch;
    */
   Card convertCardInputDtoToCard(CardInputDto cardInputDto) throws ServiceException;

   /**
    * Converte uma entidade de {@link Card} em uma de {@link CardDto}.
    *
    * @param card Instância a ser convertida.
    * @return Instância de {@link CardDto}.
    */
   CardDto convertCardToCardDto(Card card);

   /**
    * Converte uma lista de {@link Card} em una lista de {@link CardDto}
    *
    * @param cardList Lista com as instâncias a serem convertidas.
    * @return Lista com os DTOs convertidos.
    */
   List<CardDto> convertCardListToCardDtoList(List<Card> cardList);
}
