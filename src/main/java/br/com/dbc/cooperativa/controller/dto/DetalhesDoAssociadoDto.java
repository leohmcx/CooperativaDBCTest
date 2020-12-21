package br.com.dbc.cooperativa.controller.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.dbc.cooperativa.model.Associado;

public class DetalhesDoAssociadoDto {

	private Long id;
	private String nome;
	private String cpf;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	public DetalhesDoAssociadoDto(Associado associado) {
		this.id = associado.getId();
		this.nome = associado.getNome();
		this.cpf = associado.getCpf();
		this.dataCadastro = associado.getDataCadastro();
	}
	
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
