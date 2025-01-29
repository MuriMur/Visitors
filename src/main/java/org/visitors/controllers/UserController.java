package org.visitors.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.visitors.services.RoleService;
import org.visitors.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@RequestMapping("/users")
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    public UserController(UserService userService, RoleService roleService) {
        super();
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping("/view")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.listAllUsers());
        return "users_view";
    }

    @GetMapping
    public String listAllUsers(Model model) {
        model.addAttribute("users", userService.listAllUsers());
        return "users_view";
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteUser(@PathVariable Long id, Model model) {
        userService.userDelete(id);
        model.addAttribute("users", userService.listAllUsers());
        return new RedirectView("/users");
    }

    @GetMapping("/load/{id}")
    public String loadUser(@PathVariable Long id, Model model) {
        model.addAttribute("roles", roleService.listAllRoles());
        if (id > 0) {
            model.addAttribute("user", userService.getUserById(id));
        } else {
            model.addAttribute("user", userService.createUser());
        }
        return "user_edit";
    }

    @PostMapping(value = "/edit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView editUser(@RequestParam Long id,
                                 @RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam long roleId,
                                 @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date birthDate,
                                 @RequestParam String email,
                                 @RequestParam String password,
                                 Model model) {

        userService.userEdit(id, firstName, lastName, roleId, birthDate, email, password);
        System.out.println("successfully Saved!");
        return new RedirectView("/users");
    }

}