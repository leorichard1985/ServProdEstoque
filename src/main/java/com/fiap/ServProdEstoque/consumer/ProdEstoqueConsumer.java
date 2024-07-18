package com.fiap.ServProdEstoque.consumer;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fiap.ServProdEstoque.services.interfaces.ProdEstoqueService;

@Component
public class ProdEstoqueConsumer {

	private final ProdEstoqueService service;

	public ProdEstoqueConsumer(ProdEstoqueService service) {
		this.service = service;
	}

	@Bean(name = "remova-estoque")
	Consumer<ProdutoReqCompra> consumer() {
		return pRequest -> this.service.RemoverEstoque(pRequest.getIdProduto(), pRequest.getQuantidade());
	}
	
	
}
