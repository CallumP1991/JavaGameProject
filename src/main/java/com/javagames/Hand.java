package com.javagames;

import java.util.*;

public class Hand {

    private final List<Card> cards;

    /**
     * Convenience Constructor for creating an empty hand.
     */
    public Hand() {
        this(null);
    }

    /**
     * Creates a hand from a provided list of cards.
     */
    public Hand(final List<Card> cards) {
        this.cards = (cards == null || cards.isEmpty()) ? new ArrayList<>() : new ArrayList<>(cards);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Returns the reference to a random card in this hand without removing it.
     *
     * @return the selected card.
     */
    public Optional<Card> randomSelect() {
        if (this.cards.isEmpty())
            return Optional.empty();
        int index = (int) (Math.random() * cards.size());
        return Optional.of(cards.get(index));
    }

    /**
     * Removes the provided card from the hand.
     *
     * @param card the card to be removed.
     * @return the removed card.
     */
    public Optional<Card> removeCard(final Card card) {
        if (this.cards.remove(card)) {
            return Optional.of(card);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Draws a number of cards from the provided deck and adds them to the hands card list.
     *
     * @param deck        the deck.
     * @param cardsToDraw the number of cards to draw.
     * @return the number of cards actually drawn.
     */
    public int drawFromDeck(final Deck deck, int cardsToDraw) {
        final List<Card> drawnCards = deck.draw(cardsToDraw);
        this.cards.addAll(drawnCards);
        return drawnCards.size();
    }

    /**
     * Counts the number of cards in this hand.
     *
     * @return the number of cards.
     */
    public int size() {
        return this.cards.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand hand = (Hand) o;
        return Objects.equals(cards, hand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    /**
     * Performs a read-only shallow clone of a player's hand.
     *
     * @return the player's hand.
     */
    public Hand shallowClone() {
        return new Hand(Collections.unmodifiableList(this.cards));
    }
}
