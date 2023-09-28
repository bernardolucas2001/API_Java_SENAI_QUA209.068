package br.com.bernardo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bernardo.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
