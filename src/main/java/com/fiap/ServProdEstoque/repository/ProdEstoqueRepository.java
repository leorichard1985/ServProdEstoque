package com.fiap.ServProdEstoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.ServProdEstoque.model.jpaStructure.ProdEstoqueJpa;

@Repository
public interface ProdEstoqueRepository extends JpaRepository<ProdEstoqueJpa, Integer> {

}
