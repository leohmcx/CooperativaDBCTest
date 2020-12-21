package br.com.dbc.cooperativa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dbc.cooperativa.model.Assembleia;

public interface AssembleiaRepository extends JpaRepository<Assembleia, Long>{
	Page<Assembleia> findByPautaAssunto(String assunto, Pageable paginacao);
}
