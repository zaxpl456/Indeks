package com.projekt.wirtualny_indeks.index.services;

import com.projekt.wirtualny_indeks.index.models.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();


    Student getStudent(long id);

    void deleteStudent(long id);

    void saveStudent(Student student);

    void updateStudent(Student student);

    Student getStudentByUsername(String name);
}
