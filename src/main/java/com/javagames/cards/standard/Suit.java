package com.javagames.cards.standard;

public enum Suit {
    HEART(SuitColour.RED), CLUB(SuitColour.BLACK), SPADE(SuitColour.BLACK), DIAMOND(SuitColour.RED);

    private final SuitColour colour;

    Suit(final SuitColour colour) {
        this.colour = colour;
    }

    public boolean isRed() {
        return SuitColour.RED.equals(colour);
    }

    public boolean isBlack() {
        return SuitColour.BLACK.equals(colour);
    }

    private enum SuitColour {
        RED, BLACK
    }

    @Override
    public String toString() {
        return name();
    }
}
