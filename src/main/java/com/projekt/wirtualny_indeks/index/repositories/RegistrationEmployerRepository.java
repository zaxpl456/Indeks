package com.projekt.wirtualny_indeks.index.repositories;

import com.projekt.wirtualny_indeks.index.models.RegistrationEmployer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationEmployerRepository extends JpaRepository<RegistrationEmployer,Integer> {

    RegistrationEmployer findByToken(String string);
}
