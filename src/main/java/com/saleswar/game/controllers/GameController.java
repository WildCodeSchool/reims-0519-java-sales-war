package com.saleswar.game.controllers;

import javax.servlet.http.HttpSession;

import com.saleswar.game.repository.CharacterRepository;

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

    @GetMapping("/scores")
    public String scores() {
        return "scores";
    }
    @GetMapping("/game")
    public String fight(Model model, HttpSession session) {

        if(session.getAttribute("currentPlayer") == null) {
            double probability = Math.random();
            if(probability > 0.5) {
                session.setAttribute("currentPlayer", 1);
            } 
            else {
                session.setAttribute("currentPlayer", 2);
            }
        }

        model.addAttribute("message", "Let the battle begin");
        if(session.getAttribute("lastAttackFailed") != null) {
            if(session.getAttribute("lastAttackFailed").equals(false)) {
                model.addAttribute("message", "The attack worked");
            }
            else {
                model.addAttribute("message", "The attack failed");
            }
        }
        model.addAttribute("currentPlayer", session.getAttribute("currentPlayer").equals(1) ? "Sherlock" : "Moriarty");
        model.addAttribute("lifeP1", characterRepository.getFighterById(1).getLife());
        model.addAttribute("lifeP2", characterRepository.getFighterById(2).getLife());

        return "game";
    }

    @PostMapping("/game")
    public String fight(HttpSession session, @RequestParam(required = false) String attack) {

        boolean fight = true;

        if (attack != null) {

            int currentOpponent = 2;
            if (!session.getAttribute("currentPlayer").equals(1)) {
                currentOpponent = 1;
            }

            int hit = 0;
            if (attack.equals("uppercut")) {
                hit = CharacterRepository.uppercut();
            } else {
                hit = CharacterRepository.punch();
            }

            if (hit > 0) {
                session.setAttribute("lastAttackFailed", false);
                characterRepository.getFighterById(currentOpponent).takeHit(hit);
            } else {
                session.setAttribute("lastAttackFailed", true);
            }

            if (characterRepository.getFighterById(currentOpponent).getLife() == 0) {
                fight = false;
            } else {
                session.setAttribute("currentPlayer", currentOpponent);
            }
        }

        if(fight) {
            return "redirect:/game";
        } 
        else { 
            return "redirect:/index";
        }
    }

 /*   @PostMapping("/game") 
        public String game(HttpSession session, Model model, @RequestParam(required=false) String nickname1,@RequestParam(required=false) String nickname2, @RequestParam(required=false) String action) {
            if(session.getAttribute("nickname1") == null) {
                // first time we get here : check nickname
                if(nickname1 == null || nickname1.equals("")) {
                    // invalid nickname : go back to home page
                    return "redirect:/";
                }
                // register nickname in the session
                session.setAttribute("nickname1", nickname1);
                session.setAttribute("nickname2", nickname2);
                model.addAttribute("nickname1", nickname1);
                model.addAttribute("nickname2", nickname2);
            }
        return "game";
        
    }
*/
}