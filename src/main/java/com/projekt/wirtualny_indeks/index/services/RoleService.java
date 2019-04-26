package com.projekt.wirtualny_indeks.index.services;

import com.projekt.wirtualny_indeks.index.models.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role findRoleByTypeRole(Role.Types type);
    Role getRole(int id);

    void deleteRole(int id);

    void saveRole(Role role);

}
