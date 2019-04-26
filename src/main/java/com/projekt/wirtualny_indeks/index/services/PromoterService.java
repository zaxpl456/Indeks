package com.projekt.wirtualny_indeks.index.services;

import com.projekt.wirtualny_indeks.index.models.Promoter;
import com.projekt.wirtualny_indeks.index.models.Student;

import java.util.List;

public interface PromoterService {

    List<Promoter> findAll();

    Promoter findById(int id);

    void save(Promoter promoter);

    void delete(int id);

    Promoter findByStudentId(long id);

    List<Promoter> findAllByCourseId(int id);



}
