package br.com.dbc.cooperativa.model.dto;

import org.springframework.data.domain.Page;

import br.com.dbc.cooperativa.model.Associado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssociadoDto {

	private Long id;
	private String nome;

	public AssociadoDto(Associado associado) {
		this.id = associado.getId();
		this.nome = associado.getNome();
	}

	public static Page<AssociadoDto> converter(Page<Associado> associados) {
		return associados.map(AssociadoDto::new);
	}
}
