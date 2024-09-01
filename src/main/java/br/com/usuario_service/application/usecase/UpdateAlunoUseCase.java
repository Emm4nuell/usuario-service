package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.domain.model.AlunoModel;
import br.com.usuario_service.application.port.in.IUpdateAlunoUseCase;
import br.com.usuario_service.application.port.out.IFindByIdAlunoService;
import br.com.usuario_service.application.port.out.IUpdateAlunoService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@AllArgsConstructor
@Slf4j
public class UpdateAlunoUseCase implements IUpdateAlunoUseCase {

    private final IUpdateAlunoService iUpdateAlunoService;
    private final IFindByIdAlunoService iFindByIdAlunoService;

    @Override
    public AlunoModel execute(Long id, AlunoModel model) {
        if (id != null && model != null){
            var aluno = iFindByIdAlunoService.execute(id).orElseThrow(() -> {
                log.error("Aluno nao localizado na base de dados. ID: {}", id);
                return new NotFoundException("Aluno nao localizado na base de dados. ID: " + id);
            });
            model.setId(aluno.getId());
            return iUpdateAlunoService.execute(aluno);
        }else{
            log.error("Aluno ou id nao pode ser nulo.");
            throw new IllegalArgumentException("Aluno ou id nao pode ser nulo.");
        }
    }
}
