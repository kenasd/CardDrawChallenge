package com.kenasd.poker.model;

public enum HandCategory {
    FIVE_OF_KIND(10),
    STRAIGHT_FLUSH(9),
    FOUR_OF_KIND(8),
    FULL_HOUSE(7),
    FLUSH(6),
    STRAIGHT(5),
    THREE_OF_KIND(4),
    TWO_PAIR(3),
    ONE_PAIR(2),
    HIGH_CARD(1);

    private final int rank;

    HandCategory(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
}
