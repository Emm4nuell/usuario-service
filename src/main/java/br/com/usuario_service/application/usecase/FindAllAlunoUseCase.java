package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.LogAndThrow;
import br.com.usuario_service.application.domain.exception.ResourceNotFoundException;
import br.com.usuario_service.application.domain.model.AlunoModel;
import br.com.usuario_service.application.port.in.IFindAllAlunoUseCase;
import br.com.usuario_service.application.port.out.IFindAllAlunoService;
import br.com.usuario_service.application.port.out.IKafkaLog;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@AllArgsConstructor
public class FindAllAlunoUseCase implements IFindAllAlunoUseCase {

    private final IFindAllAlunoService iFindAllAlunoService;
    private final IKafkaLog iKafkaLog;

    @Override
    public Page<AlunoModel> execute(Pageable pageable) {
        var model = iFindAllAlunoService.execute(pageable);
        if (model.isEmpty()){
            throw new LogAndThrow(
                    iKafkaLog,
                    new ResourceNotFoundException("Não é possível processar: a lista está vazia.")
            );
        }
            return model;
    }
}
