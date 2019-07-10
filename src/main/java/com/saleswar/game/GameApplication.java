package com.saleswar.game;

import com.saleswar.game.entities.Opponent;

//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameApplication {

	public static void main(String[] args) {
		//SpringApplication.run(GameApplication.class, args);
		Opponent firstEnnemy = new Opponent("Germaine the angry grandmother", (Math.random()*50), 75, 250);
		System.out.println(firstEnnemy.getName()+" | "+firstEnnemy.getLightAttack()+" | "+firstEnnemy.getHeavyAttack()+" | "+firstEnnemy.getLife());
	}

}
