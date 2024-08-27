package br.com.usuario_service.application.port.out;

import br.com.usuario_service.application.domain.model.UsuarioModel;

import java.util.Optional;

public interface IFindByCpfService {
    Optional<UsuarioModel> execute(String cpf);
}
