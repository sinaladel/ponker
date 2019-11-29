package com.ottt.ponker;

import java.util.ArrayList;

public class Hand implements Comparable<Hand>{
    Player owner;

    public boolean isPair() {

        boolean isPair;
        return isPair;
    }
    public boolean threeOfKind() {
        boolean threeKind;
        return threeKind;
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
        return Tier.INVALID;
    }

    public boolean
    @Override
    public int compareTo(Hand o) {

    }



}
