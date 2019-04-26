package com.projekt.wirtualny_indeks.index.controllers;

import com.projekt.wirtualny_indeks.index.models.*;

import com.projekt.wirtualny_indeks.index.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.*;

@Controller
@SessionAttributes("Oceny")
public class GradesInputController {


    @Autowired
    RecruitmentService recruitmentService;

    @Autowired
    CourseService courseService;

    @Autowired
    SubjectService subjectService;



    @Autowired
    GradeService gradeService;


    @Autowired
    StudentService studentService;

    @RequestMapping(value="/gradeList.html", params = {"id","termin"}, method = RequestMethod.GET)
    public String showGradesList(Model model, Integer id,Integer termin){
        Grades oceny=new Grades();

        List<Grade> ocena=gradeService.findAllByTermin(termin);
        List<Recruitment> s=recruitmentService.findAllByCourseIdAndStudentSemestr(subjectService.getSubject(id).getCourse().getId(),subjectService.getSubject(id).getSemestr());
        if(ocena.size()!=s.size()) {
            for (int i = 0; i < s.size(); i++) {
                Grade grade = new Grade();
                grade.setStudent(s.get(i).getStudent());
                grade.setSubject(subjectService.getSubject(id));
                grade.setCourse(subjectService.getSubject(id).getCourse());
                grade.setTermin(termin);
                ocena.add(grade);

            }
            oceny.setGradeList(ocena);
        }
        else {

                oceny.setGradeList(ocena);

        }
        model.addAttribute("Oceny",oceny);

        return "gradeList";
    }




    @RequestMapping(value = "/gradeList.html",method = RequestMethod.POST)
    public String registrationEmployer(@Valid @ModelAttribute("Oceny")Grades grades, BindingResult bindingResult, SessionStatus status, WebRequest request) {
        if(bindingResult.hasErrors()){
            return "gradeLis";
        }

        List<Grade> ocena;

        ocena=grades.getGradeList();

        for(int i=0;i<ocena.size();i++){
            if(ocena.get(i).getOcena()>0) {
                gradeService.saveGrade(ocena.get(i));
            }
        }
        status.setComplete();
        request.removeAttribute("Oceny",WebRequest.SCOPE_SESSION);


        return "redirect:employerSubjectList.html";
    }



}
