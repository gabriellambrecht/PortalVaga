package com.feevale.portalvagas.controllers;

import com.feevale.portalvagas.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final VagaRepository vagaRepository;

    @Autowired
    public HomeController(VagaRepository vagaRepository) {
        this.vagaRepository = vagaRepository;
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model, Pageable pageable) {
        model.addAttribute("vagas", vagaRepository.findAll(pageable));
        if ( SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                //when Anonymous Authentication is enabled
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken) ){
            return "user/vagas-abertas";
        }

        return "vagas-publicas";

    }

}