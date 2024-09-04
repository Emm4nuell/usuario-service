package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.port.in.IDeleteAlunoUseCase;
import br.com.usuario_service.application.port.out.IDeleteAlunoService;
import br.com.usuario_service.application.port.out.IFindByIdAlunoService;
import br.com.usuario_service.application.port.out.IKafkaLog;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@AllArgsConstructor
@Slf4j
public class DeleteAlunoUseCase implements IDeleteAlunoUseCase {

    private final IDeleteAlunoService iDeleteAlunoService;
    private final IFindByIdAlunoService iFindByIdAlunoService;
    private final IKafkaLog iKafkaLog;

    @Override
    public void execute(Long id) {
        if (id != null){
            if (iFindByIdAlunoService.execute(id).isPresent()){
                iDeleteAlunoService.execute(id);
            }else {
                iKafkaLog.execute("Aluno nao localizado na base de dados ID: " + id);
                throw new NotFoundException("Aluno nao localizado na base de dados ID: " + id);
            }
        }else {
            iKafkaLog.execute("Aluno ou id nao pode ser nulo.");
            throw new IllegalArgumentException("Aluno ou id nao pode ser nulo.");
        }
    }
}
