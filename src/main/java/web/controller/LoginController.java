package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import web.model.User;
import web.service.UserService;

import java.util.HashSet;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/reg")
    public String registration(@ModelAttribute("user") User user) {
        user.setRoles(new HashSet<>());
        if (user.getName().equals("admin")) {
            user.getRoles().add(userService.findRoleByName("ADMIN"));
        } else {
            user.getRoles().add(userService.findRoleByName("USER"));
        }
        userService.addUser(user);
        return "redirect:/login";
    }

    @GetMapping(value = "/reg")
    public String newUserPage(Model model) {
        model.addAttribute("user", new User());
        return "reg";
    }
}