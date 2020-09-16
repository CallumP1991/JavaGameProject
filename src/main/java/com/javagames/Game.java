package com.javagames;

import com.javagames.Player;

import java.util.List;

public abstract class Game {
    
    protected final List<Player> players;

    protected Game(final List<Player> players) {
        this.players = players;
    }

    public abstract void setup();

    public abstract void start();
}
