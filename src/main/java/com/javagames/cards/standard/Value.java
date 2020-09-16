package com.javagames.cards.standard;

import java.util.Set;

/**
 * The values that a standard playing card can hold.
 */
public enum Value {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;

    private static final Set<Value> NUMERAL_CARDS = Set.of(TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN);
    private static final Set<Value> COURT_CARDS = Set.of(JACK, QUEEN, KING);

    @Override
    public String toString() {
        return name();
    }

    /**
     * Checks whether this card is a numeral card.
     *
     * @return {@literal true} if this card is a numeral card, else {@literal false}.
     */
    public boolean isNumeralCard() {
        return NUMERAL_CARDS.contains(this);
    }

    /**
     * Checks whether this card is a court card.
     *
     * @return {@literal true} if this card is a court card, else {@literal false}.
     */
    public boolean isCourtCard() {
        return COURT_CARDS.contains(this);
    }
}
