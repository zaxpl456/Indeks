package com.projekt.wirtualny_indeks.index.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="grades")
@Getter
@Setter
@NoArgsConstructor
public class Grade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;



  private int termin;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id",nullable = false)
    private Course course;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id",nullable = false)
    private Subject subject;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id",nullable = false)
    private Student student;

 private double ocena;



}
