package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.domain.model.AlunoModel;
import br.com.usuario_service.application.port.in.IFindByIdAlunoUseCase;
import br.com.usuario_service.application.port.out.IFindByIdAlunoService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@AllArgsConstructor
@Slf4j
public class FindByIdAlunoUseCase implements IFindByIdAlunoUseCase {

    private final IFindByIdAlunoService iFindByIdAlunoService;

    @Override
    public AlunoModel execute(Long id) {
        return iFindByIdAlunoService.execute(id).orElseThrow(() -> {
            log.error("Aluno nao localizado na base de dados ID: {}", id);
            return new NotFoundException("Aluno nao localizado na base de dados ID: " + id);
        });
    }
}
