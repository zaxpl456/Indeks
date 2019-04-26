package com.projekt.wirtualny_indeks.index.controllers;

import com.projekt.wirtualny_indeks.index.models.Recruitment;
import com.projekt.wirtualny_indeks.index.services.CourseService;
import com.projekt.wirtualny_indeks.index.services.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudentsListController {

    @Autowired
    RecruitmentService recruitmentService;

    @Autowired
    CourseService courseService;

    @RequestMapping(value="/studentsList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String recruitmentList(Model model){
        model.addAttribute("students", recruitmentService.getChecked());
        return "studentsList";
    }

    @GetMapping(path="/studentsList.html", params={"did"})
    public String deleteRecruitment(int did){
        recruitmentService.deleteRecruited(did);
        return "redirect:studentsList.html";
    }

    @RequestMapping(value="/studentsList.html", params = "id")
    public String acceptRecruitment(int id){
        Recruitment recruitment = recruitmentService.getRecruited(id);
        recruitment.setEnabled(true);
        recruitmentService.saveRecruited(recruitment);
        return "redirect:studentsList.html";
    }
}
