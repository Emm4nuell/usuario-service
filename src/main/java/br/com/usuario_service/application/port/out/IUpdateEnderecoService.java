package br.com.usuario_service.application.port.out;

import br.com.usuario_service.application.domain.model.EnderecoModel;

public interface IUpdateEnderecoService {
    EnderecoModel execute(EnderecoModel model);
}
