package br.com.dbc.cooperativa.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.dbc.cooperativa.model.dto.AssembleiaAssociadoDto;
import br.com.dbc.cooperativa.model.dto.AssembleiaAssociadoVoto;
import br.com.dbc.cooperativa.model.dto.AssembleiaDto;
import br.com.dbc.cooperativa.model.form.AssembleiaAssociadoForm;
import br.com.dbc.cooperativa.model.form.AssembleiaForm;
import br.com.dbc.cooperativa.service.AssembleiaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Assembleia")
@RestController
@RequestMapping("/assembleia")
public class AssembleiaController {

	@Autowired 
	private AssembleiaService service;

	@ApiOperation(value = "Obter resultado da Assembleia por Id")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AssembleiaAssociadoVoto> obterPorId(@PathVariable Long id) {
		return ResponseEntity.ok(service.obterPorId(id));
	}
	
	@ApiOperation(value = "Obter todas as Assembleias")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Cacheable(value = "listaDeAssembleias")
	public Page<AssembleiaDto> obterTodos(@RequestParam(required = false) String assunto,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return service.obterTodos(assunto, paginacao);
	}
	
	@Transactional
	@ApiOperation(value = "Incluir Assembleia")
	@CacheEvict(value = "listaDeAssembleias", allEntries = true)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AssembleiaDto> incluir(@RequestBody AssembleiaForm form) {
		return new ResponseEntity<>(service.incluir(form), HttpStatus.CREATED);
	}	

	@Transactional
	@ApiOperation(value = "Votar em Assembleia")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AssembleiaAssociadoDto> votar(@RequestBody AssembleiaAssociadoForm form) {
		return ResponseEntity.ok(service.votar(form));
	}
}
