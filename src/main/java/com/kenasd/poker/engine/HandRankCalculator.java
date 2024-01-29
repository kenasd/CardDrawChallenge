package com.kenasd.poker.engine;

import com.kenasd.poker.model.CardRank;
import com.kenasd.poker.model.Hand;
import com.kenasd.poker.model.HandRank;
import com.kenasd.poker.pattern.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HandRankCalculator {

    private static final Comparator<CardPattern> cardPatternComparator = Comparator.comparing(pattern -> pattern.getCategory().getRank());
    private final List<CardPattern> cardPatterns = initPatterns();
    private final CardPattern defaultPattern = new HighCardPattern();

    public HandRank getRank(Hand hand) {
        assert hand != null;
        for (CardPattern pattern : cardPatterns) {
            CardRank topCardRank = pattern.match(hand.getCards());
            if (topCardRank != null) {
                return HandRank.of(hand, pattern.getCategory(), topCardRank);
            }

        }
        return HandRank.of(hand, defaultPattern.getCategory(), defaultPattern.match(hand.getCards()));
    }

    // Better to use some injection mechanism here to add new pattern on fly
    private List<CardPattern> initPatterns() {
        List<CardPattern> patterns = new ArrayList<>();
        patterns.add(new FiveOfKindPattern());
        patterns.add(new FlushPattern());
        patterns.add(new FourOfKindPattern());
        patterns.add(new FullHousePattern());
        patterns.add(new HighCardPattern());
        patterns.add(new OnePairPattern());
        patterns.add(new StraightFlushPattern());
        patterns.add(new StraightPattern());
        patterns.add(new ThreeOfKindPattern());
        patterns.add(new TwoPairPattern());
        patterns.sort(cardPatternComparator.reversed());
        return patterns;
    }
}
