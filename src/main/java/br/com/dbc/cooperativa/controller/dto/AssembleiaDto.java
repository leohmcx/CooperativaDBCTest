package br.com.dbc.cooperativa.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.dbc.cooperativa.model.Assembleia;

public class AssembleiaDto {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inicio;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime fim;
	private Long id;
	private PautaDto pauta;

	public AssembleiaDto(Assembleia assembleia) {
		this.inicio = assembleia.getInicio();
		this.fim = assembleia.getFim();
		this.id = assembleia.getId();
		this.pauta = new PautaDto(assembleia.getPauta());
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getFim() {
		return fim;
	}

	public void setFim(LocalDateTime fim) {
		this.fim = fim;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PautaDto getPauta() {
		return pauta;
	}

	public void setPauta(PautaDto pauta) {
		this.pauta = pauta;
	}

	public static Page<AssembleiaDto> converter(Page<Assembleia> assembleia) {
		return assembleia.map(AssembleiaDto::new);
	}
}
