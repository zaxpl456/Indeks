package com.projekt.wirtualny_indeks.index.services;


import com.projekt.wirtualny_indeks.index.models.RegistrationEmployer;
import com.projekt.wirtualny_indeks.index.repositories.RegistrationEmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationEmployerServiceImpl implements RegistrationEmployerService {

    @Autowired
    RegistrationEmployerRepository registrationEmployerRepository;

    @Override
    public List<RegistrationEmployer> findAll() {
        return registrationEmployerRepository.findAll();
    }

    @Override
    public RegistrationEmployer getRegistrationEmployer(int id) {
        return registrationEmployerRepository.getOne(id);
    }

    @Override
    public void saveRegistrationEmployer(RegistrationEmployer registrationEmployer) {
        registrationEmployerRepository.save(registrationEmployer);
    }

    @Override
    public void deleteRegistrationEmployer() {
        registrationEmployerRepository.deleteAll();

    }

    @Override
    public RegistrationEmployer findByToken(String string) {
        return registrationEmployerRepository.findByToken(string);
    }
}
