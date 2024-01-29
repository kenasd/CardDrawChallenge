package com.kenasd.poker.pattern;

import com.kenasd.poker.model.Card;
import com.kenasd.poker.model.CardRank;
import com.kenasd.poker.model.HandCategory;

import java.util.Set;

public class FlushPattern implements CardPattern {

    @Override
    public HandCategory getCategory() {
        return HandCategory.FLUSH;
    }

    @Override
    public CardRank match(Set<Card> cards) {
        return hasSameSuit(cards) ? getTopCard(cards) : null;
    }
}
