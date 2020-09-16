package com.javagames.cards.standard;

/**
 * The suits found on standard playing cards.
 */
public enum Suit {
    HEART(SuitColour.RED), CLUB(SuitColour.BLACK), SPADE(SuitColour.BLACK), DIAMOND(SuitColour.RED);

    private final SuitColour colour;

    /**
     * Constructor for {@code Suit}.
     *
     * @param colour the colour.
     */
    Suit(final SuitColour colour) {
        this.colour = colour;
    }

    /**
     * Checks whether this Suit is red.
     *
     * @return {@literal true}, if this suit is red, else {@literal false}.
     */
    public boolean isRed() {
        return SuitColour.RED.equals(colour);
    }

    /**
     * Checks whether this Suit is black.
     *
     * @return {@literal true}, if this suit is black, else {@literal false}.
     */
    public boolean isBlack() {
        return SuitColour.BLACK.equals(colour);
    }

    /**
     * The suit colour.
     */
    private enum SuitColour {
        RED, BLACK
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return name();
    }
}
