package br.com.usuario_service.application.port.out;

import br.com.usuario_service.application.domain.model.EnderecoModel;

import java.util.Optional;

public interface IFindByIdEnderecoService {
    Optional<EnderecoModel> execute(Long id);
}
