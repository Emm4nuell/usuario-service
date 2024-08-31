package br.com.usuario_service.application.port.out;

import br.com.usuario_service.application.domain.model.ParentescoModel;

import java.util.List;

public interface IFindParentescoByUsuarioService {
    List<ParentescoModel> execute(Long id);
}
