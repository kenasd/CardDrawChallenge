package com.kenasd.poker.engine;

import com.kenasd.poker.model.Card;
import com.kenasd.poker.model.CardRank;
import com.kenasd.poker.model.CardSuit;
import com.kenasd.poker.model.Hand;
import com.kenasd.poker.pattern.CardPattern;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HandParser {

    private static final String SPLIT_PATTERN = "-";
    private static final Map<Character, CardSuit> CARD_SUIT_MAP = Map.of(
            'S', CardSuit.SPADES,
            'C', CardSuit.CLUBS,
            'H', CardSuit.HEARTS,
            'D', CardSuit.DIAMONDS
    );

    static {
        System.out.println("Cards have 4 suits: spades, clubs, hearts and diamonds.");
        System.out.println("And ranks: A, K, Q, J, 10, 9, 8, 7, 6, 5, 4, 3 and 2.");
        System.out.println("Joker and wild cards were skipped.");
        System.out.println("Accepted only combination of 5 unique cards split by \"-\".");
        System.out.println("First character must be one of S, C, H or D. Second part is the card ranks.");
        System.out.println("Examples: SA-H7-D4-C10-SJ, CA-C2-C3-C4-C5 and D10-HJ-DQ-CK-CA");
        System.out.println();
    }

    // better to have deck of cards to validate that card was used or not
    public Hand parse(String cardsString) {
        if (cardsString == null || cardsString.isEmpty())
            throw new IllegalArgumentException("Cards string cannot be null or empty.");

        String[] cards = cardsString.split(SPLIT_PATTERN);
        if (cards.length != CardPattern.CARD_COUNT)
            throw new IllegalArgumentException(String.format("Count of cards must be %s.", CardPattern.CARD_COUNT));

        Set<Card> handCards = new HashSet<>(CardPattern.CARD_COUNT);
        for (String card : cards) {
            if (card == null || card.length() < 2 || card.length() > 3)
                throw new IllegalArgumentException(String.format("Wrong card size: %s.", card));

            CardSuit cardSuit = CARD_SUIT_MAP.get(card.charAt(0));
            if (cardSuit == null)
                throw new IllegalArgumentException(String.format("Wrong card suit: %s.", card));

            CardRank cardRank = CardRank.of(card.substring(1));
            if (cardRank == null)
                throw new IllegalArgumentException(String.format("Wrong card rank: %s.", card));

            handCards.add(Card.of(cardRank, cardSuit));
        }

        if (handCards.size() != CardPattern.CARD_COUNT)
            throw new IllegalArgumentException("Hand must have 5 unique cards.");

        return new Hand(handCards);
    }

}
