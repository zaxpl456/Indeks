package com.projekt.wirtualny_indeks.index.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @NotBlank
    private String nazwa;

   @NotBlank
   private String typ;

   @NotBlank
   private String rodzaj;

   @NotNull
   private int liczba_semestrow;

   @Length(max=10000)
   @NotBlank
   private String opis;


}
