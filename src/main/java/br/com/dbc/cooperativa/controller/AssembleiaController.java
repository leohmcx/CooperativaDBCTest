package br.com.dbc.cooperativa.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.dbc.cooperativa.model.Assembleia;
import br.com.dbc.cooperativa.model.AssembleiaAssociado;
import br.com.dbc.cooperativa.model.Associado;
import br.com.dbc.cooperativa.model.Pauta;
import br.com.dbc.cooperativa.model.dto.AssembleiaAssociadoDto;
import br.com.dbc.cooperativa.model.dto.AssembleiaAssociadoVoto;
import br.com.dbc.cooperativa.model.dto.AssembleiaDto;
import br.com.dbc.cooperativa.model.form.AssembleiaAssociadoForm;
import br.com.dbc.cooperativa.model.form.AssembleiaForm;
import br.com.dbc.cooperativa.repository.AssembleiaAssociadoRepository;
import br.com.dbc.cooperativa.repository.AssembleiaRepository;
import br.com.dbc.cooperativa.repository.AssociadoRepository;
import br.com.dbc.cooperativa.repository.PautaRepository;
import io.swagger.annotations.Api;

@Api(tags = "Assembleia")
@RestController
@RequestMapping("/assembleia")
public class AssembleiaController {

	@Autowired
	private AssembleiaRepository assembleiaRepository;
	@Autowired
	private PautaRepository pautaRepository;
	@Autowired
	private AssociadoRepository associadoRepository;
	@Autowired
	private AssembleiaAssociadoRepository assembleiaAssociadoRepository;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Cacheable(value = "listaDeAssembleias")
	public Page<AssembleiaDto> lista(@RequestParam(required = false) String assunto,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<Assembleia> assembleia;
		if (assunto == null)
			assembleia = assembleiaRepository.findAll(paginacao);
		else
			assembleia = assembleiaRepository.findByPautaAssunto(assunto, paginacao);
		return AssembleiaDto.converter(assembleia);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AssembleiaAssociadoVoto> detalhar(@PathVariable Long id) {
		Optional<AssembleiaAssociadoVoto> aav = assembleiaAssociadoRepository.findVotosByAssembleiaId(id);
		if (aav.isPresent()) {
			return ResponseEntity.ok(aav.get());
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	@CacheEvict(value = "listaDeAssembleias", allEntries = true)
	public ResponseEntity<AssembleiaDto> cadastrar(@RequestBody AssembleiaForm form, UriComponentsBuilder uriBuilder) {
		Optional<Pauta> pauta  = pautaRepository.findById(form.getPautaId());
		Optional<Assembleia> a = assembleiaRepository.findByPautaId(form.getPautaId());
		if (pauta.isPresent() && !a.isPresent()) {
			Assembleia assembleia = form.converter(assembleiaRepository, pauta.get());
			assembleiaRepository.save(assembleia);
			URI uri = uriBuilder.path("/assembleia/{id}").buildAndExpand(assembleia.getId()).toUri();
			return ResponseEntity.created(uri).body(new AssembleiaDto(assembleia));
		}
		return ResponseEntity.badRequest().build();
	}

	@Transactional
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AssembleiaAssociadoDto> votar(@RequestBody AssembleiaAssociadoForm form,
			UriComponentsBuilder uriBuilder) {

		Optional<Assembleia> assembleia = assembleiaRepository
				.findByIdAndInicioLessThanEqualAndFimGreaterThanEqual(form.getAssembleiaId()
																, LocalDateTime.now()
																, LocalDateTime.now());
		
		Optional<Associado> associado = associadoRepository.findById(form.getAssociadoId());
		AssembleiaAssociado assembleiaAssociado = new AssembleiaAssociado();
		
		if (associado.isPresent() && assembleia.isPresent()) {
			assembleiaAssociado = form.converter(assembleiaRepository, associadoRepository, assembleia.get(), associado.get(), form.getVoto());
			assembleiaAssociadoRepository.saveAndFlush(assembleiaAssociado);
			URI uri = uriBuilder.path("/assembleiaassociado/{id}").buildAndExpand(assembleiaAssociado.getId()).toUri();
			return ResponseEntity.created(uri).body(new AssembleiaAssociadoDto(assembleiaAssociado));
		}
		return ResponseEntity.notFound().build();
	}
}
