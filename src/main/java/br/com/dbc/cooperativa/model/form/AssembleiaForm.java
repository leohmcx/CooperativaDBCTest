package br.com.dbc.cooperativa.model.form;

import java.time.LocalDateTime;

import com.sun.istack.NotNull;

import br.com.dbc.cooperativa.model.Assembleia;
import br.com.dbc.cooperativa.model.Pauta;
import br.com.dbc.cooperativa.repository.AssembleiaRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssembleiaForm {

	@NotNull
	private Long pautaId;	
	private Integer duracao = new Integer(0);

	public Assembleia converter(AssembleiaRepository assembleiaRepository, Pauta p) {	
		return new Assembleia(p, LocalDateTime.now().plusMinutes(duracao));
	}
}
