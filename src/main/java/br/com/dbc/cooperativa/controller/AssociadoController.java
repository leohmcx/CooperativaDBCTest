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

import br.com.dbc.cooperativa.model.dto.AssociadoDto;
import br.com.dbc.cooperativa.model.dto.DetalhesDoAssociadoDto;
import br.com.dbc.cooperativa.model.form.AssociadoForm;
import br.com.dbc.cooperativa.service.AssociadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Associado")
@RestController
@RequestMapping("/associado")
public class AssociadoController {
	
	@Autowired
	private AssociadoService service;
	
	@ApiOperation(value = "Obter Associado por Id")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DetalhesDoAssociadoDto> obterPorId(@PathVariable Long id) {
		return ResponseEntity.ok(service.obterPorId(id));
	}
	
	@ApiOperation(value = "Obter todos os Associados")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Cacheable(value = "listaDeAssociados")
	public ResponseEntity<Page<AssociadoDto>> obterTodos(@RequestParam(required = false) String nome
			, @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao){
		return ResponseEntity.ok(service.obterTodos(nome, paginacao));
	}	
	
	@Transactional
	@ApiOperation(value = "Incluir Associado")
	@CacheEvict(value = "listaDeAssociados", allEntries = true)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<AssociadoDto> incluir(@RequestBody @Valid AssociadoForm form) {
		return new ResponseEntity<>(service.incluir(form), HttpStatus.CREATED);
	}
}
