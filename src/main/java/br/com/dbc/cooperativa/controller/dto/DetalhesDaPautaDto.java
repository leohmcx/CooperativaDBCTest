package br.com.dbc.cooperativa.controller.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.dbc.cooperativa.model.Pauta;

public class DetalhesDaPautaDto {

	private Long id;
	private String assunto;
	private String descricao;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataCriacao = LocalDateTime.now();
	
	public DetalhesDaPautaDto(Pauta pauta) {
		this.id = pauta.getId();
		this.assunto = pauta.getAssunto();
		this.descricao = pauta.getDescricao();
		this.dataCriacao = pauta.getDataCriacao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
}
