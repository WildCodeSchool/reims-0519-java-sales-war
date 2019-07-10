package com.saleswar.game.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


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

}