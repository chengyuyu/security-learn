package com.example.securitylearn.controller.exclude;


import com.example.securitylearn.config.MyPasswordEncoder;
import com.example.securitylearn.dao.UserRepository;
import com.example.securitylearn.domain.RegistrationFrom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/registry")
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MyPasswordEncoder myPasswordEncoder;

    @GetMapping
    public String registerForm(){
        log.info("===============>  registry  <===============");
        return "registration.html";
    }

    @PostMapping
    public String processRegistration(RegistrationFrom form){
        userRepository.save(form.toUser(myPasswordEncoder));
        return "redirect:/login";
    }
}
