package com.kenasd.poker.engine;

import com.kenasd.poker.model.HandRank;

import java.util.Comparator;

public class HandRankComparator implements Comparator<HandRank> {

    @Override
    public int compare(HandRank o1, HandRank o2) {
        int categoryComparing = Integer.compare(o1.getCategory().getRank(), o2.getCategory().getRank());
        return categoryComparing != 0
                ? categoryComparing
                : Integer.compare(o1.getTopCardRank().getScore(), o2.getTopCardRank().getScore());
    }
}
