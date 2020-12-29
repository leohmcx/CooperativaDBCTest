package br.com.dbc.cooperativa.model.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.dbc.cooperativa.model.AssembleiaAssociado;
import br.com.dbc.cooperativa.model.Voto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssembleiaAssociadoDto {

	private String associado;
	private Long assembleia;
	private String pauta;
	@Enumerated(EnumType.STRING)
	private Voto voto;	
	
	public AssembleiaAssociadoDto(AssembleiaAssociado assembleiaAssociado) {		
		this.associado = assembleiaAssociado.getAssociado().getNome();
		this.assembleia = assembleiaAssociado.getAssembleia().getId();
		this.pauta = assembleiaAssociado.getAssembleia().getPauta().getAssunto();
		this.voto = assembleiaAssociado.getVoto();
	}
}
