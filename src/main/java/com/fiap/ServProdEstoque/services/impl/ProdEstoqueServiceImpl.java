package com.fiap.ServProdEstoque.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fiap.ServProdEstoque.model.jpaStructure.ProdEstoqueJpa;
import com.fiap.ServProdEstoque.records.ProdEstoqueRecords;
import com.fiap.ServProdEstoque.repository.ProdEstoqueRepository;
import com.fiap.ServProdEstoque.services.interfaces.ProdEstoqueService;

@Service
public class ProdEstoqueServiceImpl implements ProdEstoqueService {

	private ProdEstoqueRepository repository;

	public ProdEstoqueServiceImpl(ProdEstoqueRepository repository) {
		this.repository = repository;
	}

	@Override
	public ProdEstoqueRecords AtualizarProduto(Integer idProduto, ProdEstoqueRecords objAtualizarProduto) {

		Optional<ProdEstoqueJpa> optJpa = repository.findById(idProduto);

		if (optJpa.isEmpty()) {

			return null;

		} else {

			optJpa.get().nomeProduto = objAtualizarProduto.nomeProduto();
			optJpa.get().qtdeDisponivel = objAtualizarProduto.qtdeDisponivel();

			ProdEstoqueJpa optJpaPreSaved = optJpa.get();

			ProdEstoqueJpa optJpaSaved = repository.save(optJpaPreSaved);

			return new ProdEstoqueRecords(optJpaSaved.idProduto, optJpaSaved.nomeProduto, optJpaSaved.qtdeDisponivel);

		}
	}

	@Override
	public ProdEstoqueRecords BuscarProdutoPorId(Integer idProduto) {
		Optional<ProdEstoqueJpa> optJpa = repository.findById(idProduto);

		if (optJpa.isEmpty()) {

			return null;

		} else {

			return new ProdEstoqueRecords(optJpa.get().idProduto, optJpa.get().nomeProduto,
					optJpa.get().qtdeDisponivel);

		}
	}

	@Override
	public ProdEstoqueRecords CriarProduto(ProdEstoqueRecords objCriarProduto) {
		ProdEstoqueJpa produtoJpa = new ProdEstoqueJpa();

		produtoJpa.nomeProduto = objCriarProduto.nomeProduto();
		produtoJpa.qtdeDisponivel = objCriarProduto.qtdeDisponivel();

		ProdEstoqueJpa optJpaSaved = repository.save(produtoJpa);

		return new ProdEstoqueRecords(optJpaSaved.idProduto, optJpaSaved.nomeProduto, optJpaSaved.qtdeDisponivel);
	}

	@Override
	public List<ProdEstoqueRecords> ListarProdutos() {
		List<ProdEstoqueJpa> lstJpa = repository.findAll();

		if (lstJpa.isEmpty()) {

			return null;

		} else {

			List<ProdEstoqueRecords> lstProdEstoque = new ArrayList<>();

			for (int i = 0; i <= lstJpa.size() - 1; i++) {

				ProdEstoqueRecords itemProdEstoque = new ProdEstoqueRecords(lstJpa.get(i).idProduto,
						lstJpa.get(i).nomeProduto, lstJpa.get(i).qtdeDisponivel);

				lstProdEstoque.add(itemProdEstoque);

			}

			return lstProdEstoque;

		}
	}
	
	@Override
	public void RemoverEstoque(Integer idProduto, Integer quantidade) {
		
		Optional<ProdEstoqueJpa> optJpa = repository.findById(idProduto);

		if (!optJpa.isEmpty()) {		
			
			if (quantidade <= optJpa.get().qtdeDisponivel && quantidade > 0){
				
				optJpa.get().qtdeDisponivel = optJpa.get().qtdeDisponivel - quantidade;
				
				ProdEstoqueJpa updateEstoqueJpa = optJpa.get();
				
				repository.save(updateEstoqueJpa);
			}

		} 	
	}

}
