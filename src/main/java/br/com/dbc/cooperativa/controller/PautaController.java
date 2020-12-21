package br.com.dbc.cooperativa.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.dbc.cooperativa.controller.dto.DetalhesDaPautaDto;
import br.com.dbc.cooperativa.controller.dto.PautaDto;
import br.com.dbc.cooperativa.controller.form.PautaForm;
import br.com.dbc.cooperativa.model.Pauta;
import br.com.dbc.cooperativa.repository.PautaRepository;

@RestController
@RequestMapping("/pauta")
public class PautaController {
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@GetMapping
	@Cacheable(value = "listaDePautas")
	public Page<PautaDto> lista(@RequestParam(required = false) String assuntoPauta
			, @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao){
				
		if(assuntoPauta == null) { 
			Page<Pauta> pautas = pautaRepository.findAll(paginacao);
			return PautaDto.converter(pautas);
		}
		else { 
			Page<Pauta> pautas = pautaRepository.findByAssunto(assuntoPauta, paginacao);
			return PautaDto.converter(pautas);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesDaPautaDto> detalhar(@PathVariable Long id) {
		Optional<Pauta> pauta = pautaRepository.findById(id);
		if(pauta.isPresent()) {
			return ResponseEntity.ok(new DetalhesDaPautaDto(pauta.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDePautas", allEntries = true)
	public ResponseEntity<PautaDto> cadastrar(@RequestBody @Valid PautaForm form, UriComponentsBuilder uriBuilder) {
		Pauta pauta = form.converter(pautaRepository);
		pautaRepository.save(pauta);		
		URI uri = uriBuilder.path("/pauta/{id}").buildAndExpand(pauta.getId()).toUri();
		return ResponseEntity.created(uri).body(new PautaDto(pauta));
	}
}
