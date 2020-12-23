package br.com.dbc.cooperativa.model.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.dbc.cooperativa.model.Assembleia;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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

	public static Page<AssembleiaDto> converter(Page<Assembleia> assembleia) {
		return assembleia.map(AssembleiaDto::new);
	}
}
