package com.projekt.wirtualny_indeks.index.controllers;

import com.projekt.wirtualny_indeks.index.services.EmployerService;
import com.projekt.wirtualny_indeks.index.services.StudentService;
import com.projekt.wirtualny_indeks.index.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class PanelsController {

    @Autowired
    StudentService studentService;

    @Autowired
    EmployerService employerService;

    @RequestMapping(value="/studentPanel.html")
    public String panel1(Model model, Principal principal){
        model.addAttribute("student",studentService.getStudentByUsername(principal.getName()));
        return "studentPanel";
    }

    @RequestMapping(value="/employerPanel.html")
    public String panel2(Model model, Principal principal){
        model.addAttribute("pracownik",employerService.getEmployerByUsername(principal.getName()));
        return "employerPanel";
    }

    @RequestMapping(value="/managerPanel.html")
    public String panel3(){
        return "managerPanel";
    }

    @RequestMapping(value="/deansOfficePanel.html")
    public String panel4(){
        return "deansOfficePanel";
    }


}
