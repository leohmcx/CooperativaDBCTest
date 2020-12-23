package br.com.dbc.cooperativa.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
	
	public Assembleia(Pauta pauta, LocalDateTime fim) {
		this.pauta = pauta;
		this.fim = fim != null? fim: this.fim;
	}
}
