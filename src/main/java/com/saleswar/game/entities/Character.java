package com.saleswar.game.entities;

public class Character extends Player {

    private int life;

    public Character(int id, String name) {
        super(id, name);
        this.life = 100;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    
}