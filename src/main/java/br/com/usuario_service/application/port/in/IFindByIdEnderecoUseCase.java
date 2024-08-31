package br.com.usuario_service.application.port.in;

import br.com.usuario_service.application.domain.model.EnderecoModel;

public interface IFindByIdEnderecoUseCase {
    EnderecoModel execute(Long id);
}
