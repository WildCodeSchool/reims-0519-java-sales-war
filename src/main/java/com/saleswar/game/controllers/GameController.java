package com.saleswar.game.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
class GameController {

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

    @PostMapping("/game") 
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

}