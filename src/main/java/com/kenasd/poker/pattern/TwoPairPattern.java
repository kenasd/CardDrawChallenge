package com.kenasd.poker.pattern;

import com.kenasd.poker.model.Card;
import com.kenasd.poker.model.CardRank;
import com.kenasd.poker.model.HandCategory;

import java.util.Set;

public class TwoPairPattern implements CardPattern {

    @Override
    public HandCategory getCategory() {
        return HandCategory.TWO_PAIR;
    }

    @Override
    public CardRank match(Set<Card> cards) {
        return getTopCardFromTwoPair(cards);
    }
}
