package com.kenasd.poker.pattern;

import com.kenasd.poker.model.Card;
import com.kenasd.poker.model.CardRank;
import com.kenasd.poker.model.HandCategory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.kenasd.poker.model.CardRank.*;
import static com.kenasd.poker.model.CardSuit.*;
import static org.junit.jupiter.api.Assertions.*;

class TwoPairPatternTest {
    private final CardPattern pattern = new TwoPairPattern();

    @Test
    void validateHandCategory() {
        assertEquals(HandCategory.TWO_PAIR, pattern.getCategory());
    }

    @Test
    void validateRightCombination() {
        CardRank combination = rK;
        Set<Card> cards = Set.of(
                Card.of(r2, CLUBS),
                Card.of(r9, CLUBS),
                Card.of(r2, DIAMONDS),
                Card.of(combination, SPADES),
                Card.of(combination, HEARTS));
        CardRank cardRank = pattern.match(cards);
        assertNotNull(cardRank);
        assertEquals(combination, cardRank);
    }

    @Test
    void validateWrongCombinationWithout2PairsCombination() {
        Set<Card> cards = Set.of(
                Card.of(rQ, CLUBS),
                Card.of(rA, CLUBS),
                Card.of(rA, DIAMONDS),
                Card.of(rJ, SPADES),
                Card.of(r2, HEARTS));
        CardRank cardRank = pattern.match(cards);
        assertNull(cardRank);
    }

    @Test
    void validateWrongCombinationWith4CardsInCombination() {
        CardRank combination = rK;
        Set<Card> cards = Set.of(
                Card.of(r2, CLUBS),
                Card.of(combination, CLUBS),
                Card.of(combination, DIAMONDS),
                Card.of(combination, SPADES),
                Card.of(combination, HEARTS));
        CardRank cardRank = pattern.match(cards);
        assertNull(cardRank);
    }
}