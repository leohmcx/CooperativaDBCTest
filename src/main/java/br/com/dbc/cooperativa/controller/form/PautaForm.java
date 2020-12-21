package br.com.dbc.cooperativa.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import br.com.dbc.cooperativa.model.Pauta;
import br.com.dbc.cooperativa.repository.PautaRepository;

public class PautaForm {

	@NotNull @NotEmpty @Length(min = 5)
	private String assunto;
	@NotNull @NotEmpty @Length(min = 5)
	private String descricao;

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Pauta converter(PautaRepository pautaRepository) {		
		return new Pauta(assunto, descricao);
	}
}
