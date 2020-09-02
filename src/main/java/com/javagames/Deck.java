package com.javagames;

import java.util.*;

/**
 * A deck of cards.
 */
public class Deck {

    private List<Card> cards;

    /**
     * Creates a Deck of cards.
     *
     * @param cards the cards.
     */
    public Deck(final List<Card> cards) {
        this.cards = (cards == null || cards.isEmpty()) ? new ArrayList<>() : new ArrayList<>(cards);
    }

    /**
     * Convenience Constructor for creating an Empty Deck.
     */
    public Deck() {
        this(null);
    }

    /**
     * Shuffles the Deck.
     */
    public final void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Cuts the deck the specified number of times.
     * @param numberOfCuts the number of cuts.
     */
    public List<Deck> cut(int numberOfCuts) throws JavaGameException {
        if (this.cards.isEmpty())
            return new ArrayList<>();
        if (numberOfCuts > this.cards.size())
            throw new JavaGameException("Can't cut a deck more times than it has cards.");
        return cutHelper(numberOfCuts + 1);
    }

    /**
     * Splits the card list into the specified number of decks with nearly-equal sizes.
     *
     * @param decksToMake the number of decks.
     * @return the Deck cuts.
     */
    private List<Deck> cutHelper(int decksToMake) {
        if (decksToMake == 0)
            return new ArrayList<>();
        // For uneven cuts of a deck. Start to add extra cards to cuts until the division is even.
        int cardsToDraw = (int) Math.ceil(this.cards.size() / (double)(decksToMake));
        Deck deckCut = new Deck(draw(cardsToDraw));
        List<Deck> decks = cutHelper(decksToMake - 1);
        decks.add(deckCut);
        return decks;
    }

    /**
     * Convenience method for drawing a card.
     *
     * @return the first card from the top of the Deck.
     */
    public final List<Card> draw() {
        return draw(1);
    }


    public final List<Card> draw(final int numberOfCards) {
        if (numberOfCards < 0)
            throw new IllegalArgumentException("Cannot draw a negative number of cards from the deck.");
        if (numberOfCards >= this.cards.size()) {
            final ArrayList<Card> drawnCards = new ArrayList<>(this.cards);
            this.cards = new ArrayList<>();
            return drawnCards;
        }
        final List<Card> drawnCards = this.cards.subList(0, numberOfCards);
        this.cards = this.cards.subList(numberOfCards, this.cards.size());
        return drawnCards;
    }

    /**
     * Adds cards to the top of this deck in the order provided.
     *
     * @param cards the cards.
     */
    public void addCardsToTop(final List<Card> cards) {
        List<Card> newCards = new ArrayList<>();
        newCards.addAll(cards);
        newCards.addAll(this.cards);
        this.cards = newCards;
    }

    /**
     * Adds cards to the bottom of this deck in the order provided.
     *
     * @param card the cards.
     */
    public void addCardsToBottom(final List<Card> card) {
        cards.addAll(card);
    }

    /**
     * Produces the deck size.
     *
     * @return the deck size.
     */
    public int size() {
        return this.cards.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return this.cards.equals(deck.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }


}
