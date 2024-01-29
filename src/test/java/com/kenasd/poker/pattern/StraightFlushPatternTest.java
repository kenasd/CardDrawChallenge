package com.kenasd.poker.pattern;

import com.kenasd.poker.model.Card;
import com.kenasd.poker.model.CardRank;
import com.kenasd.poker.model.CardSuit;
import com.kenasd.poker.model.HandCategory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.kenasd.poker.model.CardRank.*;
import static com.kenasd.poker.model.CardSuit.CLUBS;
import static com.kenasd.poker.model.CardSuit.DIAMONDS;
import static org.junit.jupiter.api.Assertions.*;

class StraightFlushPatternTest {

    private final CardPattern pattern = new StraightFlushPattern();

    @Test
    void validateHandCategory() {
        assertEquals(HandCategory.STRAIGHT_FLUSH, pattern.getCategory());
    }

    @Test
    void validateRightHighCombination() {
        CardSuit combination = CLUBS;
        Set<Card> cards = Set.of(
                Card.of(rQ, combination),
                Card.of(rK, combination),
                Card.of(rA, combination),
                Card.of(rJ, combination),
                Card.of(r10, combination));
        CardRank cardRank = pattern.match(cards);
        assertNotNull(cardRank);
        assertEquals(rA, cardRank);
    }

    @Test
    void validateRightLowCombination() {
        CardSuit combination = CLUBS;
        Set<Card> cards = Set.of(
                Card.of(r2, combination),
                Card.of(r5, combination),
                Card.of(rA, combination),
                Card.of(r3, combination),
                Card.of(r4, combination));
        CardRank cardRank = pattern.match(cards);
        assertNotNull(cardRank);
        assertEquals(r5, cardRank);
    }

    @Test
    void validateWrongCombinationWithoutOneSuit() {
        CardSuit combination = CLUBS;
        Set<Card> cards = Set.of(
                Card.of(rQ, combination),
                Card.of(rK, combination),
                Card.of(rA, combination),
                Card.of(rJ, combination),
                Card.of(r10, DIAMONDS));
        CardRank cardRank = pattern.match(cards);
        assertNull(cardRank);
    }

    @Test
    void validateWrongCombinationWithoutSequentialHighOrder() {
        CardSuit combination = CLUBS;
        Set<Card> cards = Set.of(
                Card.of(rQ, combination),
                Card.of(rK, combination),
                Card.of(rA, combination),
                Card.of(rJ, combination),
                Card.of(r9, combination));
        CardRank cardRank = pattern.match(cards);
        assertNull(cardRank);
    }

    @Test
    void validateWrongCombinationWithoutSequentialLowOrder() {
        CardSuit combination = CLUBS;
        Set<Card> cards = Set.of(
                Card.of(r2, combination),
                Card.of(rK, combination),
                Card.of(rA, combination),
                Card.of(r3, combination),
                Card.of(r4, combination));
        CardRank cardRank = pattern.match(cards);
        assertNull(cardRank);
    }
}