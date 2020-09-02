package com.javagames;

import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Player {

    private final Hand hand;

    public Player(final Hand hand) {
        this.hand = hand;
    }

    /**
     * Randomises the {@code player's hand}
     */
    public void shuffleHand() {
        hand.shuffle();
    }

    /**
     * Discards randomly a provided number of cards from the player's hand.
     *
     * @param numberOfCards the number of cards.
     * @return the discarded cards.
     */
    public List<Card> discardRandomly(int numberOfCards) {
        Validate.isTrue(numberOfCards >= 0);
        final List<Card> discardedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            var card = hand.randomSelect();
            if (card.isPresent()) {
                hand.removeCard(card.get());
                discardedCards.add(card.get());
            } else {
                break;
            }
        }
        return discardedCards;
    }

    /**
     * Removes the provided card from the hand.
     *
     * @param card the card to be removed.
     * @return the card that has been discarded.
     */
    public Optional<Card> discardCard(final Card card) {
        return this.hand.removeCard(card);
    }

    /**
     * Draws cards from a provided Deck and adds it to a hand.
     *
     * @param deck the deck.
     * @param numberOfCardsToDraw the number of cards to be draw.
     * @return the number of cards actually drawn.
     */
    public int drawCards(final Deck deck, int numberOfCardsToDraw) {
        return this.hand.drawFromDeck(deck, numberOfCardsToDraw);
    }

    /**
     * Returns the number of cards in the {@code Player}'s hand.
     *
     * @return the number of cards.
     */
    public int countCards() {
        return this.hand.size();
    }

    /**
     * Reveals the player's hand.
     *
     * @return the player's hand.
     */
    public Hand revealHand() {
        return hand.shallowClone();
    }
}
