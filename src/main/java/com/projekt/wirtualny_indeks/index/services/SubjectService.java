package com.projekt.wirtualny_indeks.index.services;

import com.projekt.wirtualny_indeks.index.models.Course;
import com.projekt.wirtualny_indeks.index.models.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getAllSubjects();


    Subject getSubject(int id);

    void deleteSubject(int id);

    void saveSubject(Subject subject);



    List<Subject> findAllByEmployersId(long id);







}
