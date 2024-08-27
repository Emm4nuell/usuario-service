package br.com.usuario_service.adapters.output.database.repository;

import br.com.usuario_service.adapters.output.database.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByCpf(String cpf);
}
