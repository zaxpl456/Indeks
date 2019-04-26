package com.projekt.wirtualny_indeks.index.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name="employers")
@Getter
@Setter
@NoArgsConstructor
public class Employer {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Size(min = 2,max = 20)
        private String tytul;

        @Size(min = 2,max = 20)
        private String stanowisko;


        @Size(min = 2,max = 20)
        private String imie;


        @Size(min = 2,max = 50)
        private String nazwisko;


        @Size(min = 9,max = 11)
        private String pesel;

        @Temporal(TemporalType.DATE)
        @Past
        private Date data_urodzenia;

        @Size(min = 9)
        private String telefon;


        private String email;


        @Valid
        @OneToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id",nullable = false)
        private User user;

        @Valid
        @OneToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "adres_id",nullable = false)
        private Adres adres;


    }
