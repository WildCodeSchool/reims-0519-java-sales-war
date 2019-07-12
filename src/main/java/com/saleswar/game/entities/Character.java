package com.saleswar.game.entities;

public class Character extends Player {

    private int life;

    public Character(int id, String name,int life) {
        super(id, name);
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void takeHit(int hit) {
        this.setLife(Math.max(this.getLife() - hit, 0));
    }

}