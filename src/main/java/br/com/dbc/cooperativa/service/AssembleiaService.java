package br.com.dbc.cooperativa.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.dbc.cooperativa.exception.ResourceNotFoundException;
import br.com.dbc.cooperativa.model.Assembleia;
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

@Service
public class AssembleiaService {

	@Autowired
	private AssembleiaRepository assembleiaRepository;
	@Autowired
	private PautaRepository pautaRepository;
	@Autowired
	private AssociadoRepository associadoRepository;
	@Autowired
	private AssembleiaAssociadoRepository assembleiaAssociadoRepository;

	public Page<AssembleiaDto> obterTodos(String nome, Pageable paginacao) {
		Page<Assembleia> associados;
		if (nome == null)
			associados = assembleiaRepository.findAll(paginacao);
		else
			associados = assembleiaRepository.findByPautaAssunto(nome, paginacao);
		return AssembleiaDto.converter(associados);
	}

	public AssembleiaAssociadoVoto obterPorId(Long id) {
		Optional<AssembleiaAssociadoVoto> one = assembleiaAssociadoRepository.findVotosByAssembleiaId(id);
		AssembleiaAssociadoVoto entity = one.orElseThrow(ResourceNotFoundException::new);
		return entity;
	}

	public AssembleiaDto incluir(AssembleiaForm form) {
		Optional<Pauta> pOne = pautaRepository.findById(form.getPautaId());
		Pauta pEntity = pOne.orElseThrow(ResourceNotFoundException::new);

		Optional<Assembleia> aOne = assembleiaRepository.findByPautaId(form.getPautaId());
		Assembleia aEntity = aOne.orElse(assembleiaRepository.save(form.converter(assembleiaRepository, pEntity)));

		return new AssembleiaDto(aEntity);
	}

	public AssembleiaAssociadoDto votar(AssembleiaAssociadoForm form) {
		Optional<Assembleia> aOne = assembleiaRepository.findByIdAndInicioLessThanEqualAndFimGreaterThanEqual(
				form.getAssembleiaId(), LocalDateTime.now(), LocalDateTime.now());
		Assembleia aEntity = aOne.orElseThrow(ResourceNotFoundException::new);

		Optional<Associado> asOne = associadoRepository.findById(form.getAssociadoId());
		Associado asEntity = asOne.orElseThrow(ResourceNotFoundException::new);

		return new AssembleiaAssociadoDto(assembleiaAssociadoRepository.saveAndFlush(
				form.converter(assembleiaRepository, associadoRepository, aEntity, asEntity, form.getVoto())));
	}
}
