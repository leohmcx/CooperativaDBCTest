package br.com.dbc.cooperativa.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.dbc.cooperativa.model.dto.DetalhesDaPautaDto;
import br.com.dbc.cooperativa.model.dto.PautaDto;
import br.com.dbc.cooperativa.model.form.PautaForm;
import br.com.dbc.cooperativa.service.PautaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Pauta")
@RestController
@RequestMapping("/pauta")
public class PautaController {
	
	@Autowired
	private PautaService service;
	
	@ApiOperation(value = "Obter Pauta por Id")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DetalhesDaPautaDto> obterPorId(@PathVariable Long id) {
		return ResponseEntity.ok(service.obterPorId(id));
	}
	
	@ApiOperation(value = "Obter todas as Pautas")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Cacheable(value = "listaDePautas")
	public ResponseEntity<Page<PautaDto>> obterTodos(@RequestParam(required = false) String assunto
			, @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao){
		return ResponseEntity.ok(service.obterTodos(assunto, paginacao));
	}
	
	@Transactional
	@ApiOperation(value = "Incluir Pauta")
	@CacheEvict(value = "listaDePautas", allEntries = true)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PautaDto> incluir(@RequestBody @Valid PautaForm form) {
		return new ResponseEntity<>(service.incluir(form), HttpStatus.CREATED);
	}
}
