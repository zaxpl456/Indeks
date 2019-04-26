package com.projekt.wirtualny_indeks.index.controllers;

import com.projekt.wirtualny_indeks.index.models.User;
import com.projekt.wirtualny_indeks.index.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserEdtitionFormController {
    @Autowired
    private UserService userService;

    @GetMapping("/userEditionForm.html")
    public String registration(Model model, Principal name) {
        if(!userService.getUserByName(name.getName()).equals(null)){
            User user = userService.getUserByName(name.getName());
            user.setPassword("");
            user.setUsername("tymczasowe");
            model.addAttribute("userCommand", user);
            model.addAttribute("pass","");
        }
        return "userEditionForm";
    }

    @PostMapping("/userEditionForm.html")
    public String registration(@ModelAttribute("userCommand") User userForm, @ModelAttribute("pass") String pass, Principal name) {
        userForm.setId(userService.getUserByName(name.getName()).getId());
        if(userService.update(userForm,pass)) return "editionSuccess";
        return "userEditionForm";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //aby użytkownik nie mógł sobie wstrzyknąć aktywacji konta oraz ról (np., ADMIN)
        //roles są na wszelki wypadek, bo warstwa serwisów i tak ustawia ROLE_USER dla nowego usera
        binder.setDisallowedFields("enabled", "roles");
    }
}
