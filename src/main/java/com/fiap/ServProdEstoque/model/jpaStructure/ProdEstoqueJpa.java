package com.fiap.ServProdEstoque.model.jpaStructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_MOV_ProdEstoque")
public class ProdEstoqueJpa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProduto")
	public Integer idProduto;

	@Column(name = "nomeProduto")
	public String nomeProduto;

	@Column(name = "qtdeDisponivel")
	public Integer qtdeDisponivel;

}
