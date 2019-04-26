package com.projekt.wirtualny_indeks.index.controllers;


import com.projekt.wirtualny_indeks.index.models.Course;
import com.projekt.wirtualny_indeks.index.models.Recruitment;
import com.projekt.wirtualny_indeks.index.services.CourseService;
import com.projekt.wirtualny_indeks.index.services.RecruitmentService;
import com.projekt.wirtualny_indeks.index.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
/** Kontroller odpowiedzialny za wyświetlanie listy Kierunków i usuwanie kierunku */
public class CourseListController {

    @Autowired
    CourseService courseService;

    @Autowired
    StudentService studentService;

    @Autowired
    RecruitmentService recruitmentService;

    @RequestMapping(value="/studentCourseList.html", method = {RequestMethod.GET, RequestMethod.POST})
    /** Metoda mająca na celu zwrócenie listy kierunków studenta
     * @param principal Parametr zwracający obecnie zalogowanego studenta */
    public String showCourseList(Model model, Principal principal){
        List<Recruitment> list = recruitmentService.getRecruitmetsByStudentId(studentService.getStudentByUsername(principal.getName()).getId());
        model.addAttribute("courseListPage", list);
        return "studentCourseList";
    }

    @RequestMapping(value="/courseList.html", method = {RequestMethod.GET, RequestMethod.POST})
    /** Metoda zwracająca listę wszystkich kierunków */
    public String showCourseList(Model model){
        model.addAttribute("courseListPage", courseService.getAllCourses());
        return "courseList";
    }

    @Secured("ROLE_DZIEKANAT")
    @GetMapping(path="/courseList.html", params={"did"})
    /** Metoda usuwająca kierunek z bazy danych
     * @param did id kierunku do usunięcia */
    public String deleteCourse(int did, HttpServletRequest request){
        courseService.deleteCourse(did);
        String queryString = prepareQueryString(request.getQueryString());
        return String.format("redirect:courseList.html%s", queryString);
    }


    @RequestMapping(value="/courseList.html", params = "id", method = RequestMethod.GET)
    /** Metoda dodająca do modelu obiekt Kierunku  */
    public String showCourseDetails(Model model, Integer id){
        model.addAttribute("course", courseService.getCourse(id));
        return "courseDetails";
    }

    private String prepareQueryString(String queryString) {
        if (queryString.contains("&")) {
            return "?"+queryString.substring(queryString.indexOf("&") + 1);
        }else{
            return "";
        }
    }

}
