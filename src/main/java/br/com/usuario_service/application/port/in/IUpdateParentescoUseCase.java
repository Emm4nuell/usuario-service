package br.com.usuario_service.application.port.in;

import br.com.usuario_service.application.domain.model.ParentescoModel;

public interface IUpdateParentescoUseCase {
    ParentescoModel execute(Long id, ParentescoModel model);
}
