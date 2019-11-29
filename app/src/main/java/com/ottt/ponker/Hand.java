package com.ottt.ponker;

import java.util.ArrayList;

public class Hand implements Comparable<Hand>{
    Player owner;
    ArrayList<Card> cards;

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
        if (!pairTier.equals(Tier.INVALID)) { //has has at least one pair

            Tier twoPairTier = checkForTwoPair(h, pairTier); //Check for two pair

            Tier fourOfKindTier = checkForFourOfKind(pairTier, twoPairTier); //Check for four of kind

            Tier threeOfKindTier = checkForThreeOfKind(h, pairTier);



            if (fourOfKindTier.equals(Tier.INVALID) && !twoPairTier.equals(Tier.INVALID)) { //The pairs were not identical, but a pair was found
                hv = HandValue.TWO_PAIR; //could have full house
                Tier FullHouseTier = checkForFullHouse(pairTier, twoPairTier, h);
            }

            else {
                for (Card c:pair_p) {
                    if (c.getTier().equals(pairTier)) {
                         hv = HandValue.THREE_OF_A_KIND; //can only have three of a kind
                    }

                }
            }

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

    }

    private Tier checkForFullHouse(Tier pairTier, Tier twoPairTier, Hand h) {

    }

    private Tier checkForFourOfKind(Tier pairTier, Tier twoPairTier) {
        if (pairTier.equals(twoPairTier)) {
            return pairTier.ordinal() > twoPairTier.ordinal() ? pairTier : twoPairTier;
        }
        return Tier.INVALID;
    }

    private Tier checkForTwoPair(Hand h, Tier pairTier) {
        ArrayList<Card> pair_p = h.cards; //This will be all of the hand, excluding your pair
        int i = 0;
        for (Card c:h.cards) { //Remove only two cards of the tier found to be a pair
            if (c.getTier().equals(pairTier)) {
                pair_p.remove(c);
                i++;
            }
            if (i > 1) {break;}
        }
        return checkForPair(pair_p); //check for pair again
    }

    private boolean checkForStraight() {
    }

    private boolean checkForFlush() {
    }


}
