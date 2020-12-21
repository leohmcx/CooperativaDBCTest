package br.com.dbc.cooperativa.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Assembleia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "pauta_id")
	private Pauta pauta;
	private LocalDateTime inicio = LocalDateTime.now();
	private LocalDateTime fim = LocalDateTime.now().plusMinutes(1);
	
	public Assembleia() { }
	
	public Assembleia(Pauta pauta, LocalDateTime fim) {
		this.pauta = pauta;
		this.fim = fim != null? fim: this.fim;
	}
	
	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getFim() {
		return fim;
	}

	public void setFim(LocalDateTime fim) {
		this.fim = fim;
	}
}
