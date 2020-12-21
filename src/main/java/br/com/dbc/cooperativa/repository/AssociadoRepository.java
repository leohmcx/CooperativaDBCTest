package br.com.dbc.cooperativa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dbc.cooperativa.model.Associado;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {
	Page<Associado> findByNome(String nome, Pageable paginacao);
}
