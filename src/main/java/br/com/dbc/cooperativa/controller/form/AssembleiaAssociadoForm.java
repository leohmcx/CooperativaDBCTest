package br.com.dbc.cooperativa.controller.form;

import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import br.com.dbc.cooperativa.model.Assembleia;
import br.com.dbc.cooperativa.model.AssembleiaAssociado;
import br.com.dbc.cooperativa.model.Associado;
import br.com.dbc.cooperativa.model.Voto;
import br.com.dbc.cooperativa.repository.AssembleiaRepository;
import br.com.dbc.cooperativa.repository.AssociadoRepository;

public class AssembleiaAssociadoForm {

	@NotNull @NotEmpty
	private Long associadoId;
	@NotNull @NotEmpty
	private Long assembleiaId;
	@NotNull @NotEmpty
	private Voto voto;
	
	public AssembleiaAssociadoForm(@NotEmpty Long associadoId, @NotEmpty Long assembleiaId, @NotEmpty Voto voto) {
		this.associadoId = associadoId;
		this.assembleiaId = assembleiaId;
		this.voto = voto;
	}

	public Long getAssociadoId() {
		return associadoId;
	}

	public void setAssociadoId(Long associadoId) {
		this.associadoId = associadoId;
	}

	public Long getAssembleiaId() {
		return assembleiaId;
	}

	public void setAssembleiaId(Long assembleiaId) {
		this.assembleiaId = assembleiaId;
	}

	public Voto getVoto() {
		return voto;
	}

	public void setVoto(Voto voto) {
		this.voto = voto;
	}

	public AssembleiaAssociado converter(AssembleiaRepository ar, AssociadoRepository arr, Assembleia assembleia, Associado associado, Voto voto) {
		return new AssembleiaAssociado(assembleia, associado, voto);
	}
}
