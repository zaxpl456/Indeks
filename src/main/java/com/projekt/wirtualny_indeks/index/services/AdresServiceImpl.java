package com.projekt.wirtualny_indeks.index.services;


import com.projekt.wirtualny_indeks.index.models.Adres;
import com.projekt.wirtualny_indeks.index.repositories.AdresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdresServiceImpl implements AdresService {

    @Autowired
    AdresRepository adresRepository;

    @Override
    public List<Adres> getAllAdreses() {
        return adresRepository.findAll();
    }

    @Override
    public Adres getAdres(int id) {
        return adresRepository.findById(id).get();
    }

    @Override
    public void deleteAdres(int id) {
        adresRepository.deleteById(id);

    }

    @Override
    public void saveAdres(Adres adres) {
        adresRepository.save(adres);

    }
}
