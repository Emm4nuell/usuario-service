package br.com.usuario_service.application.port.in;

import br.com.usuario_service.application.domain.model.AlunoModel;

public interface IFindByIdAlunoUseCase {
    AlunoModel execute(Long id);
}
