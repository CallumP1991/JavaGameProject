package com.javagames;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {

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

    private Deck<Card> sortedDeck() {
        return new Deck<>(cards);
    }

    @Test
    public void shuffleTest() {
        // Given
        final Deck<Card> deck = sortedDeck();
        // When
        // Shuffle twice to make the odds of getting the ordered combination 1 / 229,442,532,802,560,000
        deck.shuffle();
        deck.shuffle();
        // Then
        assertThat(deck).isNotEqualTo(sortedDeck());
    }

    @Test
    public void shuffleEmptyDeckTest() {
        // Given
        final Deck<Card> deck = new Deck();
        // When
        deck.shuffle();
        // Then
        assertThat(deck).isEqualTo(new Deck());
    }

    @Test
    public void drawCardTest() {

        // Given
        final Deck<Card> deck = sortedDeck();
        // When
        List<Card> drawnCards = deck.draw();

        // Then
        Card expectedCard = new Card("1");
        assertThat(drawnCards).hasSize(1);
        assertThat(drawnCards.get(0)).isEqualTo(expectedCard);
        assertThat(deck.size()).isEqualTo(sortedDeck().size() - 1);
    }

    @Test
    public void drawZeroCards() {
        // Given
        final Deck<Card> deck = sortedDeck();
        // When
        final List<Card> drawnCards = deck.draw(0);
        // Then
        assertThat(drawnCards).hasSize(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawNegativeCards() {
        // Given
        final Deck<Card> deck = sortedDeck();
        // When
        try {
            deck.draw(-1);
        } finally {
            // Then
            assertThat(deck.size()).isEqualTo(12);
        }
    }

    @Test
    public void drawSingleCard() {
        // Given
        final Deck<Card> deck = sortedDeck();
        // When
        final List<Card> draw = deck.draw(1);
        // Then
        assertThat(draw).hasSize(1);
        Card expectedCard = new Card("1");
        assertThat(draw.get(0)).isEqualTo(expectedCard);
    }

    @Test
    public void drawMultipleCardsTest() {

        // Given
        final Deck<Card> deck = sortedDeck();
        // When
        List<Card> cards = deck.draw(2);

        // Then
        assertThat(cards).hasSize(2);
        Card cardOne = new Card("1");
        assertThat(cards.get(0)).isEqualTo(cardOne);
        Card cardTwo = new Card("2");
        assertThat(cards.get(1)).isEqualTo(cardTwo);
        assertThat(deck.size()).isEqualTo(sortedDeck().size() - 2);
    }

    @Test
    public void testDrawCardsFromEmptyDeck() {

        // Given
        Deck<Card> deck = new Deck();

        // When
        List<Card> drawnCards = deck.draw();

        // Then
        assertThat(drawnCards).isEmpty();
        assertThat(deck.size()).isEqualTo(0);
    }

    @Test
    public void testDepleteDeckByDrawing() {

        // Given
        Deck<Card> deck = sortedDeck();
        final int numberOfCards = deck.size();

        // When
        List<Card> cards = deck.draw(numberOfCards);

        // Then
        assertThat(cards).hasSize(numberOfCards);
        assertThat(deck.size()).isEqualTo(0);
    }

    @Test
    public void testDepleteDeckByDrawingTooManyCards() {

        // Given
        Deck<Card> deck = sortedDeck();
        final int numberOfCards = deck.size();

        // When
        List<Card> cards = deck.draw(numberOfCards + 1);

        // Then
        assertThat(cards).hasSize(numberOfCards);
        assertThat(deck.size()).isEqualTo(0);
    }

    @Test
    public void cutEquallyDeckInHalf() throws JavaGameException {
        Deck<Card> deck = sortedDeck();
        assertThat(deck.size()).isEqualTo(12);

        List<Deck<Card>> decks = deck.cut(1);

        assertThat(decks.size()).isEqualTo(2);
        for (Deck<Card> cutDeck : decks) {
            assertThat(cutDeck.size()).isEqualTo(6);
        }
        assertThat(decks.get(0)).isNotEqualTo(decks.get(1));
    }

    @Test
    public void cutEmptyDeck() throws JavaGameException {
        // Given
        Deck<Card> deck = new Deck();
        // When
        deck.cut(1);
        // Then
    }

    @Test
    public void cutEquallyDeckInThirds() throws JavaGameException {
        Deck<Card> deck = sortedDeck();
        assertThat(deck.size()).isEqualTo(12);

        List<Deck<Card>> decks = deck.cut(2);
        assertThat(decks).hasSize(3);
        assertThat(decks.get(0).size()).isEqualTo(4);
        assertThat(decks.get(1).size()).isEqualTo(4);
        assertThat(decks.get(2).size()).isEqualTo(4);
        // Check that each deck<Card cut is not equal to anything other cut.
        for (int i = 0; i < decks.size(); i++)
            for (int j = i + 1; j < decks.size(); j++)
                assertThat(decks.get(i)).isNotEqualTo(decks.get(j));

    }

    @Test
    public void cutUnequallyDeckInThirds() throws JavaGameException {
        Deck<Card> deck = sortedDeck();
        deck.draw();
        assertThat(deck.size()).isEqualTo(11);

        List<Deck<Card>> decks = deck.cut(2);
        assertThat(decks).hasSize(3);
        assertThat(decks.get(0).size()).isEqualTo(3);
        assertThat(decks.get(1).size()).isEqualTo(4);
        assertThat(decks.get(2).size()).isEqualTo(4);
        for (int i = 0; i < decks.size(); i++)
            for (int j = i + 1; j < decks.size(); j++)
                assertThat(decks.get(i)).isNotEqualTo(decks.get(j));
    }

    @Test
    public void cutUnequallyDeckInFifths() throws JavaGameException {
        Deck<Card> deck = sortedDeck();
        assertThat(deck.size()).isEqualTo(12);

        List<Deck<Card>> decks = deck.cut(4);
        assertThat(decks).hasSize(5);
        assertThat(decks.get(0).size()).isEqualTo(2);
        assertThat(decks.get(1).size()).isEqualTo(2);
        assertThat(decks.get(2).size()).isEqualTo(2);
        assertThat(decks.get(3).size()).isEqualTo(3);
        assertThat(decks.get(4).size()).isEqualTo(3);
        for (int i = 0; i < decks.size(); i++)
            for (int j = i + 1; j < decks.size(); j++)
                assertThat(decks.get(i)).isNotEqualTo(decks.get(j));
    }

    @Test
    public void cutUnequallyDeckInHalf() throws JavaGameException {
        Deck<Card> deck = sortedDeck();
        deck.draw();
        assertThat(deck.size()).isEqualTo(11);

        List<Deck<Card>> decks = deck.cut(1);
        assertThat(decks).hasSize(2);
        assertThat(decks.get(1).size()).isEqualTo(6);
        assertThat(decks.get(0).size()).isEqualTo(5);
        for (int i = 0; i < decks.size(); i++)
            for (int j = i + 1; j < decks.size(); j++)
                assertThat(decks.get(i)).isNotEqualTo(decks.get(j));
    }

    @Test
    public void testShuffleIsNotDeterministic() {
        final Deck<Card> orderedDeck = sortedDeck();
        final Deck<Card> deck1 = sortedDeck();
        final Deck<Card> deck2 = sortedDeck();
        assertThat(deck2).isEqualTo(deck1);
        assertThat(deck1).isEqualTo(deck2);
        assertThat(deck1).isEqualTo(orderedDeck);
        deck1.shuffle();
        assertThat(deck1).isNotEqualTo(deck2);
        assertThat(deck1).isNotEqualTo(orderedDeck);
        assertThat(deck2).isNotEqualTo(deck1);
        assertThat(deck2).isEqualTo(orderedDeck);
        deck2.shuffle();
        assertThat(deck1).isNotEqualTo(deck2);
        assertThat(deck1).isNotEqualTo(orderedDeck);
        assertThat(deck2).isNotEqualTo(deck1);
        assertThat(deck2).isNotEqualTo(orderedDeck);
    }

    @Test
    public void testAddCardsToTheBottomOfTheDeck() {
        // Given
        final Deck<Card> deck = sortedDeck();
        final int originalDeckSize = deck.size();
        final List<Card> cardsToAdd = Arrays.asList(new Card("13"), new Card("14"));
        // When
        deck.addCardsToBottom(cardsToAdd);
        // Then
        assertThat(deck.size()).isEqualTo(originalDeckSize + cardsToAdd.size());
        // draw and discard first 12 cards
        deck.draw(originalDeckSize);
        final List<Card> final2Cards = deck.draw(cardsToAdd.size());
        assertThat(final2Cards).isEqualTo(cardsToAdd);
    }

    @Test
    public void testAddCardsToTheTopOfTheDeck() {
        // Given
        final Deck<Card> deck = sortedDeck();
        final int originalDeckSize = deck.size();
        final List<Card> cardsToAdd = Arrays.asList(new Card("13"), new Card("14"));
        // When
        deck.addCardsToTop(cardsToAdd);
        // Then
        assertThat(deck.size()).isEqualTo(originalDeckSize + cardsToAdd.size());
        final List<Card> first2Cards = deck.draw(cardsToAdd.size());
        assertThat(first2Cards).isEqualTo(cardsToAdd);
    }

    @Test
    public void testAddZeroCardsToTop() {
        // Given
        final Deck<Card> deck = sortedDeck();
        final int originalDeckSize = deck.size();
        final List<Card> cardsToAdd = Collections.emptyList();
        // When
        deck.addCardsToTop(cardsToAdd);
        // Then
        assertThat(deck.size()).isEqualTo(originalDeckSize);
    }

    @Test
    public void testAddZeroCardsToBottom() {
        // Given
        final Deck<Card> deck = sortedDeck();
        final int originalDeckSize = deck.size();
        final List<Card> cardsToAdd = Collections.emptyList();
        // When
        deck.addCardsToBottom(cardsToAdd);
        // Then
        assertThat(deck.size()).isEqualTo(originalDeckSize);
    }
}
