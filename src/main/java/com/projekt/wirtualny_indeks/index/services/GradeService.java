package com.projekt.wirtualny_indeks.index.services;

import com.projekt.wirtualny_indeks.index.models.Grade;
import com.projekt.wirtualny_indeks.index.models.Student;

import java.util.List;

public interface GradeService {

    List<Grade> getAllGrades();


    Grade getGrade(int id);

    void deleteGrade(int id);

    void saveGrade(Grade grade);

    List<Grade> findAllByTermin(int termin);


}
