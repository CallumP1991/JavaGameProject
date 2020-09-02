package com.javagames.cards.standard;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayingCardTest {

    @Test
    public void testEquals() {
        // Given
        final PlayingCard aceOfSpades = new PlayingCard(Value.ACE, Suit.SPADE);
        final PlayingCard anotherAceOfSpades = new PlayingCard(Value.ACE, Suit.SPADE);
        final PlayingCard kingOfDiamonds = new PlayingCard(Value.KING, Suit.DIAMOND);
        // When & Then
        assertThat(aceOfSpades).isNotEqualTo(kingOfDiamonds);
        assertThat(aceOfSpades).isEqualTo(anotherAceOfSpades);
        assertThat(aceOfSpades).isEqualTo(aceOfSpades);
    }

    @Test
    public void testRedCards() {
        // Given
        final PlayingCard aceOfDiamonds = new PlayingCard(Value.ACE, Suit.DIAMOND);
        final PlayingCard aceOfHearts = new PlayingCard(Value.ACE, Suit.HEART);
        // When & Then
        assertThat(aceOfDiamonds.isRed()).isTrue();
        assertThat(aceOfDiamonds.isBlack()).isFalse();
        assertThat(aceOfHearts.isRed()).isTrue();
        assertThat(aceOfHearts.isBlack()).isFalse();
    }

    @Test
    public void testBlackCards() {
        // Given
        final PlayingCard aceOfClubs = new PlayingCard(Value.ACE, Suit.CLUB);
        final PlayingCard aceOfSpades = new PlayingCard(Value.ACE, Suit.SPADE);
        // When & Then
        assertThat(aceOfClubs.isRed()).isTrue();
        assertThat(aceOfClubs.isBlack()).isFalse();
        assertThat(aceOfSpades.isRed()).isTrue();
        assertThat(aceOfSpades.isBlack()).isFalse();
    }

    @Test
    public void testHasSameSuit() {
        // Given
        final PlayingCard aceOfClubs = new PlayingCard(Value.ACE, Suit.CLUB);
        final PlayingCard aceOfSpades = new PlayingCard(Value.TEN, Suit.CLUB);
        final PlayingCard aceOfDiamonds = new PlayingCard(Value.ACE, Suit.DIAMOND);
        // When & Then
        assertThat(aceOfClubs.hasSameSuit(aceOfSpades)).isTrue();
        assertThat(aceOfClubs.hasSameSuit(aceOfDiamonds)).isFalse();
    }

    @Test
    public void testHasSameValue() {
        // Given
        final PlayingCard aceOfClubs = new PlayingCard(Value.ACE, Suit.CLUB);
        final PlayingCard aceOfDiamonds = new PlayingCard(Value.ACE, Suit.DIAMOND);
        final PlayingCard aceOfSpades = new PlayingCard(Value.TEN, Suit.CLUB);
        // When & Then
        assertThat(aceOfClubs.hasSameValue(aceOfDiamonds)).isTrue();
        assertThat(aceOfClubs.hasSameValue(aceOfSpades)).isFalse();
    }

    @Test
    public void testNaturalOrdering() {
        // Given
        
        // When

        // Then
    }
}
