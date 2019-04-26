package com.projekt.wirtualny_indeks.index.controllers;


import com.projekt.wirtualny_indeks.index.models.*;
import com.projekt.wirtualny_indeks.index.services.PromoterService;
import com.projekt.wirtualny_indeks.index.services.RecruitmentService;
import com.projekt.wirtualny_indeks.index.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PromoterListController {

    @Autowired
    PromoterService promoterService;

    @Autowired
    StudentService studentService;

    @Autowired
    RecruitmentService recruitmentService;


    @RequestMapping(value="/promoterList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showPromoterList(Model model){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String name=authentication.getName();
        Student student=studentService.getStudentByUsername(name);
        if(student!=null) {
            Recruitment recruitment = recruitmentService.findByStudentId(student.getId());
            List<Promoter> promoters = promoterService.findAllByCourseId(recruitment.getCourse().getId());
            Boolean check = false;
            for (int i = 0; i < promoters.size(); i++) {
                if (promoters.get(i).getStudents().contains(student)) {
                    model.addAttribute("promoterListPage", promoters.get(i));
                    check = true;
                    model.addAttribute("check", check);
                    break;
                }
            }

            if (student.getSemestr() == 6 && check != true) {
                model.addAttribute("promoterListPage", promoterService.findAll());
                model.addAttribute("check", check);
            }

        }
        else {
            model.addAttribute("promoterListPage",promoterService.findAll());
        }
        return "promoterList";
    }

    @GetMapping(path="/promoterStudent.html", params={"id"})
    public String savePromoter(int id, HttpServletRequest request){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String name=authentication.getName();
        Promoter promoter=promoterService.findById(id);
        promoter.getStudents().add(studentService.getStudentByUsername(name));
        promoter.setLiczba_miejsc(promoter.getLiczba_miejsc()-1);
        promoterService.save(promoter);
        return "redirect:promoterList.html";
    }

    @GetMapping(path="/promoterStudentDelete.html", params={"did"})
    public String deletePromoter(int did, HttpServletRequest request){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String name=authentication.getName();
        Promoter promoter=promoterService.findById(did);
        promoter.getStudents().remove(studentService.getStudentByUsername(name));
        promoter.setLiczba_miejsc(promoter.getLiczba_miejsc()+1);
        promoterService.save(promoter);
        return "redirect:promoterList.html";
    }
}
