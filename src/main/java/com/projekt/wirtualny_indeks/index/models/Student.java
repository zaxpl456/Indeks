package com.projekt.wirtualny_indeks.index.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="students")
@Getter
@Setter
@NoArgsConstructor
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 2,max = 20)
    private String imie;

    @NotBlank
    @Size(min = 2,max = 50)
    private String nazwisko;

    @NotBlank
    @Size(min = 9,max = 11)
    private String pesel;

    @Temporal(TemporalType.DATE)
    @Past
    private Date data_urodzenia;

    @Size(min = 9)
    private String telefon;

    @Email
    private String email;

    @Min(0)
    private int semestr;

    @Valid
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Valid
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "adres_id",nullable = false)
    private Adres adres;


}
