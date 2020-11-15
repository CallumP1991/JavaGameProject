package com.megadri.javagamecore;

import java.util.Objects;

public class Card {

    protected final String cardName;

    public Card(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(cardName, card.cardName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardName);
    }
}
