package br.com.usuario_service.adapters.output.database.repository;

import br.com.usuario_service.adapters.output.database.entity.ParentescoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IParentescoRepository extends JpaRepository<ParentescoEntity, Long> {
    @Query(value = "select * from tb_parentesco where usuario_id = :id", nativeQuery = true)
    List<ParentescoEntity> findParentescoByUsuarioIdNative(@Param("id") Long id);
}
