package br.com.dbc.cooperativa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.dbc.cooperativa.exception.ResourceNotFoundException;
import br.com.dbc.cooperativa.model.Associado;
import br.com.dbc.cooperativa.model.dto.AssociadoDto;
import br.com.dbc.cooperativa.model.dto.DetalhesDoAssociadoDto;
import br.com.dbc.cooperativa.model.form.AssociadoForm;
import br.com.dbc.cooperativa.repository.AssociadoRepository;

@Service
public class AssociadoService {

	@Autowired
	private AssociadoRepository repository;
	
	public Page<AssociadoDto> obterTodos(String nome, Pageable paginacao) {
		Page<Associado> associados;
		if(nome == null) associados = repository.findAll(paginacao);
		else             associados = repository.findByNome(nome, paginacao);
		return AssociadoDto.converter(associados);		
	}
	
	public DetalhesDoAssociadoDto obterPorId(Long id) {
		Optional<Associado> one = repository.findById(id);
		Associado entity = one.orElseThrow(ResourceNotFoundException::new);
		return new DetalhesDoAssociadoDto(entity);
	}
	
	public AssociadoDto incluir(AssociadoForm form) {
		return new AssociadoDto(repository.save(form.converter(repository)));
	}
}
