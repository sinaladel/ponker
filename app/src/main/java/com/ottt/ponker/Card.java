package com.ottt.ponker;

import androidx.annotation.NonNull;

public class Card implements Comparable<Card> {
    private Tier tier;
    private Suit suit;


    public Card(Tier tier, Suit suit) {
        if (tier == null || suit == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        this.tier = tier;
        this.suit = suit;
    }

    public Tier getTier() {
        return tier;
    }

    public Suit getSuit() {
        return suit;
    }

    @NonNull
    @Override
    public String toString() {
        String s = "";

        switch (tier) {
            case ACE:
                s += "A";
                break;
            case TWO:
                s += "2";
                break;
            case THREE:
                s += "3";
                break;
            case FOUR:
                s += "4";
                break;
            case FIVE:
                s += "5";
                break;
            case SIX:
                s += "6";
                break;
            case SEVEN:
                s += "7";
                break;
            case EIGHT:
                s += "8";
                break;
            case NINE:
                s += "9";
                break;
            case TEN:
                s += "10";
                break;
            case JACK:
                s += "J";
                break;
            case QUEEN:
                s += "Q";
                break;
            case KING:
                s += "K";
                break;
            default:
                return " ";
        }
        switch (suit) {
            case HEARTS:
                s += "\u2665";
                break;
            case DIAMONDS:
                s += "\u2666";
                break;
            case CLUBS:
                s += "\u2663";
                break;
            case SPADES:
                s += "\u2660";
                break;
            default:
                return "\u26ab";
        }
        return s;
    }

    public String toText() {
        String s = "";

        switch (tier) {
            case ACE:
                s += "Ace of ";
                break;
            case TWO:
                s += "Two of ";
                break;
            case THREE:
                s += "Three of ";
                break;
            case FOUR:
                s += "Four of ";
                break;
            case FIVE:
                s += "Five of ";
                break;
            case SIX:
                s += "Six of ";
                break;
            case SEVEN:
                s += "Seven of ";
                break;
            case EIGHT:
                s += "Eight of ";
                break;
            case NINE:
                s += "Nine of ";
                break;
            case TEN:
                s += "Ten of ";
                break;
            case JACK:
                s += "Jack of ";
                break;
            case QUEEN:
                s += "Queen of ";
                break;
            case KING:
                s += "King of ";
                break;
            default:
                return "Invalid card";
        }
        switch (suit) {
            case HEARTS:
                s += "hearts";
                break;
            case DIAMONDS:
                s += "diamonds";
                break;
            case CLUBS:
                s += "clubs";
                break;
            case SPADES:
                s += "spades";
                break;
            default:
                return "Invalid card";
        }
        return s;
    }

    @Override
    public int compareTo(Card o) {
        if (this.tier.equals(o.tier)) {
            if (this.suit.ordinal() == o.suit.ordinal()) {
                return 0;
            } else if (o.suit.ordinal() < o.suit.ordinal()) {
                return -1;
            } else {
                return 1;
            }
        } else if (this.tier.ordinal() < o.tier.ordinal()) {
            return 1;
        } else {
            return -1;
        }
    }
}
