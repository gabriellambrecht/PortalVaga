package com.feevale.portalvagas.repositories;

import com.feevale.portalvagas.entities.Candidatura;
import com.feevale.portalvagas.entities.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {
    List<Candidatura> findByVaga(Vaga vaga);
    List<Candidatura> findByVagaAndCandidato(Vaga vaga, String candidato);
}
