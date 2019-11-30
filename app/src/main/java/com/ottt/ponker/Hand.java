package com.ottt.ponker;

import java.util.ArrayList;
import java.util.Collections;

public class Hand implements Comparable<Hand>{
    Player owner = new Player("Player");
    ArrayList<Card> cards;
    Tier highCard = Tier.INVALID;

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
        ArrayList<Card> CardsToCheck = new ArrayList<Card>(cards);
        Collections.sort(CardsToCheck);

        Tier previousTier = Tier.INVALID;
        for (Card c : CardsToCheck) {
            if (previousTier.equals(Tier.INVALID)) {
                previousTier = c.getTier();
                continue;
            }
            if (c.getTier().equals(previousTier)) {

                return c.getTier();
            }
            previousTier = c.getTier();
        }
        return Tier.INVALID;
    }


    public int compareTo(Hand that) {
        HandValue this_hv = this.getHandValue();
        HandValue that_hv = that.getHandValue();
        System.out.println("First hand: " + this_hv + " | Second Hand: " + that_hv);
        if (this_hv.equals(that_hv)) {
            return this.highCard.compareTo(that.highCard);
        }
        else return this_hv.compareTo(that_hv) * -1;
    }

    HandValue getHandValue() {
        Collections.sort(cards);
        System.out.println(cards);
        HandValue hv = HandValue.HIGH_CARD;
        Tier pairTier = checkForPair(cards);
        System.out.println("Pair: " + pairTier);
        if (!pairTier.equals(Tier.INVALID)) { //has has at least one pair

            ArrayList<Tier> twoPairTiers = checkForTwoPair(pairTier); //Check for two pair
            System.out.println("Two-pair: " + twoPairTiers);

            Tier fourOfKindTier = checkForFourOfKind(twoPairTiers); //Check for four of kind

            System.out.println("Four of a kind: " + fourOfKindTier);

            Tier threeOfKindTier = checkForThreeOfKind(twoPairTiers);
            System.out.println("Three of a kind: " + threeOfKindTier);

            Tier fullHouseTier = checkForFullHouse(twoPairTiers, threeOfKindTier);
            System.out.println("Full house: " + fullHouseTier);
            if (!fourOfKindTier.equals(Tier.INVALID)) {hv = HandValue.FOUR_OF_A_KIND; highCard = fourOfKindTier;}
            else if (!fullHouseTier.equals(Tier.INVALID)) {hv = HandValue.FULL_HOUSE; highCard = fullHouseTier;}
            else if (!threeOfKindTier.equals(Tier.INVALID)) {hv = HandValue.THREE_OF_A_KIND; highCard = threeOfKindTier;}
            else if (!twoPairTiers.equals(Tier.INVALID)) {hv = HandValue.TWO_PAIR; highCard = twoPairTiers.get(0);}
            else {hv = HandValue.PAIR; highCard = pairTier;}
        }
        else {
            Tier flushTier = checkForFlush();
            System.out.println("Flush: " + flushTier);
            Tier straightTier = checkForStraight();
            System.out.println("Straight: " + straightTier);

            if (!flushTier.equals(Tier.INVALID) && !straightTier.equals(Tier.INVALID)) {
                hv = HandValue.STRAIGHT_FLUSH;
                highCard = flushTier;
            }
            else if (!flushTier.equals(Tier.INVALID)) {
                hv = HandValue.FLUSH;
                highCard = flushTier;
            }
            else if (!straightTier.equals(Tier.INVALID)) {
                hv = HandValue.STRAIGHT;
                highCard = straightTier;
            }

            //if ( ) { hv = HandValue.STRAIGHT_FLUSH; }
            //else if (hasFlush) { hv = HandValue.FLUSH;}
            //else if (hasStraight) {hv = HandValue.STRAIGHT;}

        }
        return hv;
    }

    private Tier checkForThreeOfKind(ArrayList<Tier> twopairTiers) {

        for (int i = 0; i < 2; i++) {
            if (twopairTiers.get(i).equals(Tier.INVALID)) { continue; }
            ArrayList<Card> pair_p = removePair(twopairTiers.get(i));
            System.out.println(pair_p);
            for (Card c : pair_p) {
                if (c.getTier().equals(twopairTiers.get(i)))
                    return twopairTiers.get(i);
            }
        }
        return Tier.INVALID;
    }

    private ArrayList<Card> removePair(Tier pairTier) {
        ArrayList<Card> pair_p = new ArrayList<>(cards);
        Card emptyCard = new Card(Tier.INVALID, Suit.INVALID);

        int i = 0;
        for (int j = 0; j < cards.size(); j++) {
            if(pair_p.get(j).getTier().equals(pairTier)) { pair_p.set(j, emptyCard); i++; }
            if (i > 1) break;
        }
        pair_p.removeAll(Collections.singleton(emptyCard));

        return pair_p;
    }

    private Tier checkForFullHouse(ArrayList<Tier> twoPairTiers, Tier threeOfKindTier) {
        for (int i = 0; i < 2; i++) {
            if (twoPairTiers.get(i).equals(Tier.INVALID)) {break;}
            if (twoPairTiers.get(i).equals(threeOfKindTier)) {continue;}
            if (!twoPairTiers.get(i).equals(Tier.INVALID) && !threeOfKindTier.equals(Tier.INVALID)) {
                return twoPairTiers.get(i).ordinal() < threeOfKindTier.ordinal() ? twoPairTiers.get(i) : threeOfKindTier;
            }
        }
        return Tier.INVALID;
    }

    private Tier checkForFourOfKind(ArrayList<Tier> twoPairTiers) {
        if (twoPairTiers.get(0).equals(twoPairTiers.get(1))) {
            return twoPairTiers.get(0);
        }
        return Tier.INVALID;
    }

    private ArrayList<Tier> checkForTwoPair(Tier pairTier) {
        ArrayList<Card> pair_p = removePair(pairTier); //This will be all of the hand, excluding your pair
        Tier pairTier2 = checkForPair(pair_p);  //check for pair again
        ArrayList<Tier> twoPairTiers = new ArrayList<Tier>();
        twoPairTiers.add(pairTier.ordinal() < pairTier2.ordinal() ? pairTier : pairTier2);
        twoPairTiers.add(pairTier.ordinal() < pairTier2.ordinal() ? pairTier2 : pairTier);
        return twoPairTiers;
    }

    private Tier checkForStraight() {
        Tier previousTier = Tier.INVALID;
        for (Card c:cards) {
            if (previousTier.equals(Tier.INVALID)) {
                previousTier = c.getTier();
                continue;
            }
            if (previousTier.ordinal() - c.getTier().ordinal() != 1) {
                return Tier.INVALID;
            }
            previousTier = c.getTier();
        }
        return cards.get(0).getTier();
    }

    private Tier checkForFlush() {
        Suit previousSuit = Suit.INVALID;
        for (Card c: cards) {
            if (previousSuit.equals(Suit.INVALID)) {
                previousSuit = c.getSuit();
                continue;
            }
            if (!c.getSuit().equals(previousSuit)) {return Tier.INVALID;}
        }
        return cards.get(0).getTier();
    }


}
