package com.projekt.wirtualny_indeks.index.services;


import com.projekt.wirtualny_indeks.index.models.Student;
import com.projekt.wirtualny_indeks.index.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);

    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void updateStudent(Student student) {
        Student check = studentRepository.findById(student.getId()).get();

        if(student.getAdres()==null){
            student.setAdres(check.getAdres());
        }

        if(student.getUser()==null){
            student.setUser(check.getUser());
        }

        studentRepository.save(student);
    }

    @Override
    public Student getStudentByUsername(String name) {
        return studentRepository.getStudentByUsername(name);
    }
}
