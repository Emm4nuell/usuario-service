package br.com.usuario_service.application.port.in;

import br.com.usuario_service.application.domain.model.ParentescoModel;

import java.util.List;

public interface IFindParentescoByUsuarioUseCase {
    List<ParentescoModel> execute(Long id);
}
