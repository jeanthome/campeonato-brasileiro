package brasileirao.api.service.impl;

import brasileirao.api.dao.CardDao;
import brasileirao.api.dao.PlayerInMatchDao;
import brasileirao.api.dto.CardDto;
import brasileirao.api.dto.CardInputDto;
import brasileirao.api.enums.ServiceExceptionMessageEnum;
import brasileirao.api.exception.ServiceException;
import brasileirao.api.persistence.Card;
import brasileirao.api.persistence.Goal;
import brasileirao.api.persistence.PlayerInMatch;
import brasileirao.api.service.CardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

   /**
    * Objeto de acesso de dados da entidade {@link Card}
    */
   @Autowired
   private CardDao cardDao;

   /**
    * Classe de acesso de dados da entidade {@link brasileirao.api.persistence.PlayerInMatch}
    */
   @Autowired
   private PlayerInMatchDao playerInMatchDao;

   @Override
   public Card save(Card card) {
      return this.cardDao.save(card);
   }

   @Override
   public Card convertCardInputDtoToCard(CardInputDto cardInputDto) throws ServiceException{

      final ModelMapper modelMapper = new ModelMapper();
      final Card card = modelMapper.map(cardInputDto, Card.class);
      card.setId(null);

      final PlayerInMatch cardOwnerPlayer =
              this.playerInMatchDao.findOne(cardInputDto.getCardOwner());

      if (cardOwnerPlayer == null) {
         throw new ServiceException(ServiceExceptionMessageEnum.PLAYER_NOT_FOUND.getMessage());
      }

      card.setCardOwner(cardOwnerPlayer);
      return card;
   }

   @Override
   public CardDto convertCardToCardDto(Card card) {
      final ModelMapper modelMapper = new ModelMapper();
      final CardDto cardDto = modelMapper.map(card, CardDto.class);
      return cardDto;
   }

   @Override
   public List<CardDto> convertCardListToCardDtoList(List<Card> cardList) {
      final List<CardDto> cardDtoList = new ArrayList<>();
      for (Card card : cardList) {
         final CardDto cardDto = this.convertCardToCardDto(card);
         cardDtoList.add(cardDto);
      }
      return cardDtoList;
   }
}
