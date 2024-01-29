package com.kenasd.poker.pattern;

import com.kenasd.poker.model.Card;
import com.kenasd.poker.model.CardRank;
import com.kenasd.poker.model.HandCategory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.kenasd.poker.model.CardRank.*;
import static com.kenasd.poker.model.CardSuit.*;
import static org.junit.jupiter.api.Assertions.*;

class FullHousePatternTest {

    private final CardPattern pattern = new FullHousePattern();

    @Test
    void validateHandCategory() {
        assertEquals(HandCategory.FULL_HOUSE, pattern.getCategory());
    }

    @Test
    void validateRightCombination() {
        CardRank duplet = rK;
        CardRank triplet = r4;
        Set<Card> cards = Set.of(
                Card.of(duplet, CLUBS),
                Card.of(duplet, DIAMONDS),
                Card.of(triplet, DIAMONDS),
                Card.of(triplet, SPADES),
                Card.of(triplet, HEARTS));
        CardRank cardRank = pattern.match(cards);
        assertNotNull(cardRank);
        assertEquals(triplet, cardRank);
    }

    @Test
    void validateWrongCombinationWithout4CardsInCombination() {
        CardRank duplet = rK;
        CardRank duplet2 = r4;
        Set<Card> cards = Set.of(
                Card.of(duplet, CLUBS),
                Card.of(duplet, DIAMONDS),
                Card.of(duplet2, DIAMONDS),
                Card.of(duplet2, SPADES),
                Card.of(r3, HEARTS));
        CardRank cardRank = pattern.match(cards);
        assertNull(cardRank);
    }

}