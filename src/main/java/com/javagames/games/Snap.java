package com.javagames.games;

import com.javagames.Deck;
import com.javagames.Game;
import com.javagames.Player;
import com.javagames.cards.standard.PlayingCard;

import java.util.List;

public class Snap extends Game {

    final Deck<PlayingCard> deck;

    public Snap(final List<Player> players, final Deck<PlayingCard> deck) {
        super(players);
        if (this.players == null || this.players.size() != 2) {
            throw new IllegalArgumentException("Game of Snap requires 2 players");
        }
        if (deck == null || deck.isEmpty())
            throw new IllegalArgumentException("Game of Snap requires a non-empty deck of playing cards.");
        this.deck = deck;

    }

    @Override
    public void setup() {

    }

    @Override
    public void start() {

    }


}
