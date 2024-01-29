package com.kenasd.poker.pattern;

import com.kenasd.poker.model.Card;
import com.kenasd.poker.model.CardRank;
import com.kenasd.poker.model.HandCategory;

import java.util.Set;

public class HighCardPattern implements CardPattern {

    @Override
    public HandCategory getCategory() {
        return HandCategory.HIGH_CARD;
    }

    @Override
    public CardRank match(Set<Card> cards) {
        return getTopCard(cards);
    }
}
