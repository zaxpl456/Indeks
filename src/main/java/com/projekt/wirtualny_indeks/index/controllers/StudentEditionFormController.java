package com.projekt.wirtualny_indeks.index.controllers;

import com.projekt.wirtualny_indeks.index.models.Adres;
import com.projekt.wirtualny_indeks.index.models.Student;
import com.projekt.wirtualny_indeks.index.models.User;
import com.projekt.wirtualny_indeks.index.repositories.StudentRepository;
import com.projekt.wirtualny_indeks.index.services.AdresService;
import com.projekt.wirtualny_indeks.index.services.StudentService;
import com.projekt.wirtualny_indeks.index.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
public class StudentEditionFormController {
    @Autowired
    StudentService studentService;

    @Autowired
    UserService userService;

    @Autowired
    AdresService adresService;


    //Edycja Studenta
    @RequestMapping(value="/editStudentForm1.html", params = "id", method = RequestMethod.GET)
    public String showForm1(Model model, int id) {
        if(!studentService.getStudent(id).equals(null)){
            model.addAttribute("Student", studentService.getStudent(id));
        }
        return "editStudentForm1";
    }

    @PostMapping("/editStudentForm1.html")
    public String edit1(@Valid @ModelAttribute("Student") Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editStudentForm1";
        }
        studentService.updateStudent(student);
        return "redirect:studentPanel.html";
    }

    //Edycja adres
    @RequestMapping(value="/editStudentForm2.html", params = "id", method = RequestMethod.GET)
    public String showForm2(Model model, int id) {
        if(!studentService.getStudent(id).equals(null)){
            model.addAttribute("Adres", studentService.getStudent(id).getAdres());
        }
        return "editStudentForm2";
    }

    @PostMapping("/editStudentForm2.html")
    public String edit2(@Valid @ModelAttribute("Adres") Adres adres, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editStudentForm2";
        }
        adresService.saveAdres(adres);
        return "redirect:studentPanel.html";
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
