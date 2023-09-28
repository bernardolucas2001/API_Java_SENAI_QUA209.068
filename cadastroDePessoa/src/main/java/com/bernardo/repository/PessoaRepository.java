package com.bernardo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardo.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
