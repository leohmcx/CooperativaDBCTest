package br.com.dbc.cooperativa.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class AssembleiaAssociadoKey implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "assembleia_id")
	private Long assembleiaId;
	@Column(name = "associado_id")
	private Long associadoId;
			
	public AssembleiaAssociadoKey(Long assembleiaId, Long associadoId) {
		this.assembleiaId = assembleiaId;
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
