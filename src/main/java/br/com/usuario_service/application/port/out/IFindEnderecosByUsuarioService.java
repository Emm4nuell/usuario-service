package br.com.usuario_service.application.port.out;

import br.com.usuario_service.application.domain.model.EnderecoModel;

import java.util.List;

public interface IFindEnderecosByUsuarioService {
    List<EnderecoModel> execute(Long id);
}
