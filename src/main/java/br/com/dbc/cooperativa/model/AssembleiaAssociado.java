package br.com.dbc.cooperativa.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AssembleiaAssociado {

	@EmbeddedId
	private AssembleiaAssociadoKey id;
	
	@ManyToOne
    @MapsId("assembleiaId")
    @JoinColumn(name = "assembleia_id")
    Assembleia assembleia;

	@ManyToOne
    @MapsId("associadoId")
    @JoinColumn(name = "associado_id")
    Associado associado;

	@Enumerated(EnumType.STRING)
	private Voto voto;

	public AssembleiaAssociado(Assembleia assembleia, Associado associado, Voto voto) {		
		this.assembleia = assembleia;
		this.associado = associado;
		this.voto = voto;
		this.id = new AssembleiaAssociadoKey(assembleia.getId(), associado.getId());
	}   
}
