package br.com.dbc.cooperativa.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

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

	private Voto voto;

	public AssembleiaAssociado() { }
	
	public AssembleiaAssociado(Assembleia assembleia, Associado associado, Voto voto) {		
		this.assembleia = assembleia;
		this.associado = associado;
		this.voto = voto;
		this.id = new AssembleiaAssociadoKey(assembleia.getId(), associado.getId());
	}
    
    public AssembleiaAssociadoKey getId() {
		return id;
	}

	public void setId(AssembleiaAssociadoKey id) {
		this.id = id;
	}

	public Assembleia getAssembleia() {
		return assembleia;
	}

	public void setAssembleia(Assembleia assembleia) {
		this.assembleia = assembleia;
	}

	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}

	public Voto getVoto() {
		return voto;
	}

	public void setVoto(Voto voto) {
		this.voto = voto;
	}    
}
