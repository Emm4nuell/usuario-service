package br.com.usuario_service.application.port.out;

import br.com.usuario_service.application.domain.model.AlunoModel;

import java.util.Optional;

public interface IFindByIdAlunoService {
    Optional<AlunoModel> execute(Long id);
}
