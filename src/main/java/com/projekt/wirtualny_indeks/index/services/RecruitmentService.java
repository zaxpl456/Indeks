package com.projekt.wirtualny_indeks.index.services;

import com.projekt.wirtualny_indeks.index.models.Course;
import com.projekt.wirtualny_indeks.index.models.Recruitment;
import com.projekt.wirtualny_indeks.index.models.Student;

import java.util.List;

public interface RecruitmentService {

    List<Recruitment> getAllRecruited();

    List<Recruitment> getRecruitmetsByStudentId(Long id);

    Recruitment getRecruited(int id);

    void deleteRecruited(int id);

    void saveRecruited(Recruitment recruitment);

    List<Recruitment> findAllByCourseIdAndStudentSemestr(int id, int semestr);

    Recruitment findByStudentId(long id);

    List<Recruitment> getUnchecked();

    List<Recruitment> getChecked();


}
