package br.com.dbc.cooperativa.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.dbc.cooperativa.model.Assembleia;

public interface AssembleiaRepository extends JpaRepository<Assembleia, Long>{
	Page<Assembleia> findByPautaAssunto(String assunto, Pageable paginacao);
	Optional<Assembleia> findByPautaId(Long pautaId);
	
	@Query(value = "SELECT * FROM ASSEMBLEIA  WHERE ID = :pId AND :pData BETWEEN INICIO AND FIM", nativeQuery = true)
	Optional<Assembleia> findByDate(@Param("pId") Long id, @Param("pData") String data);
}
