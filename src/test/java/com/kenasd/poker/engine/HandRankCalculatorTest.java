package com.kenasd.poker.engine;

import com.kenasd.poker.model.*;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.kenasd.poker.model.CardRank.*;
import static com.kenasd.poker.model.CardSuit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HandRankCalculatorTest {

    private final HandRankCalculator calculator = new HandRankCalculator();

    @Test
    void validateHandRankOfFourOfKind() {
        Hand hand = Hand.of(Set.of(
                Card.of(r2, CLUBS),
                Card.of(rK, CLUBS),
                Card.of(rK, DIAMONDS),
                Card.of(rK, SPADES),
                Card.of(rK, HEARTS)));
        HandRank fourOfKind = calculator.getRank(hand);
        assertEquals(hand, fourOfKind.getHand());
        assertEquals(HandCategory.FOUR_OF_KIND, fourOfKind.getCategory());
        assertEquals(CardRank.rK, fourOfKind.getTopCardRank());
    }

    @Test
    void validateHandRankOfFlush() {
        CardSuit combination = CLUBS;
        Hand hand = Hand.of(Set.of(
                Card.of(r2, combination),
                Card.of(rK, combination),
                Card.of(rA, combination),
                Card.of(r6, combination),
                Card.of(r9, combination)));
        HandRank fourOfKind = calculator.getRank(hand);
        assertEquals(hand, fourOfKind.getHand());
        assertEquals(HandCategory.FLUSH, fourOfKind.getCategory());
        assertEquals(CardRank.rA, fourOfKind.getTopCardRank());
    }

    @Test
    void validateHandRankOfHighCard() {
        Hand hand = Hand.of(Set.of(
                Card.of(r2, CLUBS),
                Card.of(r4, CLUBS),
                Card.of(r7, DIAMONDS),
                Card.of(r5, SPADES),
                Card.of(rJ, HEARTS)));
        HandRank fourOfKind = calculator.getRank(hand);
        assertEquals(hand, fourOfKind.getHand());
        assertEquals(HandCategory.HIGH_CARD, fourOfKind.getCategory());
        assertEquals(CardRank.rJ, fourOfKind.getTopCardRank());
    }

}