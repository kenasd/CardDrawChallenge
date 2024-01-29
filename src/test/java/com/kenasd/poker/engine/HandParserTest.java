package com.kenasd.poker.engine;

import com.kenasd.poker.model.Card;
import com.kenasd.poker.model.Hand;
import com.kenasd.poker.pattern.CardPattern;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandParserTest {

    private final HandParser parser = new HandParser();

    @Test
    void validateRightInput() {
        Hand hand = parser.parse("SA-H7-D4-C10-SJ");
        assertNotNull(hand);
        assertNotNull(hand.getCards());
        assertEquals(CardPattern.CARD_COUNT, hand.getCards().size());
        for (Card card : hand.getCards()) {
            assertNotNull(card);
            assertNotNull(card.getRank());
            assertNotNull(card.getSuit());
        }
    }

    @Test
    void validateWrongInputWhenCardsIsEmpty() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> parser.parse(""));
        assertEquals("Cards string cannot be null or empty.", exception.getMessage());
    }

    @Test
    void validateWrongInputWhenCardsLessThanExpected() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> parser.parse("SA-H7-D4-C10"));
        assertEquals(String.format("Count of cards must be %s.", CardPattern.CARD_COUNT), exception.getMessage());
    }

    @Test
    void validateWrongInputWhenCardFormatWrong() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> parser.parse("SA-H7-D4-C10-S"));
        assertEquals(String.format("Wrong card size: %s.", 'S'), exception.getMessage());
    }

    @Test
    void validateWrongInputWhenCardSuitIsWrong() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> parser.parse("SA-H7-D4-C10-G9"));
        assertEquals(String.format("Wrong card suit: %s.", "G9"), exception.getMessage());
    }

    @Test
    void validateWrongInputWhenCardRankIsWrong() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> parser.parse("SA-H7-D4-C10-S11"));
        assertEquals(String.format("Wrong card rank: %s.", "S11"), exception.getMessage());
    }

    @Test
    void validateWrongInputWhenCardsHasDuplicates() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> parser.parse("SA-H7-D4-C10-C10"));
        assertEquals("Hand must have 5 unique cards.", exception.getMessage());
    }
}