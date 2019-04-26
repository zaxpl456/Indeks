package com.projekt.wirtualny_indeks.index.services;


import com.projekt.wirtualny_indeks.index.models.Employer;
import com.projekt.wirtualny_indeks.index.repositories.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {
    @Autowired
    EmployerRepository employerRepository;

    @Override
    public List<Employer> getAllEmployers() {
        return employerRepository.findAll();
    }

    @Override
    public Employer getEmployers(long id) {
        return employerRepository.findById(id).get();
    }

    @Override
    public void deleteEmployer(long id) {
        employerRepository.deleteById(id);

    }

    @Override
    public void saveEmployer(Employer employer) {
        employerRepository.save(employer);

    }

    @Override
    public Employer findEmployerByUserId(long id) {
        return employerRepository.findEmployerByUserId(id);
    }

    @Override
    public Employer getEmployerByUsername(String name) {
        return employerRepository.getEmployerByUsername(name);
    }
}
