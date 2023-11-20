package com.itgroup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth/users")
public class AuthorizationController {

    @PostMapping("")
    public String registration() {
        return null;
    }

    @PostMapping("/login")
    public String login() {
        return null;
    }

    @PostMapping("/logout")
    public String logout() {
        return null;
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id) {
        return null;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id) {
        return null;
    }
}
