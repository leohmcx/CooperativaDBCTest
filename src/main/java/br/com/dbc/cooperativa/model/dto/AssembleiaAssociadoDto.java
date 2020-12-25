package br.com.dbc.cooperativa.model.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.dbc.cooperativa.model.Assembleia;
import br.com.dbc.cooperativa.model.AssembleiaAssociado;
import br.com.dbc.cooperativa.model.Associado;
import br.com.dbc.cooperativa.model.Voto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssembleiaAssociadoDto {

	private Associado associado;
	private Assembleia assembleia;
	@Enumerated(EnumType.STRING)
	private Voto voto;	
	
	public AssembleiaAssociadoDto(AssembleiaAssociado assembleiaAssociado) {		
		this.associado = assembleiaAssociado.getAssociado();
		this.assembleia = assembleiaAssociado.getAssembleia();
		this.voto = assembleiaAssociado.getVoto();
	}
}
