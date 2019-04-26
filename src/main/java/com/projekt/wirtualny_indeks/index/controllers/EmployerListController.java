package com.projekt.wirtualny_indeks.index.controllers;

import com.projekt.wirtualny_indeks.index.models.Employer;
import com.projekt.wirtualny_indeks.index.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class EmployerListController {

    @Autowired
    private EmployerService employerService;

    @Autowired
    private SubjectService subjectService;



    @GetMapping("/editEmployer.html" )
    public String editEmployer(Model model, Optional<Integer> id) {

        model.addAttribute("regEmployer", employerService.getEmployers(id.get()));



        return "editEmployer";
    }

    @PostMapping("/editEmployer.html" )
    public String editorEmployer(@Valid @ModelAttribute("regEmployer") Employer employer, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "editEmployer";
        }
        employer.setAdres(employerService.getEmployers(employer.getId()).getAdres());
        employer.setUser(employerService.getEmployers(employer.getId()).getUser());

        employerService.saveEmployer(employer);



        return "redirect:employerList.html";
    }
    @RequestMapping(value="/employerList.html", params = "id", method = RequestMethod.GET)
    public String showEmployerDetails(Model model, Integer id){
        model.addAttribute("Employer", employerService.getEmployers(id));
        model.addAttribute("Subjects", subjectService.findAllByEmployersId(id));
        return "employerDetails";
    }

    @RequestMapping(value="/employerList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showEmployerList(Model model){
        model.addAttribute("employerListPage", employerService.getAllEmployers());
        return "employerList";
    }

    @GetMapping(path="/employerList.html", params={"did"})
    public String deleteEmployer(int did, HttpServletRequest request){
        employerService.deleteEmployer(did);
        String queryString = prepareQueryString(request.getQueryString());
        return String.format("redirect:employerList.html%s", queryString);
    }

    private String prepareQueryString(String queryString) {
        if (queryString.contains("&")) {
            return "?"+queryString.substring(queryString.indexOf("&") + 1);
        }else{
            return "";
        }
    }
}
