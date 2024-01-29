package com.kenasd.poker.pattern;

import com.kenasd.poker.model.Card;
import com.kenasd.poker.model.CardRank;
import com.kenasd.poker.model.HandCategory;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public interface CardPattern {
    int CARD_COUNT = 5;
    int DUPLET_SIZE = 2;
    CardRank WILD_CARD = CardRank.rW;
    Comparator<CardRank> CARD_RANK_COMPARATOR = Comparator.comparingInt(CardRank::getScore).reversed();

    HandCategory getCategory();

    CardRank match(Set<Card> cards);

    default boolean hasWild(Set<Card> cards) {
        assert cards != null;
        return cards.stream()
                .map(Card::getRank)
                .anyMatch(rank -> rank == WILD_CARD);
    }

    default boolean hasSameSuit(Set<Card> cards) {
        assert cards != null;
        return cards.stream()
                .collect(Collectors.groupingBy(Card::getSuit, Collectors.counting()))
                .values().stream()
                .max(Comparator.comparingLong(l -> l))
                .orElse(0L) == CARD_COUNT;
    }

    default CardRank getTopCardInStraightOrder(Set<Card> cards) {
        assert cards != null && cards.size() == CARD_COUNT;
        List<CardRank> sortedList = cards.stream()
                .map(Card::getRank)
                .sorted(CARD_RANK_COMPARATOR)
                .collect(Collectors.toList());
        // check low straight
        int startCard = 1;
        if (sortedList.get(0) == CardRank.rA && sortedList.get(CARD_COUNT - 1) == CardRank.r2) {
            startCard++;
        }
        // check high straight
        for (int index = startCard; index < CARD_COUNT; index++) {
            if (sortedList.get(index - 1).getScore() - sortedList.get(index).getScore() != 1) {
                return null;
            }
        }
        return startCard == 1 ? sortedList.get(0) : sortedList.get(1);
    }

    default CardRank getTopCard(Set<Card> cards) {
        assert cards != null;
        List<CardRank> sortedList = cards.stream()
                .map(Card::getRank)
                .sorted(CARD_RANK_COMPARATOR)
                .collect(Collectors.toList());
        return sortedList.get(0);
    }

    default CardRank getCardByCount(Set<Card> cards, int cardCount) {
        assert cards != null;
        return cards.stream()
                .filter(card -> card.getRank() != WILD_CARD)
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == cardCount)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    default CardRank getTopCardFromTwoPair(Set<Card> cards) {
        assert cards != null;
        List<CardRank> duplets = cards.stream()
                .filter(card -> card.getRank() != WILD_CARD)
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == DUPLET_SIZE)
                .map(Map.Entry::getKey)
                .sorted(CARD_RANK_COMPARATOR)
                .collect(Collectors.toList());
        return duplets.size() == DUPLET_SIZE ? duplets.get(0) : null;
    }
}
