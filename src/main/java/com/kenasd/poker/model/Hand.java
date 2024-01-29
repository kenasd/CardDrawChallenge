package com.kenasd.poker.model;

import java.util.Set;

public class Hand {
    private final Set<Card> cards;

    public Hand(Set<Card> cards) {
        this.cards = cards;
    }

    public static Hand of(Set<Card> cards) {
        return new Hand(cards);
    }

    public Set<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                '}';
    }
}
