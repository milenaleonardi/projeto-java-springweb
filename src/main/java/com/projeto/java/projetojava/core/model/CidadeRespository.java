package com.projeto.java.projetojava.core.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRespository extends JpaRepository<Cidade, Long> {
}
