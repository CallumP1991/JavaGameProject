package com.javagames.cards.standard;

import com.javagames.Card;

import java.util.Objects;

public class PlayingCard extends Card {

    private final Suit suit;
    private final Value value;

    public PlayingCard(final Value value, final Suit suit) {
        super(value + "-OF-" + suit);
        this.suit = suit;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PlayingCard that = (PlayingCard) o;
        return suit == that.suit &&
                value == that.value;
    }

    /**
     * Checks whether this card is red.
     *
     * @return true, if card is red.
     */
    public boolean isRed() {
        return suit.isRed();
    }

    /**
     * Checks whether this card is black.
     *
     * @return true, if card is black.
     */
    public boolean isBlack() {
        return suit.isBlack();
    }

    /**
     * Checks whether this card has the same suit as a provided {@code PlayingCard}.
     *
     * @param playingCard the playing card.
     * @return true, if the cards have the same suit.
     */
    public boolean hasSameSuit(final PlayingCard playingCard) {
        return playingCard.suit == suit;
    }

    /**
     * Checks whether this card has the same suit as a provided {@code PlayingCard}.
     *
     * @param playingCard the playing card.
     * @return true, if the cards have the same suit.
     */
    public boolean hasSameValue(final PlayingCard playingCard) {
        return playingCard.value == value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), suit, value);
    }

    @Override
    public String toString() {
        return super.cardName;
    }
}
