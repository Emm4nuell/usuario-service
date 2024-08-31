package br.com.usuario_service.application.port.in;

import br.com.usuario_service.application.domain.model.EnderecoModel;

public interface IUpdateEnderecoUseCase {
    EnderecoModel execute(Long id, EnderecoModel model);
}
