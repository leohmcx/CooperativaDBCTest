package br.com.dbc.cooperativa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.dbc.cooperativa.controller.dto.AssembleiaAssociadoVoto;
import br.com.dbc.cooperativa.model.AssembleiaAssociado;
import br.com.dbc.cooperativa.model.AssembleiaAssociadoKey;

public interface AssembleiaAssociadoRepository extends JpaRepository<AssembleiaAssociado, AssembleiaAssociadoKey> {

	@Query(value = "SELECT P.ASSUNTO, A.INICIO, A.FIM "
			+ ", (SELECT COUNT(1) FROM ASSEMBLEIA_ASSOCIADO AA1 WHERE AA1.ASSEMBLEIA_ID = AA.ASSEMBLEIA_ID AND aa1.VOTO = 1) as SIM "
			+ ", (SELECT COUNT(1) FROM ASSEMBLEIA_ASSOCIADO AA2 WHERE AA2.ASSEMBLEIA_ID = AA.ASSEMBLEIA_ID AND aa2.VOTO = 0) as NAO "
			+ " FROM ASSEMBLEIA_ASSOCIADO AA, PAUTA P, ASSEMBLEIA A "
			+ " WHERE P.ID = A.PAUTA_ID "
			+ "   AND A.ID = AA.ASSEMBLEIA_ID "
			+ "   AND AA.ASSEMBLEIA_ID = :pId "
			+ " GROUP BY P.ASSUNTO, A.INICIO, A.FIM "
			+ ", (SELECT COUNT(1) FROM ASSEMBLEIA_ASSOCIADO AA1 WHERE AA1.ASSEMBLEIA_ID = AA.ASSEMBLEIA_ID AND aa1.VOTO = 1) "
			+ ", (SELECT COUNT(1) FROM ASSEMBLEIA_ASSOCIADO AA2 WHERE AA2.ASSEMBLEIA_ID = AA.ASSEMBLEIA_ID AND aa2.VOTO = 0) ", nativeQuery = true)
	AssembleiaAssociadoVoto findVotosByAssembleiaId(@Param("pId") Long id);
}
