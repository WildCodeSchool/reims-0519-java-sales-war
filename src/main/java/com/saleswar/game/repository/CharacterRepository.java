package com.saleswar.game.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.saleswar.game.entities.Character;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CharacterRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/baker_street_fighter?serverTimezone=GMT";
    private final static String DB_USER = "baker";
    private final static String DB_PASSWORD = "Fighter51!";

    private List<Character> characters;

    public CharacterRepository() {
        this.characters = new ArrayList<Character>();
        characters.add(new Character(1, "Sherlock Holmes",100));
        characters.add(new Character(2, "Professeur Moriarty",100));
    }

    public Character getFighterById(int id) {
        for(Character character : characters) {
            if(character.getId() == id) {
                return character;
            }
        }
        return null;
    }

    public static int uppercut() {
        double probability = Math.random();
        int damage = 30;
        if (probability > 0.5) {
            return damage;
        } else {
            return 0;
        }
    }

    public static int punch() {
        double probability = Math.random();
        int damage = 10;
        if (probability > 0.2) {
            return damage;
        } else {
            return 0;
        }
    }

    public List<Character> getRankingCharacters() {
        try(
            Connection connection = DriverManager.getConnection(
                DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM fighter"
            );
        ) {
            try(
                ResultSet resulSet = statement.executeQuery();
            ) {
                List<Character> players = new ArrayList<Character>();

                while(resulSet.next()){
                    int id = resulSet.getInt("id");
                    String name = resulSet.getString("name");
                    int life = resulSet.getInt("life");
                    characters.add(new Character(id, name, life));
                }

                return players;
            }
        }
        catch (SQLException e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, "", e
            );
        }
    }

    public static int insertScore() {
        //todo faire linsert du score
    return 0;
    }
}