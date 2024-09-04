package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.CpfAlreadyExistsException;
import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.domain.model.AlunoModel;
import br.com.usuario_service.application.port.in.ICreateAlunoUseCase;
import br.com.usuario_service.application.port.out.ICreateAlunoService;
import br.com.usuario_service.application.port.out.IFindByCpfAlunoService;
import br.com.usuario_service.application.port.out.IFindByIdUsuarioService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@UseCase
@AllArgsConstructor
@Slf4j
public class CreateAlunoUseCase implements ICreateAlunoUseCase {

    private final ICreateAlunoService iCreateAlunoService;
    private final IFindByCpfAlunoService iFindByCpfAlunoService;
    private final IFindByIdUsuarioService iFindByIdUsuarioService;

    @Override
    public AlunoModel execute(Long id, AlunoModel model) {
        if (model != null){
            var usuario = iFindByIdUsuarioService.execute(id).orElseThrow(() -> {
                log.error("Usuario nao localizado na base de dados para cadastrar ALUNO com ID: {}", id);
                return new NotFoundException("Usuario nao localizado na base de dados para cadastrar ALUNO com ID: " + id);
            });
            if (iFindByCpfAlunoService.execute(model.getCpf()).isEmpty()){
                model.setUsuario(usuario);
                model.setData_cadastro(LocalDateTime.now());
                return iCreateAlunoService.execute(model);
            }else{
                log.error("Aluno ja cadastrado no sistema. CPF: {}", model.getCpf());
                throw new CpfAlreadyExistsException("Aluno ja cadastrado no sistema. CPF: " + model.getCpf());
            }
        }else{
            log.error("O modelo de aluno não pode ser nulo.");
            throw new IllegalArgumentException("O modelo de aluno não pode ser nulo.");
        }
    }
}
