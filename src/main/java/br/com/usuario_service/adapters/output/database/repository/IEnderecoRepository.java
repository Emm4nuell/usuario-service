package br.com.usuario_service.adapters.output.database.repository;

import br.com.usuario_service.adapters.output.database.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
}
