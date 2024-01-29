package com.kenasd.poker.pattern;

import com.kenasd.poker.model.Card;
import com.kenasd.poker.model.CardRank;
import com.kenasd.poker.model.HandCategory;

import java.util.Set;

public class OnePairPattern implements CardPattern {

    @Override
    public HandCategory getCategory() {
        return HandCategory.ONE_PAIR;
    }

    @Override
    public CardRank match(Set<Card> cards) {
        int cardCount = 2;
        return getCardByCount(cards, cardCount);
    }
}
