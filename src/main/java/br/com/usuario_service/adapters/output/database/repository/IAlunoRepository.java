package br.com.usuario_service.adapters.output.database.repository;

import br.com.usuario_service.adapters.output.database.entity.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAlunoRepository extends JpaRepository<AlunoEntity, Long> {
    Optional<AlunoEntity> findByCpf(String cpf);
}
