package com.mk.myspaceweb.controller;

import com.mk.myspaceweb.data.entity.User;
import com.mk.myspaceweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/test")
    public String test(Model model) {
        userService.checkUserNameAndCreate("mkizhevsk@gmail.com");
        return "redirect:/users";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "user/create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{username}")
    public String showEditForm(@PathVariable String username, Model model) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user/edit";
        } else {
            return "redirect:/users";
        }
    }

    @PostMapping("/edit/{username}")
    public String updateUser(@PathVariable String username, @ModelAttribute User userDetails) {
        userService.updateUser(username, userDetails);
        return "redirect:/users";
    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return "redirect:/users";
    }
}
