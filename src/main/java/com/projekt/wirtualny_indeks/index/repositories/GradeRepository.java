package com.projekt.wirtualny_indeks.index.repositories;

import com.projekt.wirtualny_indeks.index.models.Grade;
import com.projekt.wirtualny_indeks.index.models.Student;
import com.projekt.wirtualny_indeks.index.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade,Integer> {

    List<Student> findAllByCourseIdAndStudentSemestr(int courseId, int semestr);
    List<Grade> findAllByTermin(int termin);


}
