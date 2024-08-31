package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.CpfAlreadyExistsException;
import br.com.usuario_service.application.domain.model.UsuarioModel;
import br.com.usuario_service.application.port.in.ICreateUsuarioUseCase;
import br.com.usuario_service.application.port.out.ICreateUsuarioService;
import br.com.usuario_service.application.port.out.IFindByCpfService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@UseCase
@AllArgsConstructor
public class CreateUsuarioUseCase implements ICreateUsuarioUseCase {

    private final ICreateUsuarioService iCreateUsuarioService;
    private final IFindByCpfService iFindByCpfService;

    @Override
    public UsuarioModel execute(UsuarioModel model) {
        if (model != null){

            if(iFindByCpfService.execute(model.getCpf()).isEmpty()){
                model.setData_created(LocalDateTime.now());
                model.setStatus(false);
                return iCreateUsuarioService.execute(model);
            }else{
                throw new CpfAlreadyExistsException("Usuario ja cadastrado na base de dados CPF: " + model.getCpf());
            }
        }else{
            throw new IllegalArgumentException("Os dados do usuario nao pode ser nulo.");
        }
    }
}
