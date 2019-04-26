package com.projekt.wirtualny_indeks.index.services;

import com.projekt.wirtualny_indeks.index.models.RegistrationEmployer;

import java.util.List;

public interface RegistrationEmployerService {

    List<RegistrationEmployer> findAll();

    RegistrationEmployer getRegistrationEmployer(int id);

    void saveRegistrationEmployer(RegistrationEmployer registrationEmployer);

    void deleteRegistrationEmployer();

    RegistrationEmployer findByToken(String string);

}
