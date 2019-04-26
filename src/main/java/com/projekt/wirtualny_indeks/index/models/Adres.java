package com.projekt.wirtualny_indeks.index.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "adreses")
public class Adres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String miejscowosc;


    private String ulica;

    @NotNull
    private int numer;


    private String kraj;


    private String kod_pocztowy;



}
