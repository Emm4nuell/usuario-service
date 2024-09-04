package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.domain.model.AlunoModel;
import br.com.usuario_service.application.port.in.IUpdateAlunoUseCase;
import br.com.usuario_service.application.port.out.IFindByIdAlunoService;
import br.com.usuario_service.application.port.out.IKafkaLog;
import br.com.usuario_service.application.port.out.IUpdateAlunoService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class UpdateAlunoUseCase implements IUpdateAlunoUseCase {

    private final IUpdateAlunoService iUpdateAlunoService;
    private final IFindByIdAlunoService iFindByIdAlunoService;
    private final IKafkaLog iKafkaLog;

    @Override
    public AlunoModel execute(Long id, AlunoModel model) {
        if (id != null && model != null){
            var aluno = iFindByIdAlunoService.execute(id).orElseThrow(() -> {
                iKafkaLog.execute("Aluno nao localizado na base de dados. ID: " + id);
                return new NotFoundException("Aluno nao localizado na base de dados. ID: " + id);
            });
            model.setId(aluno.getId());
            return iUpdateAlunoService.execute(aluno);
        }else{
            iKafkaLog.execute("Aluno ou id nao pode ser nulo.");
            throw new IllegalArgumentException("Aluno ou id nao pode ser nulo.");
        }
    }
}
