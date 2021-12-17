package com.api.projeto.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.projeto.model.Produto;
import com.api.projeto.repository.ProdutosRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = "*")
@RestController

@RequestMapping("/produto")
@Api(value="API REST Produtos")
public class ProdutosController {

	@Autowired
	ProdutosRepository produtosRepository;
	
	@ApiOperation(value="Retorna uma lista de Produtos")
	@GetMapping
	public List<Produto> list() {
		
		return  produtosRepository.findAll();
		
	//Get	
	}
	
	@ApiOperation(value="Retorna o produto por id")
	@GetMapping("/{id}")
	public ResponseEntity<Produto> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Produto> produto = produtosRepository.findById(id);
        if(produto.isPresent())
            return new ResponseEntity<Produto>(produto.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	//Post
	@ApiOperation(value="Salva o produto")
	@PostMapping
	@org.springframework.transaction.annotation.Transactional
	public Produto Criar(@RequestBody Produto produto ){
		return produtosRepository.save(produto);
	}
	
	//PUT
	@ApiOperation(value="atualiza o produto")
	@RequestMapping("/{id}")
    public ResponseEntity<Produto> Put(@PathVariable(value = "id") long id, @RequestBody Produto newProduto)
    {
        Optional<Produto> oldProduto = produtosRepository.findById(id);
        if(oldProduto.isPresent()){
            Produto produto = oldProduto.get();
            produto.setName(newProduto.getName());
            produtosRepository.save(produto);
            return new ResponseEntity<Produto>(produto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	//Delete
	@ApiOperation(value="Apaga o produto por id")
	@DeleteMapping("/{id}")
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Produto> produto = produtosRepository.findById(id);
        if(produto.isPresent()){
            produtosRepository.delete(produto.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
