package com.javagames.cards.standard;

import java.util.Set;

public enum Value {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;

    private static final Set<Value> NUMERAL_CARDS = Set.of(TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN);
    private static final Set<Value> COURT_CARDS = Set.of(JACK, QUEEN, KING);

    @Override
    public String toString() {
        return name();
    }

    public boolean isNumeral() {
        return NUMERAL_CARDS.contains(this);
    }

    public boolean isCourt() {
        return COURT_CARDS.contains(this);
    }
}
