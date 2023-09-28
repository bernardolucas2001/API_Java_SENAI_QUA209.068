package com.bernardo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bernardo.model.Pessoa;
import com.bernardo.repository.PessoaRepository;





@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@GetMapping
	public List<Pessoa> listarPessoas() {
		
		List<Pessoa> pessoas = pessoaRepository.findAll();
		
		return pessoas;
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
		return pessoaRepository.findById(id)
				.map(objetoGravado -> ResponseEntity.ok().body(objetoGravado))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> cadastrarPessoa(@RequestBody Pessoa pessoa) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaRepository.save(pessoa));
	}
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizarProduto(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		return pessoaRepository.findById(id).map(objetoGravado -> {
			objetoGravado.setNome(pessoa.getNome());
			objetoGravado.setCpf(pessoa.getCpf());
			objetoGravado.setDataNascimento(pessoa.getDataNascimento());
			objetoGravado.setEndereco(pessoa.getEndereco());
			objetoGravado.setTelefone(pessoa.getTelefone());
			Pessoa pessoaAtualizado = pessoaRepository.save(objetoGravado);
			return ResponseEntity.ok().body(pessoaAtualizado);
		}).orElse(ResponseEntity.notFound().build());
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
		return pessoaRepository.findById(id).map(objetoGravado -> {
			pessoaRepository.deleteById(id);
			return ResponseEntity.noContent().<Void>build();
		}).orElse(ResponseEntity.notFound().build());
		
	}
	

}
