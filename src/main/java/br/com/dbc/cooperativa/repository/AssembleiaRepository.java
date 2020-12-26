package br.com.dbc.cooperativa.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dbc.cooperativa.model.Assembleia;

public interface AssembleiaRepository extends JpaRepository<Assembleia, Long>{
	Page<Assembleia> findByPautaAssunto(String assunto, Pageable paginacao);
	Optional<Assembleia> findByPautaId(Long pautaId);
	Optional<Assembleia> findByIdAndInicioLessThanEqualAndFimGreaterThanEqual(Long id, LocalDateTime inicio, LocalDateTime fim);
}
