package com.projekt.wirtualny_indeks.index.services;

import com.projekt.wirtualny_indeks.index.models.Promoter;
import com.projekt.wirtualny_indeks.index.models.Student;
import com.projekt.wirtualny_indeks.index.repositories.PromoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromoterServiceImpl implements PromoterService {

    @Autowired
    PromoterRepository promoterRepository;

    @Override
    public List<Promoter> findAll() {
        return promoterRepository.findAll();
    }

    @Override
    public Promoter findById(int id) {
        return promoterRepository.findById(id).get();
    }

    @Override
    public void save(Promoter promoter) {
        promoterRepository.save(promoter);

    }

    @Override
    public void delete(int id) {
        promoterRepository.deleteById(id);

    }

    @Override
    public Promoter findByStudentId(long id) {
        return promoterRepository.findByStudentsId(id);
    }

    @Override
    public List<Promoter> findAllByCourseId(int id) {
        return promoterRepository.findAllByCourseId(id);
    }


}
