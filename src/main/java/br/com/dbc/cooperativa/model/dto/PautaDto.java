package br.com.dbc.cooperativa.model.dto;

import org.springframework.data.domain.Page;

import br.com.dbc.cooperativa.model.Pauta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PautaDto {

	private Long id;
	private String assunto;
	
	public PautaDto(Pauta pauta) {
		this.id = pauta.getId();
		this.assunto = pauta.getAssunto();
	}

	public static Page<PautaDto> converter(Page<Pauta> pautas) {
		return pautas.map(PautaDto::new);
	}
}
