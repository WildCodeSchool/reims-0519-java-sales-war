package com.saleswar.game.repositories;


public class Actions {

    public static int highheels() {
        double probability = Math.random();
        int damage = 30;
        if (probability > 0.5) {
            return damage;
        } else {
            return 0;
        }
    }

    public static int handbag() {
        double probability = Math.random();
        int damage = 10;
        if (probability > 0.2) {
            return damage;
        } else {
            return 0;
        }
}


}