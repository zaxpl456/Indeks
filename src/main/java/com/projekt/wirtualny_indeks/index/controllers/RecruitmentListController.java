package com.projekt.wirtualny_indeks.index.controllers;

import com.projekt.wirtualny_indeks.index.models.Recruitment;
import com.projekt.wirtualny_indeks.index.services.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RecruitmentListController {

    @Autowired
    RecruitmentService recruitmentService;

    @RequestMapping(value="/recruitmentList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String recruitmentList(Model model){
        model.addAttribute("recruitments", recruitmentService.getUnchecked());
        return "recruitmentList";
    }

    @GetMapping(path="/recruitmentList.html", params={"did"})
    public String deleteRecruitment(int did){
        recruitmentService.deleteRecruited(did);
        return "redirect:recruitmentList.html";
    }

    @RequestMapping(value="/recruitmentList.html", params = "id")
    public String acceptRecruitment(int id){
        Recruitment recruitment = recruitmentService.getRecruited(id);
        recruitment.setEnabled(true);
        recruitmentService.saveRecruited(recruitment);
        return "redirect:recruitmentList.html";
    }
}
