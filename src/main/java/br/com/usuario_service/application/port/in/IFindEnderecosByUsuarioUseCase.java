package br.com.usuario_service.application.port.in;

import br.com.usuario_service.application.domain.model.EnderecoModel;

import java.util.List;

public interface IFindEnderecosByUsuarioUseCase {
    List<EnderecoModel> execute(Long id);
}
