package com.saleswar.game.entities;

public class Opponent {

    private String name;
    private double lightAttack;
    private int heavyAttack;
    private int life;

    public Opponent(String name, double lightAttack, int heavyAttack, int life) {
        this.name = name;
        this.lightAttack = lightAttack;
        this.heavyAttack = heavyAttack;
        this.life = life;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeavyAttack() {
        return heavyAttack;
    }

    public void setHeavyAttack(int heavyAttack) {
        this.heavyAttack = heavyAttack;
    }

    public double getLightAttack() {
        return lightAttack;
    }

    public void setLightAttack(double lightAttack) {
        this.lightAttack = lightAttack;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}