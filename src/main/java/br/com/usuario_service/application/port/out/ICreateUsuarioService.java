package br.com.usuario_service.application.port.out;

import br.com.usuario_service.application.domain.model.UsuarioModel;

public interface ICreateUsuarioService {
    UsuarioModel execute(UsuarioModel model);
}
