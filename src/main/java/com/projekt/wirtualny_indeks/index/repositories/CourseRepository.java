package com.projekt.wirtualny_indeks.index.repositories;

import com.projekt.wirtualny_indeks.index.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Integer> {

    @Query("select c from Course c where c.typ like ?1 and c.rodzaj like ?2")
    List<Course> getFilteredCourses(String typ, String rodzaj);

}
