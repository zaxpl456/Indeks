package com.projekt.wirtualny_indeks.index.services;

import com.projekt.wirtualny_indeks.index.models.Course;

import java.util.List;

public interface CourseService {


    List<Course> getAllCourses();

    List<Course> getFilteredCourses(String typ, String rodzaj);

    Course getCourse(int id);

    void deleteCourse(int id);

    void saveCourse(Course course);

}
