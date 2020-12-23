package br.com.dbc.cooperativa.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.dbc.cooperativa.model.Pauta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
}
