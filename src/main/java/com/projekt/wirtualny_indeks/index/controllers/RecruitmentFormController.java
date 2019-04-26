package com.projekt.wirtualny_indeks.index.controllers;

import com.projekt.wirtualny_indeks.index.models.Course;
import com.projekt.wirtualny_indeks.index.models.Recruitment;
import com.projekt.wirtualny_indeks.index.services.CourseService;
import com.projekt.wirtualny_indeks.index.services.RecruitmentService;
import com.projekt.wirtualny_indeks.index.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

import static javax.servlet.http.HttpUtils.getRequestURL;

@Controller
public class RecruitmentFormController {

    @Autowired
    CourseService courseService;

    @Autowired
    StudentService studentService;

    @Autowired
    RecruitmentService recruitmentService;

    String pom;

    @RequestMapping(value="/courseTypeSelect.html")
    public String selection1() {
        return "courseTypeSelect";
    }

    @RequestMapping(value="/courseTypeSelect2.html", params = "tid", method = RequestMethod.GET)
    public String selection2(Model model, int tid) {
        model.addAttribute("tid", tid);
        return "courseTypeSelect2";
    }

    @RequestMapping(value="/recruitmentForm.html", params = {"tid", "rid"}, method = RequestMethod.GET)
    public String showForm(Model model, int tid, int rid, HttpServletRequest request) {
        pom = request.getQueryString();
        if(tid==1) {
            if(rid==1) {
                model.addAttribute("kursy", courseService.getFilteredCourses("Stacjonarne", "Inżynierskie"));
            }
            if(rid==3) {
                model.addAttribute("kursy", courseService.getFilteredCourses("Stacjonarne", "Licencjackie"));
            }
            model.addAttribute("rekrutacja", new Recruitment());
        }
        if(tid==2){
            if(rid==1) {
                model.addAttribute("kursy", courseService.getFilteredCourses("Niestacjonarne", "Inżynierskie"));
            }
            if(rid==3) {
                model.addAttribute("kursy", courseService.getFilteredCourses("Niestacjonarne", "Licencjackie"));
            }
            model.addAttribute("rekrutacja", new Recruitment());
        }
        return "recruitmentForm";
    }

    @PostMapping("/recruitmentForm.html")
    public String edit1(@Valid @ModelAttribute("rekrutacja")Recruitment recruitment, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "redirect:recruitmentForm.html?"+pom;
        }
        recruitment.setStudent(studentService.getStudentByUsername(principal.getName()));
        recruitmentService.saveRecruited(recruitment);

        return "recruitmentSuccess.html";
    }

    @RequestMapping(value="/recruitmentForm2.html", params = {"tid", "rid"}, method = RequestMethod.GET)
    public String showForm2(Model model, int tid, int rid, HttpServletRequest request) {
        pom = request.getQueryString();
        if(tid==1) {
            if(rid==2) {
                model.addAttribute("kursy", courseService.getFilteredCourses("Stacjonarne", "Magisterskie"));
            }
            Recruitment recruitment = new Recruitment();
            recruitment.setWynik1(0);
            recruitment.setWynik2(0);
            model.addAttribute("rekrutacja", recruitment);
        }
        if(tid==2){
            if(rid==2) {
                model.addAttribute("kursy", courseService.getFilteredCourses("Niestacjonarne", "Magisterskie"));
            }
            Recruitment recruitment = new Recruitment();
            recruitment.setWynik1(0);
            recruitment.setWynik2(0);
            model.addAttribute("rekrutacja", recruitment);
        }
        return "recruitmentForm2";
    }

    @PostMapping("/recruitmentForm2.html")
    public String edit2(@ModelAttribute("rekrutacja") Recruitment recruitment, Principal principal) {
        if (recruitment.getCourse().getId()<=0) {
            return "redirect:recruitmentForm2.html?"+pom;
        }
        if(studentService.getStudentByUsername(principal.getName()).getSemestr()==6 || studentService.getStudentByUsername(principal.getName()).getSemestr()==7) {
            recruitment.setStudent(studentService.getStudentByUsername(principal.getName()));
            recruitmentService.saveRecruited(recruitment);
            return "recruitmentFailed.html";
        }
        return "recruitmentSuccess.html";
    }


}
