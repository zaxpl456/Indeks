package com.projekt.wirtualny_indeks.index.controllers;

import com.projekt.wirtualny_indeks.index.models.Role;
import com.projekt.wirtualny_indeks.index.models.Student;
import com.projekt.wirtualny_indeks.index.models.User;
import com.projekt.wirtualny_indeks.index.services.AdresService;
import com.projekt.wirtualny_indeks.index.services.RoleService;
import com.projekt.wirtualny_indeks.index.services.StudentService;
import com.projekt.wirtualny_indeks.index.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Controller
public class StudentRegistrationFormController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AdresService adresService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/registrationStudentForm.html")
    public String registration(Model model) {
        model.addAttribute("userCommand", new Student());
        return "registrationStudentForm";
    }

    @PostMapping("/registrationStudentForm.html")
    public String registration(@Valid @ModelAttribute("userCommand") Student userForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "registrationStudentForm";
        }

        User user = userService.getUserByName(principal.getName());

        Role userRole = roleService.findRoleByTypeRole(Role.Types.ROLE_STUDENT);
        List roles = Arrays.asList(userRole);
        user.setRoles(new HashSet<>(roles));

        userService.update(user);
        userForm.setUser(user);
        adresService.saveAdres(userForm.getAdres());
        studentService.saveStudent(userForm);
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
