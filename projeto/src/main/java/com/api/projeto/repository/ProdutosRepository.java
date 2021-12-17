package com.api.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.projeto.model.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Long> { 

}


