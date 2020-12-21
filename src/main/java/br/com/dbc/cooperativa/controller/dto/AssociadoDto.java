package br.com.dbc.cooperativa.controller.dto;

import org.springframework.data.domain.Page;
import br.com.dbc.cooperativa.model.Associado;

public class AssociadoDto {

	private Long id;
	private String nome;

	public AssociadoDto() { }

	public AssociadoDto(Associado associado) {
		this.id = associado.getId();
		this.nome = associado.getNome();
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

	public static Page<AssociadoDto> converter(Page<Associado> associados) {
		return associados.map(AssociadoDto::new);
	}
}
