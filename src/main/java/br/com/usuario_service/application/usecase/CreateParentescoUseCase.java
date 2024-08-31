package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.CpfAlreadyExistsException;
import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.domain.model.ParentescoModel;
import br.com.usuario_service.application.port.in.ICreateParentescoUseCase;
import br.com.usuario_service.application.port.out.ICreateParentescoService;
import br.com.usuario_service.application.port.out.IFindByIdUsuarioService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class CreateParentescoUseCase implements ICreateParentescoUseCase {

    private final ICreateParentescoService iCreateParentescoService;
    private final IFindByIdUsuarioService iFindByIdUsuarioService;

    @Override
    public ParentescoModel execute(Long id, ParentescoModel model) {
        if(id != null && model != null){
            var usuario = iFindByIdUsuarioService.execute(id).orElseThrow(() ->
                    new NotFoundException("Usuario nao localizado na base de dados com ID: " + id));
            if (model.getCpf().equals(usuario.getCpf())){
                throw new CpfAlreadyExistsException("Parente ja cadastrado no sistema. CPF: " + usuario.getCpf());
            }else {
                model.setUsuario(usuario);
                return iCreateParentescoService.execute(model);
            }
        }else{
            throw new IllegalArgumentException("O modelo de parentesco ou id não pode ser nulo.");
        }
    }
}
