package etsii.tfg.DungeonRaiders.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WelcomeController {

    @GetMapping(value = { "/", "/inicio" })
    public String index(Map<String, Object> model) {
        return "welcome";
    }
}
