package com.projekt.wirtualny_indeks.index.services;

import com.projekt.wirtualny_indeks.index.models.Subject;
import com.projekt.wirtualny_indeks.index.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubject(int id) {
        return subjectRepository.findById(id).get();

    }

    @Override
    public void deleteSubject(int id) {
        subjectRepository.deleteById(id);

    }

    @Override
    public void saveSubject(Subject subject) {
        subjectRepository.save(subject);

    }

    @Override
    public List<Subject> findAllByEmployersId(long id) {
        return subjectRepository.findAllByEmployersId(id);
    }


}
