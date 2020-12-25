package br.com.dbc.cooperativa.model.form;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import br.com.dbc.cooperativa.model.Assembleia;
import br.com.dbc.cooperativa.model.AssembleiaAssociado;
import br.com.dbc.cooperativa.model.Associado;
import br.com.dbc.cooperativa.model.Voto;
import br.com.dbc.cooperativa.repository.AssembleiaRepository;
import br.com.dbc.cooperativa.repository.AssociadoRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssembleiaAssociadoForm {

	@NotNull @NotEmpty
	private Long associadoId;
	@NotNull @NotEmpty
	private Long assembleiaId;
	@NotNull @NotEmpty @Enumerated(EnumType.STRING)
	private Voto voto;
	
	public AssembleiaAssociadoForm(@NotEmpty Long associadoId, @NotEmpty Long assembleiaId, @NotEmpty Voto voto) {
		this.associadoId = associadoId;
		this.assembleiaId = assembleiaId;
		this.voto = voto;
	}

	public AssembleiaAssociado converter(AssembleiaRepository ar, AssociadoRepository arr, Assembleia assembleia, Associado associado, Voto voto) {
		return new AssembleiaAssociado(assembleia, associado, voto);
	}
}