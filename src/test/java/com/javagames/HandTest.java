package com.javagames;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class HandTest {

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
    public void shuffleTest() {
        // Given
        final Hand hand = orderedHand();
        // When
        // Shuffle twice to make the odds of getting the ordered combination 1 / 229,442,532,802,560,000
        hand.shuffle();
        hand.shuffle();
        // Then
        assertThat(hand).isNotEqualTo(orderedHand());
    }

    @Test
    public void testShuffleIsNotDeterministic() {
        final Hand orderedHand = orderedHand();
        final Hand hand1 = orderedHand();
        final Hand hand2 = orderedHand();
        assertThat(hand2).isEqualTo(hand1);
        assertThat(hand1).isEqualTo(hand2);
        assertThat(hand1).isEqualTo(orderedHand);
        hand1.shuffle();
        assertThat(hand1).isNotEqualTo(hand2);
        assertThat(hand1).isNotEqualTo(orderedHand);
        assertThat(hand2).isNotEqualTo(hand1);
        assertThat(hand2).isEqualTo(orderedHand);
        hand2.shuffle();
        assertThat(hand1).isNotEqualTo(hand2);
        assertThat(hand1).isNotEqualTo(orderedHand);
        assertThat(hand2).isNotEqualTo(hand1);
        assertThat(hand2).isNotEqualTo(orderedHand);
    }

    @Test
    public void testDrawOneCardFromDeck() {
        // Given
        final Deck deck = orderedDeck();
        final Hand hand = new Hand();
        // When
        hand.drawFromDeck(deck, 1);
        // Then
        assertThat(deck.size()).isEqualTo(11);
        assertThat(hand.size()).isEqualTo(1);
    }

    @Test
    public void testDrawMultipleCardsFromDeck() {
        // Given
        final Deck deck = orderedDeck();
        final int deckSize = deck.size();
        final Hand hand = new Hand();
        final int cardsToDraw = 5;
        // When
        hand.drawFromDeck(deck, cardsToDraw);
        // Then
        assertThat(deck.size()).isEqualTo(deckSize - cardsToDraw);
        assertThat(hand.size()).isEqualTo(cardsToDraw);
    }

    @Test
    public void testDrawCardsAndDepleteDeck() {
        // Given
        final Deck deck = orderedDeck();
        final int deckSize = deck.size();
        final Hand hand = new Hand();
        final int cardsToDraw = 12;
        // When
        hand.drawFromDeck(deck, cardsToDraw);
        // Then
        assertThat(deck.size()).isEqualTo(deckSize - cardsToDraw);
        assertThat(hand.size()).isEqualTo(cardsToDraw);
    }

    @Test
    public void testDepleteDeckByDrawingTooManyCards() {
        // Given
        final Deck deck = new Deck();
        final Hand hand = new Hand();
        final int cardsToDraw = 1;
        // When
        hand.drawFromDeck(deck, cardsToDraw);
        // Then
        assertThat(deck.size()).isEqualTo(0);
        assertThat(hand.size()).isEqualTo(0);
    }

    @Test
    public void testRemoveCardSuccessfully() {
        // Given
        final Hand orderedHand = orderedHand();
        final Card cardToRemove = new Card("1");
        // When
        final Optional<Card> card = orderedHand.removeCard(cardToRemove);
        // Then
        assertThat(card).isPresent();
        assertThat(card).hasValue(cardToRemove);
    }

    @Test
    public void testRemoveCardUnsuccessfully() {
        // Given
        final Hand hand = new Hand();
        final Card cardToRemove = new Card("1");
        // When
        final Optional<Card> card = hand.removeCard(cardToRemove);
        // Then
        assertThat(card).isNotPresent();
    }

    @Test
    public void testRandomSelectOnEmptyHand() {
        // Given
        final Hand hand = new Hand();
        // When
        final Optional<Card> selectedCard = hand.randomSelect();
        // Then
        assertThat(selectedCard).isNotPresent();
    }

    @Test
    public void testRandomSelectOnSingleCardHand() {
        // Given
        final Card card = new Card("1");
        final List<Card> cards = Collections.singletonList(card);
        final Hand hand = new Hand(cards);
        // When
        final Optional<Card> selectedCard = hand.randomSelect();
        // Then
        assertThat(selectedCard).hasValue(card);
    }

    @Test
    public void testRandomSelectOnPopulatedHand() {
        // Given
        final Hand hand = orderedHand();
        // When
        final Optional<Card> selectedCard = hand.randomSelect();
        // Then
        assertThat(selectedCard).isPresent();
    }
}
