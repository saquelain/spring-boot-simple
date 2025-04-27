package com.example.simple.controller;

import com.example.simple.model.User;
import com.example.simple.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Render the HTML page with the list of users
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users"; // Render users.html
    }

    // Handle user creation
    @PostMapping("/create")
    public String createUser(@RequestParam String name) {
        User user = new User(name);
        userRepository.save(user);
        return "redirect:/users"; // Redirect back to the users page
    }

    // Handle user deletion
    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userRepository.deleteById(id);
        return "redirect:/users"; // Redirect back to the users page
    }
}