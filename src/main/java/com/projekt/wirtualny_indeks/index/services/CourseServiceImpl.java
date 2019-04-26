package com.projekt.wirtualny_indeks.index.services;


import com.projekt.wirtualny_indeks.index.models.Course;
import com.projekt.wirtualny_indeks.index.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getFilteredCourses(String typ, String rodzaj) {
        return courseRepository.getFilteredCourses(typ,rodzaj);
    }

    @Override
    public Course getCourse(int id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public void deleteCourse(int id) {
        courseRepository.deleteById(id);

    }

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);

    }
}
