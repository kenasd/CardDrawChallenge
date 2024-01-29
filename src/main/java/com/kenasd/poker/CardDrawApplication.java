package com.kenasd.poker;

import com.kenasd.poker.engine.HandParser;
import com.kenasd.poker.engine.HandRankCalculator;
import com.kenasd.poker.engine.HandRankComparator;
import com.kenasd.poker.model.Hand;
import com.kenasd.poker.model.HandRank;

import java.util.Scanner;

public class CardDrawApplication {

    // To simplify run only one comparing
    public static void main(String[] args) {
        System.out.println("Hello to \"5 Card Draw\" coding challenge!");
        HandParser parser = new HandParser();
        HandRankCalculator calculator = new HandRankCalculator();
        HandRankComparator comparator = new HandRankComparator();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input 5 card for first hand:");
        String firstCards = scanner.nextLine();
        Hand firstHand = parser.parse(firstCards);
        System.out.println(firstHand);

        System.out.println("Input 5 card for second hand:");
        String secondCards = scanner.nextLine();
        Hand secondHand = parser.parse(secondCards);
        System.out.println(secondHand);

        HandRank firstRank = calculator.getRank(firstHand);
        System.out.printf("First hand has combination: '%s' and highest card: '%s'%n",
                firstRank.getCategory(), firstRank.getTopCardRank());

        HandRank secondRank = calculator.getRank(secondHand);
        System.out.printf("Second hand has combination: '%s' and highest card: '%s'%n",
                secondRank.getCategory(), secondRank.getTopCardRank());

        int compare = comparator.compare(firstRank, secondRank);
        if (compare != 0) {
            System.out.printf("%s hand is the winner!", (compare < 0 ? "Second" : "First"));
        } else {
            System.out.println("Both hands have equal combinations!");
        }


    }

}