package com.ottt.ponker;

import java.util.ArrayList;
import java.util.Collections;

public class Hand implements Comparable<Hand>{
    Player owner = new Player("Player");
    ArrayList<Card> cards;

    public Hand(Player owner) {
        this.owner = owner;
    }

    public Hand (ArrayList<Card> cards) {
        this.cards = cards;
    }

    //check for pair then then two pair, then if true pass both pair tier into three of a kind
    //If pair and three of a kind are true, full house is true
    //sort list, check for straight and flush.
    //If two pair == true and tier of both is the same, four of a kind
    //Four of a kind can be done in a nested if statement if the two tiers are true in pair method
    //Checking for pairs returns tier
    //


    /*
    Check for pair
    after checking for pair, pass in remaining cards and compare to previous pair check
    check for two pair -- two pair if results above are non-equal tiers
    check for four of a kind -- four of kind if results are equal tiers
    check for three of kind -- if remaining cards contain only one of the above tier, three pair
    check for flush  -- all suits are equal
    check for straight -- sort hand, all cards are incremental
    check for straight flush -- above results
    check for high card
     */


    Tier checkForPair(ArrayList<Card> cards) {
        ArrayList<Card> CardsToCheck = new ArrayList<Card>();
        CardsToCheck.addAll(cards);
        Collections.sort(CardsToCheck);

        System.out.println(CardsToCheck);
        Tier previousTier = Tier.INVALID;
        for (Card c : CardsToCheck) {
            if (previousTier.equals(Tier.INVALID)) {
                previousTier = c.getTier();
                System.out.println("Set initial tier to " + previousTier);
                continue;
            }
            System.out.println("Comparing " + c.getTier() + " to " + previousTier);
            if (c.getTier().equals(previousTier)) {

                return c.getTier();
            }
            previousTier = c.getTier();
        }
        return Tier.INVALID;
    }


    public int compareTo(Hand that) {
        HandValue this_hv = getHandValue(this);
        HandValue that_hv = getHandValue(that);

        this_hv.ordinal();

        return 0;
    }

    HandValue getHandValue(Hand h) {
        HandValue hv = HandValue.HIGH_CARD;
        Tier pairTier = checkForPair(h.cards);
        System.out.println("Pair: " + pairTier);
        if (!pairTier.equals(Tier.INVALID)) { //has has at least one pair

            ArrayList<Tier> twoPairTiers = checkForTwoPair(h, pairTier); //Check for two pair
            System.out.println("Two-pair: " + twoPairTiers);

            Tier fourOfKindTier = checkForFourOfKind(twoPairTiers); //Check for four of kind
            System.out.println("Four of a kind: " + fourOfKindTier);

            Tier threeOfKindTier = checkForThreeOfKind(h, pairTier);
            System.out.println("Three of a kind: " + threeOfKindTier);

            Tier fullHouseTier = checkForFullHouse(pairTier, threeOfKindTier, h);
            System.out.println("Full house: " + fullHouseTier);

        }
        else {
            boolean hasFlush = checkForFlush();
            boolean hasStraight = checkForStraight();
            if ( hasFlush && hasStraight) { hv = HandValue.STRAIGHT_FLUSH; }
            else if (hasFlush) { hv = HandValue.FLUSH;}
            else if (hasStraight) {hv = HandValue.STRAIGHT;}

        }
        return HandValue.HIGH_CARD;
    }

    private Tier checkForThreeOfKind(Hand h, Tier pairTier) {
        ArrayList<Card> pair_p = removePair(h, pairTier);

        for (Card c:pair_p) {
            if (c.getTier().equals(pairTier))
                    return pairTier;
        }
        return Tier.INVALID;
    }

    private ArrayList<Card> removePair(Hand h, Tier pairTier) {
        ArrayList<Card> pair_p = new ArrayList<Card>();
        pair_p.addAll(h.cards);
        Card emptyCard = new Card(Tier.INVALID, Suit.INVALID);

        int i = 0;
        for (int j = 0; j < h.cards.size(); j++) {
            if(pair_p.get(j).getTier().equals(pairTier)) { pair_p.set(j, emptyCard); i++; }
            if (i > 1) break;
        }
        pair_p.removeAll(Collections.singleton(emptyCard));

        System.out.println("Hand: " + h.cards);
        System.out.println("Hand - pair removed: " + pair_p);

        return pair_p;
    }

    private Tier checkForFullHouse(Tier pairTier, Tier threeOfKindTier, Hand h) {
        return Tier.INVALID;
    }

    private Tier checkForFourOfKind(ArrayList<Tier> twoPairTiers) {
        if (twoPairTiers.get(0).equals(twoPairTiers.get(1))) {
            return twoPairTiers.get(0).ordinal() > twoPairTiers.get(1).ordinal() ? twoPairTiers.get(0) : twoPairTiers.get(1);
        }
        return Tier.INVALID;
    }

    private ArrayList<Tier> checkForTwoPair(Hand h, Tier pairTier) {
        ArrayList<Card> pair_p = removePair(h, pairTier); //This will be all of the hand, excluding your pair
        Tier pairTier2 = checkForPair(pair_p);  //check for pair again
        ArrayList<Tier> twoPairTiers = new ArrayList<Tier>();
        twoPairTiers.add(pairTier);
        twoPairTiers.add(pairTier2);
        return twoPairTiers;
    }

    private boolean checkForStraight() {
        return false;
    }

    private boolean checkForFlush() {
        return false;
    }


}
