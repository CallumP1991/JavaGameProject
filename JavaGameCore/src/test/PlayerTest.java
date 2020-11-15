package com.javagames;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    private final List<Card> cards = new ArrayList<>();

    {
        cards.add(new Card("1"));
        cards.add(new Card("2"));
        cards.add(new Card("3"));
        cards.add(new Card("4"));
        cards.add(new Card("5"));
        cards.add(new Card("6"));
        cards.add(new Card("7"));
        cards.add(new Card("8"));
        cards.add(new Card("9"));
        cards.add(new Card("10"));
        cards.add(new Card("11"));
        cards.add(new Card("12"));
    }

    private Hand orderedHand() {
        return new Hand(cards);
    }

    private Deck orderedDeck() {
        return new Deck(cards);
    }

    @Test
    public void testRandomlyDiscardOneCard() {
        // Given
        final Player player = new Player(orderedHand());
        // When
        final List<Card> discardedCards = player.discardRandomly(1);
        // Then
        assertThat(discardedCards.size()).isEqualTo(1);
        assertThat(player.countCards()).isEqualTo(11);
    }

    @Test
    public void testRandomlyDiscardMultipleCards() {
        // Given
        final Player player = new Player(orderedHand());
        int numberOfCardsToDiscard = 3;
        // When
        final List<Card> discardedCards = player.discardRandomly(numberOfCardsToDiscard);
        // Then
        assertThat(discardedCards.size()).isEqualTo(numberOfCardsToDiscard);
        assertThat(player.countCards()).isNotIn(discardedCards.size());
    }

    @Test
    public void testRandomlyDiscardCardFromEmptyHand() {
        // Given
        final Player player = new Player(orderedHand());
        int numberOfCardsToDiscard = 0;
        // When
        final List<Card> discardedCards = player.discardRandomly(numberOfCardsToDiscard);
        // Then
        assertThat(discardedCards.size()).isEqualTo(numberOfCardsToDiscard);
        assertThat(player.countCards()).isNotIn(discardedCards.size());
    }

    @Test
    public void testDiscardSpecificCard() {
        // Given
        Card card = new Card("1");
        final Player player = new Player(orderedHand());
        // When
        final Optional<Card> discardedCard = player.discardCard(card);
        // Then
        assertThat(discardedCard).hasValue(card);
        assertThat(player.countCards()).isEqualTo(11);
    }

    @Test
    public void testCountCards() {
        // Given
        final Hand hand = orderedHand();
        final Player player = new Player(hand);
        // When
        final int cardCount = player.countCards();
        // Then
        assertThat(cardCount).isEqualTo(hand.size());
    }

    @Test
    public void testShuffle() {
        // Given
        final Hand hand = orderedHand();
        final Player player = new Player(hand);
        // When
        // Shuffle twice to make getting the same combination incredibly unlikely.
        player.shuffleHand();
        player.shuffleHand();
        // Then
        assertThat(player.revealHand()).isNotEqualTo(orderedHand());
    }

    @Test
    public void drawCardsFromDeck() {
        // Given
        final Deck deck = orderedDeck();
        final Hand hand = new Hand();
        final Player player = new Player(hand);
        final int cardsToDraw = 3;
        // When
        player.drawCards(deck, cardsToDraw);
        // Then
        assertThat(player.countCards()).isEqualTo(cardsToDraw);
        assertThat(deck.size()).isEqualTo(orderedDeck().size() - cardsToDraw);
    }
}
