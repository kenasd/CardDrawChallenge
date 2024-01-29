package com.kenasd.poker.pattern;

import com.kenasd.poker.model.Card;
import com.kenasd.poker.model.CardRank;
import com.kenasd.poker.model.HandCategory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.kenasd.poker.model.CardRank.*;
import static com.kenasd.poker.model.CardSuit.*;
import static org.junit.jupiter.api.Assertions.*;

class FiveOfKindPatternTest {

    private final CardPattern pattern = new FiveOfKindPattern();

    @Test
    void validateHandCategory() {
        assertEquals(HandCategory.FIVE_OF_KIND, pattern.getCategory());
    }

    @Test
    void validateRightCombination() {
        // not sure that it's possible to wild card have a suit but...
        CardRank combination = rK;
        Set<Card> cards = Set.of(
                Card.of(rW, CLUBS),
                Card.of(combination, CLUBS),
                Card.of(combination, DIAMONDS),
                Card.of(combination, SPADES),
                Card.of(combination, HEARTS));
        CardRank cardRank = pattern.match(cards);
        assertNotNull(cardRank);
        assertEquals(combination, cardRank);
    }

    @Test
    void validateWrongCombinationWithoutWild() {
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

    @Test
    void validateWrongCombinationWithout4CardsInCombination() {
        CardRank combination = rK;
        Set<Card> cards = Set.of(
                Card.of(rW, CLUBS),
                Card.of(combination, CLUBS),
                Card.of(combination, DIAMONDS),
                Card.of(combination, SPADES),
                Card.of(r2, HEARTS));
        CardRank cardRank = pattern.match(cards);
        assertNull(cardRank);
    }

}