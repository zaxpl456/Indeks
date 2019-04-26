package com.projekt.wirtualny_indeks.index.services;

import com.projekt.wirtualny_indeks.index.models.Recruitment;
import com.projekt.wirtualny_indeks.index.repositories.RecruitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitmentServiceImpl implements RecruitmentService {

    @Autowired
    RecruitmentRepository recruitmentRepository;

    @Override
    public List<Recruitment> getAllRecruited() {
        return recruitmentRepository.findAll();
    }

    @Override
    public List<Recruitment> getRecruitmetsByStudentId(Long id) {
        return recruitmentRepository.getRecruitmetsByStudentId(id);
    }

    @Override
    public Recruitment getRecruited(int id) {
        return recruitmentRepository.findById(id).get();
    }

    @Override
    public void deleteRecruited(int id) {
        recruitmentRepository.deleteById(id);
    }

    @Override
    public void saveRecruited(Recruitment recruitment) {
        recruitmentRepository.save(recruitment);
    }

    @Override
    public List<Recruitment> findAllByCourseIdAndStudentSemestr(int id, int semestr) {
        return recruitmentRepository.findAllByCourseIdAndStudentSemestr(id,semestr);
    }

    @Override
    public Recruitment findByStudentId(long id) {
        return recruitmentRepository.findByStudentId(id);
    }

    @Override
    public List<Recruitment> getUnchecked() {
        return recruitmentRepository.getUnchecked();
    }

    @Override
    public List<Recruitment> getChecked() {
        return recruitmentRepository.getCheecked();
    }


}
