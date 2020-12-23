package br.com.dbc.cooperativa.model.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.dbc.cooperativa.model.Pauta;
import br.com.dbc.cooperativa.repository.PautaRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PautaForm {

	@NotNull @NotEmpty @Length(min = 5)
	private String assunto;
	@NotNull @NotEmpty @Length(min = 5)
	private String descricao;
	
	public Pauta converter(PautaRepository pautaRepository) {		
		return new Pauta(assunto, descricao);
	}
}
