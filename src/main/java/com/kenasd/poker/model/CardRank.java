package com.kenasd.poker.model;

import java.util.HashMap;
import java.util.Map;

public enum CardRank {
    rW("W", 15),
    rA("A", 14),
    rK("K", 13),
    rQ("Q", 12),
    rJ("J", 11),
    r10("10", 10),
    r9("9", 9),
    r8("8", 8),
    r7("7", 7),
    r6("6", 6),
    r5("5", 5),
    r4("4", 4),
    r3("3", 3),
    r2("2", 2);
    private static final Map<String, CardRank> CARD_RANK_MAP;

    static {
        Map<String, CardRank> rankMap = new HashMap<>(CardRank.values().length);
        for (CardRank cardRank : CardRank.values()) {
            rankMap.put(cardRank.getValue(), cardRank);
        }
        CARD_RANK_MAP = Map.copyOf(rankMap); //immutable
    }

    private final String value;
    private final int score;

    CardRank(String value, int score) {
        this.value = value;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getValue() {
        return value;
    }

    public static CardRank of(String card) {
        return CARD_RANK_MAP.get(card);
    }
}
