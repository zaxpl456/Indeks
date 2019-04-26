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
@Table(name = "promoter")
@Getter
@Setter
@NoArgsConstructor
public class Promoter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employer_id",nullable = false)
    private Employer employer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id",nullable = false)
    private Course course;


    @NotNull
    private int liczba_miejsc;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="promotor_employers",
            joinColumns = @JoinColumn(name = "promoter_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students=new HashSet<>();

}
