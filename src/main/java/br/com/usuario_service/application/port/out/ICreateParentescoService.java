package br.com.usuario_service.application.port.out;

import br.com.usuario_service.application.domain.model.ParentescoModel;

public interface ICreateParentescoService {
    ParentescoModel execute(ParentescoModel model);
}
