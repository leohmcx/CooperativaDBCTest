package br.com.dbc.cooperativa.controller.dto;

import org.springframework.data.domain.Page;

import br.com.dbc.cooperativa.model.Pauta;

public class PautaDto {

	private Long id;
	private String assunto;
	
	public PautaDto() {}
	
	public PautaDto(Pauta pauta) {
		this.id = pauta.getId();
		this.assunto = pauta.getAssunto();
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

	public static Page<PautaDto> converter(Page<Pauta> pautas) {
		return pautas.map(PautaDto::new);
	}
}
