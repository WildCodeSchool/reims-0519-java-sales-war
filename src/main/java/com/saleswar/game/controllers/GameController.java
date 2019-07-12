package com.saleswar.game.controllers;

import javax.servlet.http.HttpSession;

import com.saleswar.game.repository.CharacterRepository;
//import com.saleswar.game.repository.ScoreRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
class GameController {

    private static CharacterRepository characterRepository = new CharacterRepository();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/rules")
    public String rules() {
        return "rules";
    }

    @GetMapping("/win")
    public String win() {
        return "win";
    }
    @GetMapping("/loose")
    public String loose() {
        return "loose";
    }
    @GetMapping("/scores")
    public String scores() {
        return "scores";
    }
    @GetMapping("/game")
    public String fight(Model model, HttpSession session) {

            session.setAttribute("currentPlayer", 1);
            session.setAttribute("currentPlayer", 2);
            session.setAttribute("currentOpponent", 3);
            

        model.addAttribute("message", "Get back fast your dress");
        if(session.getAttribute("lastAttackFailed") != null) {
            if(session.getAttribute("lastAttackFailed").equals(false)) {
                model.addAttribute("message", "Your attack hit the grandma, but she's older than you and beat your ass back ! ");
            }
            else {
                model.addAttribute("message", "The attack failed");
            }
        }
        model.addAttribute("currentPlayer", session.getAttribute("currentPlayer").equals(1) ? "Player 1 & Player 2" : "Germaine la folle furieuse");
        model.addAttribute("lifeP1", characterRepository.getFighterById(1).getLife());
        model.addAttribute("lifeP2", characterRepository.getFighterById(2).getLife());
        model.addAttribute("lifeP3", characterRepository.getFighterById(3).getLife());

        model.addAttribute("nickname1", session.getAttribute("nickname1"));
        model.addAttribute("nickname2", session.getAttribute("nickname2"));

        return "game";
    }

    @PostMapping("/game")
    public String fight(HttpSession session, Model model, @RequestParam(required = false) String attack, @RequestParam(required=false) String nickname1, @RequestParam(required=false) String nickname2) {

        if (session.getAttribute("nickname1") == null) {
            session.setAttribute("nickname1", nickname1);
        }
        if (session.getAttribute("nickname2") == null) {
        session.setAttribute("nickname2", nickname2);
        }

        boolean fight = true;

        if (attack != null) {
            int targetId = 1;
            int bigMomaId = 3;

            double probability = Math.random();
            if (probability < 0.5) {
                targetId = 2;
            }
            else {
                targetId = 1;
            }

            int hit = 0;
            if (attack.equals("uppercut")) {
                hit = CharacterRepository.uppercut();
            }

            else {
                hit = CharacterRepository.punch();
            }

            int hitBigMoma = 0;
            hitBigMoma = characterRepository.bigMomaAttack();
            
            if (hit > 0) {
                session.setAttribute("lastAttackFailed", false);
                characterRepository.getFighterById(bigMomaId).takeHit(hit);
            }
            if (hitBigMoma > 0) {
                characterRepository.getFighterById(targetId).takeHit(hitBigMoma);
            }

            else {
                session.setAttribute("lastAttackFailed", true);
            }

            if (characterRepository.getFighterById(bigMomaId).getLife() == 0) {
                fight = false;
            }

            if (characterRepository.getFighterById(targetId).getLife() == 0) {
                return "redirect:/loose";
            }
            
            else {
                session.setAttribute("currentPlayer", bigMomaId);

            }

        }
        if(fight) {
            return "redirect:/game";
        } 
        else { 
            return "redirect:/win";
        }
    }
}