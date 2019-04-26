package com.projekt.wirtualny_indeks.index.repositories;

import com.projekt.wirtualny_indeks.index.models.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface EmployerRepository extends JpaRepository<Employer,Long> {
    Employer findEmployerByUserId(long id);

    @Query("select e from Employer e where e.user.username = ?1")
    Employer getEmployerByUsername(String username);
}
