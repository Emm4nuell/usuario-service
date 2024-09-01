package br.com.usuario_service.application.port.out;

import br.com.usuario_service.application.domain.model.AlunoModel;

import java.util.Optional;

public interface IFindByCpfAlunoService {
    Optional<AlunoModel> execute(String cpf);
}
