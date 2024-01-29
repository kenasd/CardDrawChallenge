package com.kenasd.poker.engine;

import com.kenasd.poker.model.Card;
import com.kenasd.poker.model.Hand;
import com.kenasd.poker.model.HandRank;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.kenasd.poker.model.CardRank.*;
import static com.kenasd.poker.model.CardSuit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HandRankComparatorTest {

    private final HandRankComparator comparator = new HandRankComparator();
    private final HandRankCalculator calculator = new HandRankCalculator();

    @Test
    void validateStraightFlushWinsInFourOfKind() {
        HandRank fourOfKind = calculator.getRank(Hand.of(Set.of(
                Card.of(r2, CLUBS),
                Card.of(rK, CLUBS),
                Card.of(rK, DIAMONDS),
                Card.of(rK, SPADES),
                Card.of(rK, HEARTS))));
        HandRank straightFlush = calculator.getRank(Hand.of(Set.of(
                Card.of(rQ, CLUBS),
                Card.of(rK, CLUBS),
                Card.of(rA, CLUBS),
                Card.of(rJ, CLUBS),
                Card.of(r10, CLUBS))));
        int compare = comparator.compare(fourOfKind, straightFlush);
        assertEquals(compare, -1);
    }

    @Test
    void validateFourOfKind10LoseInFourOfKindKing() {
        HandRank fourOfKindK = calculator.getRank(Hand.of(Set.of(
                Card.of(r2, CLUBS),
                Card.of(rK, CLUBS),
                Card.of(rK, DIAMONDS),
                Card.of(rK, SPADES),
                Card.of(rK, HEARTS))));
        HandRank fourOfKindK10 = calculator.getRank(Hand.of(Set.of(
                Card.of(r2, CLUBS),
                Card.of(rQ, CLUBS),
                Card.of(rQ, DIAMONDS),
                Card.of(rQ, SPADES),
                Card.of(rQ, HEARTS))));
        int compare = comparator.compare(fourOfKindK, fourOfKindK10);
        assertEquals(compare, 1);
    }

    @Test
    void validateFullHouseWinInStraight() {
        HandRank straight = calculator.getRank(Hand.of(Set.of(
                Card.of(rQ, CLUBS),
                Card.of(rK, DIAMONDS),
                Card.of(rA, CLUBS),
                Card.of(rJ, CLUBS),
                Card.of(r10, CLUBS))));
        HandRank fullHouse = calculator.getRank(Hand.of(Set.of(
                Card.of(rK, CLUBS),
                Card.of(rK, DIAMONDS),
                Card.of(r10, DIAMONDS),
                Card.of(r10, SPADES),
                Card.of(r10, HEARTS))));
        int compare = comparator.compare(straight, fullHouse);
        assertEquals(compare, -1);
    }

    @Test
    void validateOnePairOf9EqualOnePairOf9() {
        HandRank straight = calculator.getRank(Hand.of(Set.of(
                Card.of(r2, CLUBS),
                Card.of(rK, CLUBS),
                Card.of(r9, CLUBS),
                Card.of(r6, CLUBS),
                Card.of(r9, DIAMONDS))));
        HandRank fullHouse = calculator.getRank(Hand.of(Set.of(
                Card.of(r2, SPADES),
                Card.of(rK, SPADES),
                Card.of(r9, SPADES),
                Card.of(r6, SPADES),
                Card.of(r9, HEARTS))));
        int compare = comparator.compare(straight, fullHouse);
        assertEquals(compare, 0);
    }
}