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

import br.com.dbc.cooperativa.controller.dto.AssociadoDto;
import br.com.dbc.cooperativa.controller.dto.DetalhesDoAssociadoDto;
import br.com.dbc.cooperativa.controller.form.AssociadoForm;
import br.com.dbc.cooperativa.model.Associado;
import br.com.dbc.cooperativa.repository.AssociadoRepository;

@RestController
@RequestMapping("/associado")
public class AssociadoController {
	
	@Autowired
	private AssociadoRepository associadoRepository;
	
	@GetMapping
	@Cacheable(value = "listaDeAssociados")
	public Page<AssociadoDto> lista(@RequestParam(required = false) String nome
			, @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao){
				
		if(nome == null) { 
			Page<Associado> associados = associadoRepository.findAll(paginacao);
			return AssociadoDto.converter(associados);
		}
		else { 
			Page<Associado> associados = associadoRepository.findByNome(nome, paginacao);
			return AssociadoDto.converter(associados);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesDoAssociadoDto> detalhar(@PathVariable Long id) {
		Optional<Associado> associado = associadoRepository.findById(id);
		if(associado.isPresent()) {
			return ResponseEntity.ok(new DetalhesDoAssociadoDto(associado.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping	
	@Transactional
	@CacheEvict(value = "listaDeAssociados", allEntries = true)
	public ResponseEntity<AssociadoDto> cadastrar(@RequestBody @Valid AssociadoForm form, UriComponentsBuilder uriBuilder) {
		Associado associado = form.converter(associadoRepository);
		associadoRepository.save(associado);		
		URI uri = uriBuilder.path("/associado/{id}").buildAndExpand(associado.getId()).toUri();
		return ResponseEntity.created(uri).body(new AssociadoDto(associado));
	}
}
