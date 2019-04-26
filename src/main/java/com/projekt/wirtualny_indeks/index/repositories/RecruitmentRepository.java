package com.projekt.wirtualny_indeks.index.repositories;

import com.projekt.wirtualny_indeks.index.models.Course;
import com.projekt.wirtualny_indeks.index.models.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment,Integer> {

    @Query("select r from Recruitment r where r.enabled = false")
    List<Recruitment> getUnchecked();

    @Query("select r from Recruitment r where r.enabled = true")
    List<Recruitment> getCheecked();

    @Query("select r from Recruitment r where r.student.id = ?1 and r.enabled = true")
    List<Recruitment> getRecruitmetsByStudentId(Long id);
    List<Recruitment> findAllByCourseIdAndStudentSemestr(int id, int semestr);

    Recruitment findByStudentId(long id);



}
