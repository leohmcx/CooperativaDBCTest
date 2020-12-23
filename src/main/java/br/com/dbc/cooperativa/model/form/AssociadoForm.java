package br.com.dbc.cooperativa.model.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import br.com.dbc.cooperativa.model.Associado;
import br.com.dbc.cooperativa.repository.AssociadoRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssociadoForm {

	@NotNull @NotEmpty @Length(min = 5)
	private String nome;
	@NotNull @NotEmpty @Length(min = 5)
	private String cpf;

	public Associado converter(AssociadoRepository associadoRepository) {
		return new Associado(nome, cpf);
	}
}
