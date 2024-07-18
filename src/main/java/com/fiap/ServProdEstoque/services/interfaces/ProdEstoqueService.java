package com.fiap.ServProdEstoque.services.interfaces;

import java.util.List;

import com.fiap.ServProdEstoque.records.ProdEstoqueRecords;

public interface ProdEstoqueService {
	
	List<ProdEstoqueRecords> ListarProdutos();

	ProdEstoqueRecords BuscarProdutoPorId(Integer idProduto);

	ProdEstoqueRecords CriarProduto(ProdEstoqueRecords objCriarProduto);

	ProdEstoqueRecords AtualizarProduto(Integer idProduto, ProdEstoqueRecords objAtualizarProduto);
	
	void RemoverEstoque(Integer idProduto, Integer quantidade);

}
