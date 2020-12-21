package br.com.dbc.cooperativa.controller.dto;

import br.com.dbc.cooperativa.model.Assembleia;
import br.com.dbc.cooperativa.model.AssembleiaAssociado;
import br.com.dbc.cooperativa.model.Associado;
import br.com.dbc.cooperativa.model.Voto;

public class AssembleiaAssociadoDto {

	private Associado associado;
	private Assembleia assembleia;
	private Voto voto;	
	
	public AssembleiaAssociadoDto(AssembleiaAssociado assembleiaAssociado) {		
		this.associado = assembleiaAssociado.getAssociado();
		this.assembleia = assembleiaAssociado.getAssembleia();
		this.voto = assembleiaAssociado.getVoto();
	}

	public Associado getAssociado() {
		return associado;
	}
	
	public void setAssociado(Associado associado) {
		this.associado = associado;
	}
	
	public Assembleia getAssembleia() {
		return assembleia;
	}
	
	public void setAssembleia(Assembleia assembleia) {
		this.assembleia = assembleia;
	}
	
	public Voto getVoto() {
		return voto;
	}

	public void setVoto(Voto voto) {
		this.voto = voto;
	}
}
