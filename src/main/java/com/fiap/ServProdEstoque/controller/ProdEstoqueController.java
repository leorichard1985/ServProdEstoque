package com.fiap.ServProdEstoque.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.ServProdEstoque.records.ProdEstoqueRecords;
import com.fiap.ServProdEstoque.services.interfaces.ProdEstoqueService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/produto")
public class ProdEstoqueController {

	@Autowired
	private ProdEstoqueService service;

	@GetMapping
	public CompletableFuture<ResponseEntity<Object>> ListarProdutos() {

		try {

			List<ProdEstoqueRecords> produto = service.ListarProdutos();

			if (Objects.isNull(produto)) {
				return CompletableFuture.completedFuture(ResponseEntity.noContent().build());
			} else {
				return CompletableFuture.completedFuture(ResponseEntity.ok().body(produto));
			}

		} catch (DataIntegrityViolationException e) {

			return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(e));
		}
	}

	@GetMapping("/{idProduto}")
	public CompletableFuture<ResponseEntity<Object>> BuscarProdutoPorId(
			@PathVariable("idProduto") Integer idProduto) {

		try {

			ProdEstoqueRecords produto = service.BuscarProdutoPorId(idProduto);

			if (Objects.isNull(produto)) {
				return CompletableFuture.completedFuture(ResponseEntity.noContent().build());
			} else {
				return CompletableFuture.completedFuture(ResponseEntity.ok().body(produto));
			}

		} catch (DataIntegrityViolationException e) {

			return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(e));
		}

	}

	@PostMapping()
	@Transactional
	public CompletableFuture<ResponseEntity<Object>> CriarCliente(@RequestBody ProdEstoqueRecords objCriarProduto)
			throws URISyntaxException {

		try {

			ProdEstoqueRecords produto = service.CriarProduto(objCriarProduto);
			
			if (Objects.isNull(produto)) {
				return CompletableFuture.completedFuture(ResponseEntity.noContent().build());
			} else {
				return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).body(produto));
			}

			

		} catch (DataIntegrityViolationException e) {

			return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(e));
		}

	}

	@PutMapping("/{idProduto}")
	public CompletableFuture<ResponseEntity<Object>> AtualizarCliente(@PathVariable("idProduto") Integer idProduto,
			@RequestBody ProdEstoqueRecords objAtualizarProduto) {

		try {

			ProdEstoqueRecords produto = service.AtualizarProduto(idProduto, objAtualizarProduto);

			if (Objects.isNull(produto)) {
				return CompletableFuture.completedFuture(ResponseEntity.noContent().build());
			} else {
				return CompletableFuture.completedFuture(ResponseEntity.ok().body(produto));
			}

		} catch (DataIntegrityViolationException e) {

			return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(e));
		}
	}
	
	
}
