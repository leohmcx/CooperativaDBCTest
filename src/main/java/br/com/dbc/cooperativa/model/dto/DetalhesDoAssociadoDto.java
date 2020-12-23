package br.com.dbc.cooperativa.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.dbc.cooperativa.model.Associado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
}
