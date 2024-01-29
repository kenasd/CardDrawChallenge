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

class FlushPatternTest {

    private final CardPattern pattern = new FlushPattern();

    @Test
    void validateHandCategory() {
        assertEquals(HandCategory.FLUSH, pattern.getCategory());
    }

    @Test
    void validateRightCombination() {
        CardSuit combination = CLUBS;
        Set<Card> cards = Set.of(
                Card.of(r2, combination),
                Card.of(rK, combination),
                Card.of(rA, combination),
                Card.of(r6, combination),
                Card.of(r9, combination));
        CardRank cardRank = pattern.match(cards);
        assertNotNull(cardRank);
        assertEquals(rA, cardRank);
    }

    @Test
    void validateWrongCombinationWithoutOneSuit() {
        CardSuit combination = CLUBS;
        Set<Card> cards = Set.of(
                Card.of(r2, combination),
                Card.of(rK, combination),
                Card.of(rA, combination),
                Card.of(r6, combination),
                Card.of(r9, DIAMONDS));
        CardRank cardRank = pattern.match(cards);
        assertNull(cardRank);
    }
}