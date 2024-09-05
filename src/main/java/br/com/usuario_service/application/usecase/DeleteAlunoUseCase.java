package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.LogAndThrow;
import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.port.in.IDeleteAlunoUseCase;
import br.com.usuario_service.application.port.out.IDeleteAlunoService;
import br.com.usuario_service.application.port.out.IFindByIdAlunoService;
import br.com.usuario_service.application.port.out.IKafkaLog;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class DeleteAlunoUseCase implements IDeleteAlunoUseCase {

    private final IDeleteAlunoService iDeleteAlunoService;
    private final IFindByIdAlunoService iFindByIdAlunoService;
    private final IKafkaLog iKafkaLog;

    @Override
    public void execute(Long id) {
        if (id == null){
            throw new LogAndThrow(
                    iKafkaLog,
                    new IllegalArgumentException("Aluno ou id nao pode ser nulo."));
        }

        if (iFindByIdAlunoService.execute(id).isEmpty()){
            throw new LogAndThrow(
                    iKafkaLog,
                    new NotFoundException("Aluno nao localizado na base de dados ID: " + id)
            );
        }

        iDeleteAlunoService.execute(id);
    }
}
