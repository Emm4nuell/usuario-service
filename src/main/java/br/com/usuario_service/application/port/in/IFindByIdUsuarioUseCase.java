package br.com.usuario_service.application.port.in;

import br.com.usuario_service.application.domain.model.UsuarioModel;

public interface IFindByIdUsuarioUseCase {
    UsuarioModel execute(Long id);
}
