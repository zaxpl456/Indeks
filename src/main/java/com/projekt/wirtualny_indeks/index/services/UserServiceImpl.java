package com.projekt.wirtualny_indeks.index.services;


import com.projekt.wirtualny_indeks.index.models.Role;
import com.projekt.wirtualny_indeks.index.repositories.RoleRepository;
import com.projekt.wirtualny_indeks.index.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    //bez adnotacji @Transactional sesja jest zamykana po wywołaniu findByUsername, co uniemożliwia dociągnięcie ról, gdyż fetch=EAGER.
    //ponadto, musi być włączone zarządzanie transakcjami @EnableTransactionManagement
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       com.projekt.wirtualny_indeks.index.models.User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return createUserDetails(user);
    }

    private UserDetails createUserDetails(com.projekt.wirtualny_indeks.index.models.User user) {
        Set<GrantedAuthority> grantedAuthorities =
                user.getRoles().stream().map(//mapowanie Role na GrantedAuthority
                        r -> new SimpleGrantedAuthority(r.getType().toString())
                ).collect(Collectors.toSet());

        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, grantedAuthorities);
    }

    @Override
    public void save(com.projekt.wirtualny_indeks.index.models.User user) {
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPasswordConfirm(null);//wyzerowanie jest potrzebne ze względu na walidację
        userRepository.saveAndFlush(user);
    }

    @Override
    public boolean isUniqueLogin(String username) {
        return userRepository.findByUsername(username) == null;
    }

    @Override
    public com.projekt.wirtualny_indeks.index.models.User getUserByName(String name) {
        com.projekt.wirtualny_indeks.index.models.User user = userRepository.findByUsername(name);
        if (user == null) {
            throw new UsernameNotFoundException(name);
        }
        return user;
    }

    @Override
    public boolean update(com.projekt.wirtualny_indeks.index.models.User user, String pass) {
        com.projekt.wirtualny_indeks.index.models.User user2 = userRepository.findById(user.getId()).get();
        if(passwordEncoder.matches(pass, user2.getPassword())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUsername(user2.getUsername());
            user.setRoles(user2.getRoles());
            user.setEnabled(user2.isEnabled());
            user.setPasswordConfirm(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }


    public void update(com.projekt.wirtualny_indeks.index.models.User user) {
        userRepository.saveAndFlush(user);
    }
}
