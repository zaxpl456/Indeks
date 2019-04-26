package com.projekt.wirtualny_indeks.index.controllers;

import com.projekt.wirtualny_indeks.index.models.Promoter;
import com.projekt.wirtualny_indeks.index.services.CourseService;
import com.projekt.wirtualny_indeks.index.services.EmployerService;
import com.projekt.wirtualny_indeks.index.services.PromoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Controller
public class PromoterFormController {

    @Autowired
    PromoterService promoterService;

    @Autowired
    EmployerService employerService;

    @Autowired
    CourseService courseService;

    @GetMapping("/promoterForm.html")
    public String showForm(Model model, Optional<Integer> id) {
        model.addAttribute("Promoter",
                id.isPresent()?
                        promoterService.findById(id.get()):
                        new Promoter());
        model.addAttribute("Employer",employerService.getAllEmployers());
        model.addAttribute("Course",courseService.getAllCourses());
        return "promoterForm";
    }

    @PostMapping("/promoterForm.html")
    public String savePromotor(@Valid @ModelAttribute("Promoter") Promoter promoter,BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "promoterForm";
        }



        promoterService.save(promoter);



        return "redirect:promoterList.html";
    }
}
