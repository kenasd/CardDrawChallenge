package com.kenasd.poker.pattern;

import com.kenasd.poker.model.Card;
import com.kenasd.poker.model.CardRank;
import com.kenasd.poker.model.HandCategory;

import java.util.Set;

public class StraightFlushPattern implements CardPattern {

    @Override
    public HandCategory getCategory() {
        return HandCategory.STRAIGHT_FLUSH;
    }

    @Override
    public CardRank match(Set<Card> cards) {
        return hasSameSuit(cards) ? getTopCardInStraightOrder(cards) : null;
    }
}
