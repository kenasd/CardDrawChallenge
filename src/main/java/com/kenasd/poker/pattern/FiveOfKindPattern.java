package com.kenasd.poker.pattern;

import com.kenasd.poker.model.Card;
import com.kenasd.poker.model.CardRank;
import com.kenasd.poker.model.HandCategory;

import java.util.Set;

public class FiveOfKindPattern implements CardPattern {

    @Override
    public HandCategory getCategory() {
        return HandCategory.FIVE_OF_KIND;
    }

    @Override
    public CardRank match(Set<Card> cards) {
        int cardCount = 4;
        return hasWild(cards) ? getCardByCount(cards, cardCount) : null;
    }
}
