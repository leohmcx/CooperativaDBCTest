package br.com.dbc.cooperativa.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AssembleiaAssociadoKey implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "assembleia_id")
	private Long assembleiaId;
	@Column(name = "associado_id")
	private Long associadoId;
	
	public AssembleiaAssociadoKey() { }
			
	public AssembleiaAssociadoKey(Long assembleiaId, Long associadoId) {
		this.assembleiaId = assembleiaId;
		this.associadoId = associadoId;
	}

	public Long getAssembleiaId() {
		return assembleiaId;
	}

	public void setAssembleiaId(Long assembleiaId) {
		this.assembleiaId = assembleiaId;
	}

	public Long getAssociadoId() {
		return associadoId;
	}

	public void setAssociadoId(Long associadoId) {
		this.associadoId = associadoId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if(getClass() != o.getClass()) return false;
		AssembleiaAssociadoKey that = (AssembleiaAssociadoKey) o;
		return Objects.equals(assembleiaId, that.assembleiaId) && 
				Objects.equals(associadoId, that.associadoId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(assembleiaId, associadoId);
	}	
}
