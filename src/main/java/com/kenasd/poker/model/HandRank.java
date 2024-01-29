package com.kenasd.poker.model;

public class HandRank {
    private final Hand hand;
    private final HandCategory category;
    private final CardRank topCardRank;

    public HandRank(Hand hand, HandCategory category, CardRank topCardRank) {
        this.hand = hand;
        this.category = category;
        this.topCardRank = topCardRank;
    }

    public static HandRank of(Hand hand, HandCategory category, CardRank topCardRank) {
        return new HandRank(hand, category, topCardRank);
    }

    public Hand getHand() {
        return hand;
    }

    public HandCategory getCategory() {
        return category;
    }

    public CardRank getTopCardRank() {
        return topCardRank;
    }
}
