package com.ottt.ponker;

import com.ottt.ponker.enums.HandValue;
import com.ottt.ponker.enums.Suit;
import com.ottt.ponker.enums.Tier;

import java.util.ArrayList;
import java.util.Collections;

public class Hand implements Comparable<Hand> {
    Player owner = null;
    ArrayList<Card> cards;
    Tier highCard = Tier.INVALID;

    public Hand(Player owner, ArrayList<Card> handContent) {
        this.owner = owner;
        this.cards = handContent;
    }

    public Hand(Player owner) {
        this.owner = owner;
        this.cards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            cards.add(Game.deck.nextCard());
        }
    }

    public void removeAll(ArrayList<Card> cardsToRemove) {
        cards.removeAll(Collections.singleton(cardsToRemove));
    }

    public void addCard() {
        cards.add(Game.deck.nextCard());
    }

    public int compareTo(Hand o) {
        HandValue hv = this.getHandValue();
        HandValue o_hv = o.getHandValue();

        System.out.println("First hand: " + hv + " | Second Hand: " + hv);

        //compare the value of the hand, compare the high card if the hand value is equal
        if (hv.equals(o_hv)) {
            return this.highCard.compareTo(o.highCard);
        } else return hv.compareTo(o_hv) * -1; //compare in reverse order
    }

    HandValue getHandValue() {
        Collections.sort(cards); //Cards should be in descending order for determining hand value
        System.out.println(cards);

        Tier pairTier = checkForPair(cards); //Checks for a pair, then returns the tier of the paired cards
        System.out.println("Pair: " + pairTier);

        if (!pairTier.equals(Tier.INVALID)) { //has has at least one pair, all hands except flush and straight are possible

            ArrayList<Tier> twoPairTiers = checkForTwoPair(pairTier);
            System.out.println("Two-pair: " + twoPairTiers);

            Tier fourOfKindTier = checkForFourOfKind(twoPairTiers);
            System.out.println("Four of a kind: " + fourOfKindTier);

            Tier threeOfKindTier = checkForThreeOfKind(twoPairTiers);
            System.out.println("Three of a kind: " + threeOfKindTier);

            Tier fullHouseTier = checkForFullHouse(twoPairTiers, threeOfKindTier);
            System.out.println("Full house: " + fullHouseTier);


            if (!fourOfKindTier.equals(Tier.INVALID)) {
                highCard = fourOfKindTier;
                return HandValue.FOUR_OF_A_KIND; //Four of kind - no other hands are possible: return
            } else if (!fullHouseTier.equals(Tier.INVALID)) {
                highCard = fullHouseTier;
                return HandValue.FULL_HOUSE; //Full house - no other hands are possible: return
            } else if (!threeOfKindTier.equals(Tier.INVALID)) {
                highCard = threeOfKindTier;
                return HandValue.THREE_OF_A_KIND; //Already checked for full house - no higher hands are possible: return
            } else if (!twoPairTiers.equals(Tier.INVALID)) {
                highCard = twoPairTiers.get(0);
                return HandValue.TWO_PAIR; //Already checked for higher value hands: return
            } else {
                highCard = pairTier;
                return HandValue.PAIR;
            } //All higher value hands have been checked for, must be pair: return
        } else { //Does not have any matching cards flush and straight are possible
            Tier flushTier = checkForFlush();
            System.out.println("Flush: " + flushTier);

            Tier straightTier = checkForStraight();
            System.out.println("Straight: " + straightTier);

            if (!flushTier.equals(Tier.INVALID) && !straightTier.equals(Tier.INVALID)) {
                highCard = flushTier;
                return HandValue.STRAIGHT_FLUSH; //Has straight flush, no other hands are possible
            } else if (!flushTier.equals(Tier.INVALID)) {
                highCard = flushTier;
                return HandValue.FLUSH; //Has flush only: return
            } else if (!straightTier.equals(Tier.INVALID)) {
                highCard = straightTier;
                return HandValue.STRAIGHT; //Has straight only: return
            }
        }
        highCard = cards.get(0).getTier();
        return HandValue.HIGH_CARD; //No higher value hand was found, must be high card
    }


    private Tier checkForPair(ArrayList<Card> cards) {
        Collections.sort(cards); //Make sure cards are in descending order

        Tier previousTier = Tier.INVALID;
        //loop through the ordered list to determine if any cards are of the same tier
        for (Card c : cards) {
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


    private ArrayList<Card> removePair(Tier pairTier) {
        ArrayList<Card> pair_p = new ArrayList<>(cards); //do not modify the original list
        Card emptyCard = new Card(Tier.INVALID, Suit.INVALID);

        int i = 0;
        for (int j = 0; j < cards.size(); j++) { //loop through the list and replace only two cards of matching tier with empty cards
            if (pair_p.get(j).getTier().equals(pairTier)) {
                pair_p.set(j, emptyCard);
                i++;
            }
            if (i > 1) break;
        }
        pair_p.removeAll(Collections.singleton(emptyCard)); //remove all empty cards

        return pair_p;
    }

    private ArrayList<Tier> checkForTwoPair(Tier pairTier) {
        ArrayList<Card> pair_p = removePair(pairTier); //This will be all of the hand, excluding your pair
        Tier pairTier2 = checkForPair(pair_p);  //check for pair again

        //return list of tiers in the two-pair in descending order
        ArrayList<Tier> twoPairTiers = new ArrayList<Tier>();
        twoPairTiers.add(pairTier.ordinal() < pairTier2.ordinal() ? pairTier : pairTier2);
        twoPairTiers.add(pairTier.ordinal() < pairTier2.ordinal() ? pairTier2 : pairTier);
        return twoPairTiers;
    }


    private Tier checkForFourOfKind(ArrayList<Tier> twoPairTiers) {
        //if both tiers of the two pair are equal, you have a four of a kind
        if (twoPairTiers.get(0).equals(twoPairTiers.get(1))) {
            return twoPairTiers.get(0);
        }
        return Tier.INVALID;
    }


    private Tier checkForThreeOfKind(ArrayList<Tier> twoPairTiers) {

        for (int i = 0; i < 2; i++) { //Check for three of kind on any previously found pair
            if (twoPairTiers.get(i).equals(Tier.INVALID)) {
                continue;
            }
            ArrayList<Card> pair_p = removePair(twoPairTiers.get(i));
            System.out.println(pair_p);
            for (Card c : pair_p) {
                //if there is one card left of the same tier of a pair after removing that pair, you have a three of a kind
                if (c.getTier().equals(twoPairTiers.get(i)))
                    return twoPairTiers.get(i);
            }
        }
        return Tier.INVALID;
    }


    private Tier checkForFullHouse(ArrayList<Tier> twoPairTiers, Tier threeOfKindTier) {
        for (int i = 0; i < 2; i++) {
            if (twoPairTiers.get(i).equals(Tier.INVALID)) {
                break;
            } //if there is no pair,there cannot be a full house
            if (twoPairTiers.get(i).equals(threeOfKindTier)) {
                continue;
            } //if the first pair is equal to the three of a kind, continue to the second pair
            if (!twoPairTiers.get(i).equals(Tier.INVALID) && !threeOfKindTier.equals(Tier.INVALID)) {
                //if there is a unique pair and three of kind in the hand, you have a full house
                return twoPairTiers.get(i).ordinal() < threeOfKindTier.ordinal() ? twoPairTiers.get(i) : threeOfKindTier;
            }
        }
        return Tier.INVALID;
    }


    private Tier checkForStraight() {
        //loop through the entire hand to check if each subsequent tier is different by exactly 1
        Tier previousTier = Tier.INVALID;
        for (Card c : cards) {
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
        //loop through the entire hand to check if each suit is the same
        Suit previousSuit = Suit.INVALID;
        for (Card c : cards) {
            if (previousSuit.equals(Suit.INVALID)) {
                previousSuit = c.getSuit();
                continue;
            }
            if (!c.getSuit().equals(previousSuit)) {
                return Tier.INVALID;
            }
        }
        return cards.get(0).getTier();
    }

    public String[] toArray() {
        String[] array = new String[5];
        for (int i = 0; i < 5; i++) {
            array[i] = cards.get(i).toString();
        }
        return array;
    }

}
