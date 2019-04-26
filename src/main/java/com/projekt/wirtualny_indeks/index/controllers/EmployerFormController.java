package com.projekt.wirtualny_indeks.index.controllers;

import com.projekt.wirtualny_indeks.index.models.Adres;
import com.projekt.wirtualny_indeks.index.models.Employer;
import com.projekt.wirtualny_indeks.index.models.Role;
import com.projekt.wirtualny_indeks.index.models.User;
import com.projekt.wirtualny_indeks.index.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployerFormController {
    @Autowired
    private EmployerService employerService;

    @Autowired
    private AdresService adresService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RegistrationEmployerService registrationEmployerService;

    @GetMapping(value = "/registrationEmployerForm.html",params = "token")
    public String registrationEmployer(Model model,String token) {


        if(registrationEmployerService.findByToken(token)!=null) {
            model.addAttribute("regEmployer", new Employer());
            model.addAttribute("user", new User());
            model.addAttribute("adress", new Adres());

        }
        else {
            return "redirect:/";
        }

        return "registrationEmployerForm";
    }



    @PostMapping("/registrationEmployerForm.html")
    public String registrationEmployer(@Valid @ModelAttribute("regEmployer") Employer employer,@ModelAttribute("adress") Adres adres,@ModelAttribute("user") User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "registrationEmployerForm";
        }



        Role userRole = roleService.findRoleByTypeRole(Role.Types.ROLE_PRACOWNIK);
        List roles = Arrays.asList(userRole);
        user.setRoles(new HashSet<>(roles));
        userService.save(user);
        employer.setUser(user);
        employer.setAdres(adres);
        adresService.saveAdres(adres);
        employerService.saveEmployer(employer);
        registrationEmployerService.deleteRegistrationEmployer();




        return "registrationSuccess";
    }


}
