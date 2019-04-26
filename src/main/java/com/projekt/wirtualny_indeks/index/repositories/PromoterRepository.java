package com.projekt.wirtualny_indeks.index.repositories;

import com.projekt.wirtualny_indeks.index.models.Promoter;
import com.projekt.wirtualny_indeks.index.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface PromoterRepository extends JpaRepository<Promoter,Integer> {

    Promoter findByStudentsId(long id);

    List<Promoter> findAllByCourseId(int id);
}
