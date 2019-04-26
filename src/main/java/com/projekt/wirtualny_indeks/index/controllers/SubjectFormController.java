package com.projekt.wirtualny_indeks.index.controllers;

import com.projekt.wirtualny_indeks.index.models.Course;
import com.projekt.wirtualny_indeks.index.models.Subject;
import com.projekt.wirtualny_indeks.index.services.CourseService;
import com.projekt.wirtualny_indeks.index.services.EmployerService;
import com.projekt.wirtualny_indeks.index.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class SubjectFormController {
    @Autowired
    SubjectService subjectService;

    @Autowired
    EmployerService employerService;


    @Autowired
    CourseService courseService;

    @GetMapping("/subjectForm.html")
    public String showForm(Model model, Optional<Integer> id) {
        model.addAttribute("Subject",
                id.isPresent()?
                        subjectService.getSubject(id.get()):
                        new Subject());
        model.addAttribute("Employer",employerService.getAllEmployers());
        return "subjectForm";
    }

    @PostMapping("/subjectForm.html")
    public String saveSubject(@Valid @ModelAttribute("Subject") Subject subject, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "subjectForm";
        }
        subjectService.saveSubject(subject);

        return "redirect:subjectList.html";
    }

    @ModelAttribute("Courses")
    public List<Course> loadTypes(){
        List<Course> courses = courseService.getAllCourses();
        return courses;
    }


}
