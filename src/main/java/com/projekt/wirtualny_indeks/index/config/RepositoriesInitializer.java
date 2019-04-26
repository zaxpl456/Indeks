package com.projekt.wirtualny_indeks.index.config;


import com.projekt.wirtualny_indeks.index.models.Role;
import com.projekt.wirtualny_indeks.index.models.User;
import com.projekt.wirtualny_indeks.index.repositories.RoleRepository;
import com.projekt.wirtualny_indeks.index.repositories.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class RepositoriesInitializer {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;


    @Bean
    InitializingBean init() {

        return () -> {

            if(roleRepository.findAll().isEmpty() == true){
                try {
                    Role roleUser = roleRepository.save(new Role(Role.Types.ROLE_USER));
                    Role roleStudent = roleRepository.save(new Role(Role.Types.ROLE_STUDENT));
                    Role roleAdmin = roleRepository.save(new Role(Role.Types.ROLE_KIEROWNIK));
                    Role roleDziekanat = roleRepository.save(new Role(Role.Types.ROLE_DZIEKANAT));
                    Role rolePracownik = roleRepository.save(new Role(Role.Types.ROLE_PRACOWNIK));

                    User user = new User("user", true);
                    user.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                    user.setPassword(passwordEncoder.encode("user"));

                    User student = new User("student", true);
                    student.setRoles(new HashSet<>(Arrays.asList(roleStudent)));
                    student.setPassword(passwordEncoder.encode("student"));

                    User admin = new User("admin", true);
                    admin.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
                    admin.setPassword(passwordEncoder.encode("admin"));

                    User dziekanat = new User("dziekanat", true);
                    dziekanat.setRoles(new HashSet<>(Arrays.asList(roleDziekanat, roleStudent)));
                    dziekanat.setPassword(passwordEncoder.encode("dziekanat"));

                    User pracownik = new User("pracownik", true);
                    pracownik.setRoles(new HashSet<>(Arrays.asList(roleAdmin, roleStudent)));
                    pracownik.setPassword(passwordEncoder.encode("pracownik"));

                    User test = new User("test", true);
                    test.setRoles(new HashSet<>(Arrays.asList(roleAdmin, roleUser,roleStudent,roleDziekanat,rolePracownik)));
                    test.setPassword(passwordEncoder.encode("test"));

                    userRepository.save(user);
                    userRepository.save(student);
                    userRepository.save(admin);
                    userRepository.save(dziekanat);
                    userRepository.save(pracownik);
                    userRepository.save(test);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        };


        };

    }


