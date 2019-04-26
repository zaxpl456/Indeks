package com.projekt.wirtualny_indeks.index.controllers;

import com.projekt.wirtualny_indeks.index.models.Promoter;
import com.projekt.wirtualny_indeks.index.models.RegistrationEmployer;
import com.projekt.wirtualny_indeks.index.services.RegistrationEmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
public class RegistrationEmployerController {

    @Autowired
    RegistrationEmployerService registrationEmployerService;

    @Autowired
    public JavaMailSender emailSender;

    @GetMapping("/registrationAccess.html")
    public String showForm(Model model) {
        model.addAttribute("Person", new RegistrationEmployer());
        return "registrationAccess";
    }


    @PostMapping("/registrationAccess.html")
    public String savePromotor(@Valid @ModelAttribute("Person") RegistrationEmployer registrationEmployer) {
        String generatedString=createRandom(10,"ABCDEFGHIJKLMNOPQRST");
        registrationEmployer.setToken(generatedString);
        registrationEmployerService.saveRegistrationEmployer(registrationEmployer);

        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(registrationEmployer.getEmail());
        message.setSubject("REJESTRACJA PRACOWNIKA");
        message.setText("localhost:8080/registrationEmployerForm.html?token="+registrationEmployer.getToken());
        emailSender.send(message);

        return "redirect:emailSend.html";

    }

    public String createRandom(int codeLength,String id){
        return new SecureRandom()
                .ints(codeLength,0,id.length())
                .mapToObj(id::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
