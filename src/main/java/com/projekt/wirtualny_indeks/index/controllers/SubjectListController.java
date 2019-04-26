package com.projekt.wirtualny_indeks.index.controllers;


import com.projekt.wirtualny_indeks.index.models.User;
import com.projekt.wirtualny_indeks.index.repositories.RoleRepository;
import com.projekt.wirtualny_indeks.index.services.EmployerService;
import com.projekt.wirtualny_indeks.index.services.SubjectService;
import com.projekt.wirtualny_indeks.index.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SubjectListController {
    @Autowired
    SubjectService subjectService;

    @Autowired
    EmployerService employerService;

    @Autowired
    UserService userService;



    @RequestMapping(value="/subjectList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showSubjectsList(Model model){

        model.addAttribute("subjectListPage", subjectService.getAllSubjects());
        return "subjectList";
    }

    @RequestMapping(value="/employerSubjectList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showEmployerSubjectsList(Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User u=  userService.getUserByName(authentication.getName());
        employerService.findEmployerByUserId(u.getId()).getImie();

        model.addAttribute("subjectListPage", subjectService.findAllByEmployersId(employerService.findEmployerByUserId(u.getId()).getId()));
        return "employerSubjectList";
    }

    @GetMapping(path="/subjectList.html", params={"did"})
    public String deleteSubject(int did, HttpServletRequest request){
        subjectService.deleteSubject(did);
        String queryString = prepareQueryString(request.getQueryString());
        return String.format("redirect:subjectList.html%s", queryString);
    }


    @RequestMapping(value="/subjectList.html", params = "id", method = RequestMethod.GET)
    public String showSubjectDetails(Model model, Integer id){
        model.addAttribute("Subject", subjectService.getSubject(id));
        model.addAttribute("Employers",employerService.getAllEmployers());
        return "subjectDetails";
    }

    private String prepareQueryString(String queryString) {
        if (queryString.contains("&")) {
            return "?"+queryString.substring(queryString.indexOf("&") + 1);
        }else{
            return "";
        }
    }
}
