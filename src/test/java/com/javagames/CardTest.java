package com.javagames;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CardTest {

    @Test
    public void testEquals() {
        final Card card1 = new Card("1");
        final Card anotherCard1 = new Card("1");
        final Card card2 = new Card("2");

        assertThat(card1).isEqualTo(anotherCard1);
        assertThat(card1).isNotEqualTo(card2);
    }

}
