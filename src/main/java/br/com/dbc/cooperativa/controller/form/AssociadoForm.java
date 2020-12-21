package br.com.dbc.cooperativa.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import br.com.dbc.cooperativa.model.Associado;
import br.com.dbc.cooperativa.repository.AssociadoRepository;

public class AssociadoForm {

	@NotNull @NotEmpty @Length(min = 5)
	private String nome;
	@NotNull @NotEmpty @Length(min = 5)
	private String cpf;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Associado converter(AssociadoRepository associadoRepository) {
		return new Associado(nome, cpf);
	}
}
