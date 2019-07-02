package com.feevale.portalvagas.controllers;

import com.feevale.portalvagas.entities.Vaga;
import com.feevale.portalvagas.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class VagaController2 {

    private final VagaRepository vagaRepository;

    @Autowired
    public VagaController2(VagaRepository vagaRepository) {
        this.vagaRepository = vagaRepository;
    }

    @GetMapping("/vagas")
    public String vagas(Model model, Pageable pageable) {
        model.addAttribute("vagas", vagaRepository.findAll(pageable));
        return "gestorRH/vagas";
    }

    @GetMapping("/vagas/adiciona")
    public String adicionaVagaPagina(Model model) {
        model.addAttribute("vaga", new Vaga());
        return "gestorRH/vagas-adiciona";
    }

    @PostMapping("/vagas")
    public String adicionaVaga(@Valid Vaga vaga, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "gestorRH/add-vaga";
        }
        vagaRepository.save(vaga);
        return "redirect:/vagas";
    }

    @GetMapping("/vagas/atualiza/{id}")
    public String atualizaVagaPagina(@PathVariable("id") long id, Model model) {
        Vaga vaga = vagaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Codigo invalida de vaga:" + id));
        model.addAttribute("vaga", vaga);
        return "gestorRH/vagas-atualiza";
    }

    @PostMapping("/vagas/atualiza/{id}")
    public String atualizaVaga(@Valid Vaga vaga, @PathVariable long id, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "gestorRH/update-vaga";
        }
        vaga.setCodigo(id);
        vagaRepository.save(vaga);
        return "redirect:/vagas";
    }

    @GetMapping("/vagas/delete/{id}")
    public String deletaVaga(@PathVariable("id") long id, Model model) {
        Vaga vaga = vagaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Codigo invalida de vaga:" + id));
        vagaRepository.delete(vaga);
        return "redirect:/vagas";
    }

}