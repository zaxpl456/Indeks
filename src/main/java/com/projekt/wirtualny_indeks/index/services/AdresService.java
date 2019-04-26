package com.projekt.wirtualny_indeks.index.services;

import com.projekt.wirtualny_indeks.index.models.Adres;

import java.util.List;

public interface AdresService {



    List<Adres> getAllAdreses();


    Adres getAdres(int id);

    void deleteAdres(int id);

    void saveAdres(Adres adres);

}
