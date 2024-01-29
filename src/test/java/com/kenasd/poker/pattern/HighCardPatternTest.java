package com.kenasd.poker.pattern;

import com.kenasd.poker.model.Card;
import com.kenasd.poker.model.CardRank;
import com.kenasd.poker.model.HandCategory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.kenasd.poker.model.CardRank.*;
import static com.kenasd.poker.model.CardSuit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HighCardPatternTest {
    private final CardPattern pattern = new HighCardPattern();

    @Test
    void validateHandCategory() {
        assertEquals(HandCategory.HIGH_CARD, pattern.getCategory());
    }

    @Test
    void validateRightCombination() {
        Set<Card> cards = Set.of(
                Card.of(r2, CLUBS),
                Card.of(r5, CLUBS),
                Card.of(rJ, DIAMONDS),
                Card.of(r5, SPADES),
                Card.of(rJ, HEARTS));
        CardRank cardRank = pattern.match(cards);
        assertNotNull(cardRank);
        assertEquals(rJ, cardRank);
    }
}