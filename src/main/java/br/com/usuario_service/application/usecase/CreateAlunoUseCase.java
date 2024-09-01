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

import java.time.LocalDateTime;

@UseCase
@AllArgsConstructor
public class CreateAlunoUseCase implements ICreateAlunoUseCase {

    private final ICreateAlunoService iCreateAlunoService;
    private final IFindByCpfAlunoService iFindByCpfAlunoService;
    private final IFindByIdUsuarioService iFindByIdUsuarioService;

    @Override
    public AlunoModel execute(Long id, AlunoModel model) {
        if (model != null){
            var usuario = iFindByIdUsuarioService.execute(id).orElseThrow(() ->
                    new NotFoundException("Usuario nao localizado na base de dados com ID: " + id));
            if (iFindByCpfAlunoService.execute(model.getCpf()).isEmpty()){
                model.setUsuario(usuario);
                model.setData_cadastro(LocalDateTime.now());
                return iCreateAlunoService.execute(model);
            }else{
                throw new CpfAlreadyExistsException("Aluno ja cadastrado no sistema. CPF: " + model.getCpf());
            }
        }else{
            throw new IllegalArgumentException("O modelo de aluno não pode ser nulo.");
        }
    }
}
