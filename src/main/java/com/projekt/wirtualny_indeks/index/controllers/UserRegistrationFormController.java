package com.projekt.wirtualny_indeks.index.controllers;

import com.projekt.wirtualny_indeks.index.models.Adres;
import com.projekt.wirtualny_indeks.index.models.Employer;
import com.projekt.wirtualny_indeks.index.models.Role;
import com.projekt.wirtualny_indeks.index.models.User;
import com.projekt.wirtualny_indeks.index.services.AdresService;
import com.projekt.wirtualny_indeks.index.services.EmployerService;
import com.projekt.wirtualny_indeks.index.services.RoleService;
import com.projekt.wirtualny_indeks.index.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by grzesiek on 23.08.2017.
 */
@Controller
public class UserRegistrationFormController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;



    @GetMapping("/registrationForm.html")
    public String registration(Model model) {
        model.addAttribute("userCommand", new User());
        return "registrationForm";
    }


    @PostMapping("/registrationForm.html")
    public String registration(@Valid @ModelAttribute("userCommand") User userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registrationForm";
        }
        Role userRole = roleService.findRoleByTypeRole(Role.Types.ROLE_USER);
        List roles = Arrays.asList(userRole);
        userForm.setRoles(new HashSet<>(roles));

        userService.save(userForm);
        return "registrationSuccess";
    }




    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //aby użytkownik nie mógł sobie wstrzyknąć aktywacji konta oraz ról (np., ADMIN)
        //roles są na wszelki wypadek, bo warstwa serwisów i tak ustawia ROLE_USER dla nowego usera
        binder.setDisallowedFields("enabled", "roles");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, false);
        binder.registerCustomEditor(Date.class, "date", dateEditor);
    }




}