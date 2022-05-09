package com.generation.terradagente.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Atributo nome precisa ser inserido.")
	@Size(min = 5, max = 255, message = "Número mínimo de caracteres é 5 e número máximo de caracteres é 255.")
	private String nome;
	
	@NotNull(message = "Atributo quantidade precisa ser inserido.")
	private int quantidade;
	
	@Size(max=500, message = "Número máximo de caracteres é 500." )
	private String descricao;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@NotNull(message = "Preço é obrigatório.")
	@Positive(message = "O preço deve ser maior do que zero.")
	private BigDecimal preco;
	
	private String foto1;
	
	private String foto2;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@NotNull(message = "Peso é obrigatório.")
	@Positive(message = "O peso deve ser maior do que zero.")
	private BigDecimal peso;
	
	@NotNull(message = "Atributo ativo precisa ser inserido.")
	private boolean ativo;

	@NotNull(message = "Atributo perecível precisa ser inserido.")
	private boolean perecivel;
	
	// If perecivel == true 
	@Column(name = "data_fabricacao")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataFabricacao;

	@Column(name = "data_validade")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataValidade;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getFoto1() {
		return foto1;
	}

	public void setFoto1(String foto1) {
		this.foto1 = foto1;
	}

	public String getFoto2() {
		return foto2;
	}

	public void setFoto2(String foto2) {
		this.foto2 = foto2;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public boolean isPerecivel() {
		return perecivel;
	}

	public void setPerecivel(boolean perecivel) {
		this.perecivel = perecivel;
	}

	public LocalDate getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(LocalDate dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}
}
