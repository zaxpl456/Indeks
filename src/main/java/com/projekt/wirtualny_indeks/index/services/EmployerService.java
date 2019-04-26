package com.projekt.wirtualny_indeks.index.services;

import com.projekt.wirtualny_indeks.index.models.Employer;

import java.util.List;

public interface EmployerService {

    List<Employer> getAllEmployers();


    Employer getEmployers(long id);

    void deleteEmployer(long id);

    void saveEmployer(Employer employer);

    Employer findEmployerByUserId(long id);

    Employer getEmployerByUsername(String name);
}
