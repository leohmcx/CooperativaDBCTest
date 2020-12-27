package br.com.dbc.cooperativa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.dbc.cooperativa.exception.ResourceNotFoundException;
import br.com.dbc.cooperativa.model.Pauta;
import br.com.dbc.cooperativa.model.dto.PautaDto;
import br.com.dbc.cooperativa.model.dto.DetalhesDaPautaDto;
import br.com.dbc.cooperativa.model.form.PautaForm;
import br.com.dbc.cooperativa.repository.PautaRepository;

@Service
public class PautaService {

	@Autowired
	private PautaRepository repository;
	
	public Page<PautaDto> obterTodos(String assunto, Pageable paginacao) {
		Page<Pauta> pautas;
		if(assunto == null) pautas = repository.findAll(paginacao);
		else             	pautas = repository.findByAssunto(assunto, paginacao);
		return PautaDto.converter(pautas);		
	}
	
	public DetalhesDaPautaDto obterPorId(Long id) {
		Optional<Pauta> one = repository.findById(id);
		Pauta entity = one.orElseThrow(ResourceNotFoundException::new);
		return new DetalhesDaPautaDto(entity);
	}
	
	public PautaDto incluir(PautaForm form) {
		return new PautaDto(repository.save(form.converter(repository)));
	}
}
