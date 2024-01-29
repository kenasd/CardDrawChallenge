package com.kenasd.poker.pattern;

import com.kenasd.poker.model.Card;
import com.kenasd.poker.model.CardRank;
import com.kenasd.poker.model.HandCategory;

import java.util.Set;

public class FullHousePattern implements CardPattern {

    @Override
    public HandCategory getCategory() {
        return HandCategory.FULL_HOUSE;
    }

    @Override
    public CardRank match(Set<Card> cards) {
        int firstCardCount = 2;
        CardRank duplet = getCardByCount(cards, firstCardCount);
        if (duplet != null) {
            int secondCardCount = 3;
            CardRank triplet = getCardByCount(cards, secondCardCount);
            return duplet != triplet ? triplet : null;
        }
        return null;
    }
}
