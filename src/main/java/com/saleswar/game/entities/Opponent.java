package com.saleswar.game.entities;

public class Opponent {
    private int id;
    private String name;
    private int life;

    public Opponent(int id,String name) {
        this.id = id;
        this.name = name;
        this.life = 200;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}