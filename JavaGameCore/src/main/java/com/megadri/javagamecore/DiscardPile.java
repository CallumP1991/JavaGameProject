package com.megadri.javagamecore;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Optional;
import java.util.Queue;


/**
 * A discard pile of cards.
 *
 * @param <T> the type of card in the discard pile.
 */
public class DiscardPile<T extends Card> extends Deck<T> {

    private final Deque<T> discardedCards;

    public DiscardPile(Deque<T> discardedCards) {
        this.discardedCards = discardedCards;
    }

    public DiscardPile() {
        this.discardedCards = new ArrayDeque<>();
    }

    public Optional<T> pickupCard() {
        if (discardedCards.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(discardedCards.pop());
    }

    public void addCard(T card) {
        discardedCards.push(card);
    }

    public Queue<T> viewPile() {
        return Collections.asLifoQueue(discardedCards);
    }
}
