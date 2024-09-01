package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.port.in.IDeleteParentescoUseCase;
import br.com.usuario_service.application.port.out.IDeleteParentescoService;
import br.com.usuario_service.application.port.out.IFindByIdParentescoService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class DeleteParentescoUseCase implements IDeleteParentescoUseCase {

    private final IDeleteParentescoService iDeleteParentescoService;
    private final IFindByIdParentescoService iFindByIdParentescoService;

    @Override
    public void execute(Long id) {
        if (id != null){
            if (iFindByIdParentescoService.execute(id).isPresent()){
                iDeleteParentescoService.execute(id);
            }else{
                throw new NotFoundException("Endereco nao localizado na base de dados ID: " + id);
            }
        }else {
            throw new IllegalArgumentException("Id Parentesco nao pode ser nulo.");
        }
    }
}
