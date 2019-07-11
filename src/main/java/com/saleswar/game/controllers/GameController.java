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
        model.addAttribute("currentPlayer", session.getAttribute("currentPlayer").equals(1) ? "Player 1" : "Grand Mère");
        model.addAttribute("lifeP1", characterRepository.getFighterById(1).getLife());
        model.addAttribute("lifeP2", characterRepository.getFighterById(3).getLife());

        return "game";
    }

    @PostMapping("/game")
    public String fight(HttpSession session, @RequestParam(required = false) String attack) {

        boolean fight = true;

        if (attack != null) {

            int currentPlayer = 1;
            int currentOpponent = 3;
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
                characterRepository.getFighterById(currentPlayer).takeHit(hit/2);
            } else {
                session.setAttribute("lastAttackFailed", true);
            }

            if (characterRepository.getFighterById(currentOpponent).getLife() == 0) {
                fight = false;
            } 
            if (characterRepository.getFighterById(currentPlayer).getLife() == 0) {
                return "redirect:/loose";
            }
            else {
                session.setAttribute("currentPlayer", currentOpponent);
            }
            
        }

        if(fight) {
            return "redirect:/game";
        } 
        else { 
            return "redirect:/win";
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