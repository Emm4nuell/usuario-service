package br.com.usuario_service.application.port.out;

import br.com.usuario_service.application.domain.model.AlunoModel;

public interface ICreateAlunoService {
    AlunoModel execute(AlunoModel model);
}
