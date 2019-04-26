package com.projekt.wirtualny_indeks.index.services;

import com.projekt.wirtualny_indeks.index.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void save(User user);

    boolean isUniqueLogin(String login);

    User getUserByName(String name);

    void update(User user);
    boolean update(User user, String pass);
}
