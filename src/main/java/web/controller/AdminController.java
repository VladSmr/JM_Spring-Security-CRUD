package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin")
    public String allUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @RequestMapping(value = "/newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "createUser";
    }

    @PostMapping(value = "/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editUser(@PathVariable(name = "id") long id) {
        ModelAndView modelAndView = new ModelAndView("editUser");
        modelAndView.addObject("user", userService.findUser(id));
        return modelAndView;
    }

    @PostMapping(value = "/edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}