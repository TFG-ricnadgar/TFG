package etsii.tfg.DungeonRaiders.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    
    @GetMapping("/")
    public String index() {
        return "This is a Dungeon Raider welcome page";
    }
}
