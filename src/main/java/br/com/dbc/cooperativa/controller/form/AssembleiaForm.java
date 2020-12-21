package br.com.dbc.cooperativa.controller.form;

import java.time.LocalDateTime;

import com.sun.istack.NotNull;

import br.com.dbc.cooperativa.model.Assembleia;
import br.com.dbc.cooperativa.model.Pauta;
import br.com.dbc.cooperativa.repository.AssembleiaRepository;

public class AssembleiaForm {

	@NotNull
	private Long pautaId;	
	private Integer duracao = new Integer(0);
	
	public Long getPautaId() {
		return pautaId;
	}

	public void setPautaId(Long pautaId) {
		this.pautaId = pautaId;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public Assembleia converter(AssembleiaRepository assembleiaRepository, Pauta p) {	
		return new Assembleia(p, LocalDateTime.now().plusMinutes(duracao));
	}
}
