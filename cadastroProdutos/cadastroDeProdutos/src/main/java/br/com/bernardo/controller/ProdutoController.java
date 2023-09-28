package br.com.bernardo.controller;

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

import br.com.bernardo.model.Produto;
import br.com.bernardo.repository.ProdutoRepository;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	  /*@GetMapping("/olamundo")
	  public String olaMundo() {
		  return "Olá Mundo";
	  }*/
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<Produto> listarProdutos() {
		/*Produto p1 = new Produto("Celular", 45, 2555.79);
		Produto p2 = new Produto("Cafeteira arno", 20, 199.99);
		Produto p3 = new Produto("Mouse sem fio", 200, 10.95);
		
		return Arrays.asList(p1, p2, p3);*/
	    List<Produto> produtos = produtoRepository.findAll();
	    return produtos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Long id) {
		return produtoRepository.findById(id)
				.map(objetoGravado -> ResponseEntity.ok().body(objetoGravado))
				.orElse(ResponseEntity.notFound().build());
	}
	@PostMapping
	public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
		return produtoRepository.findById(id).map(objetoGravado -> {
			objetoGravado.setNomeProduto(produto.getNomeProduto());
			objetoGravado.setQuantidade(produto.getQuantidade());
			objetoGravado.setPreco(produto.getPreco());
			Produto produtoAtualizado = produtoRepository.save(objetoGravado);
			return ResponseEntity.ok().body(produtoAtualizado);
		}).orElse(ResponseEntity.notFound().build());
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
		return produtoRepository.findById(id).map(objetoGravado -> {
			produtoRepository.deleteById(id);
			return ResponseEntity.noContent().<Void>build();
		}).orElse(ResponseEntity.notFound().build());
		
	}
	
	
	
	
}
