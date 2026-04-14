package org.example.backend.controllers;


import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DefultController {

    @GetMapping("/get")
    public String defult() {
        return "Funguje to";
    }

}
