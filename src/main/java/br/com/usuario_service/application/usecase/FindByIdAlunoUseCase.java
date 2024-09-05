package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.LogAndThrow;
import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.domain.model.AlunoModel;
import br.com.usuario_service.application.port.in.IFindByIdAlunoUseCase;
import br.com.usuario_service.application.port.out.IFindByIdAlunoService;
import br.com.usuario_service.application.port.out.IKafkaLog;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class FindByIdAlunoUseCase implements IFindByIdAlunoUseCase {

    private final IFindByIdAlunoService iFindByIdAlunoService;
    private final IKafkaLog iKafkaLog;

    @Override
    public AlunoModel execute(Long id) {
        return iFindByIdAlunoService.execute(id).orElseThrow(() ->
                new LogAndThrow(
                        iKafkaLog,
                        new NotFoundException("Aluno nao localizado na base de dados ID: " + id)
                ));
    }
}
