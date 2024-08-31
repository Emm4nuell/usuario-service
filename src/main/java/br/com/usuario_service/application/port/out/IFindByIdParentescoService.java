package br.com.usuario_service.application.port.out;

import br.com.usuario_service.application.domain.model.ParentescoModel;

import java.util.Optional;

public interface IFindByIdParentescoService {
    Optional<ParentescoModel> execute(Long id);
}
