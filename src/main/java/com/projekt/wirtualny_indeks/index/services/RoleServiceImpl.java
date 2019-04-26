package com.projekt.wirtualny_indeks.index.services;

import com.projekt.wirtualny_indeks.index.models.Role;
import com.projekt.wirtualny_indeks.index.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRoleByTypeRole(Role.Types type) {
        return roleRepository.findRoleByType(type);
    }

    @Override
    public Role getRole(int id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public void deleteRole(int id) {
        roleRepository.deleteById(id);

    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);

    }
}
