package com.feevale.portalvagas.controllers;

import com.feevale.portalvagas.entities.Candidatura;
import com.feevale.portalvagas.entities.Vaga;
import com.feevale.portalvagas.repositories.CandidaturaRepository;
import com.feevale.portalvagas.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CandidaturaController {

    private final CandidaturaRepository canRepository;
    private final VagaRepository vagaRepository;

    @Autowired
    public CandidaturaController(CandidaturaRepository canRepository, VagaRepository vagaRepository) {
        this.canRepository = canRepository;
        this.vagaRepository = vagaRepository;
    }

    @GetMapping("/apply/{id}")
    public String apply(@PathVariable("id") long vagaId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Vaga vagaApplied = vagaRepository.findById(vagaId).get();
        Candidatura cand = new Candidatura();
        cand.setCandidato(currentPrincipalName);
        cand.setVaga(vagaApplied);
        canRepository.save(cand);
        return "redirect:/home";
    }

    @GetMapping("/vagas/candidatos/{id}")
    public String candidatos(@PathVariable("id") long id, Model model) {
        Vaga vaga = vagaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Codigo invalida de vaga:" + id));
        model.addAttribute("candidatos", canRepository.findByVaga(vaga));
        return "gestorRH/candidatos";
    }
}
