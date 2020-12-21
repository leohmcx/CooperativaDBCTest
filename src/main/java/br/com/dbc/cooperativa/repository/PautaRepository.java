package br.com.dbc.cooperativa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dbc.cooperativa.model.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long>{
	Page<Pauta> findByAssunto(String assunto, Pageable paginacao);
}
