package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.domain.model.ParentescoModel;
import br.com.usuario_service.application.port.in.IUpdateParentescoUseCase;
import br.com.usuario_service.application.port.out.IFindByIdParentescoService;
import br.com.usuario_service.application.port.out.IUpdateParentescoService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@AllArgsConstructor
@Slf4j
public class UpdateParentescoUseCase implements IUpdateParentescoUseCase {

    private final IUpdateParentescoService iUpdateParentescoService;
    private final IFindByIdParentescoService iFindByIdParentescoService;

    @Override
    public ParentescoModel execute(Long id, ParentescoModel model) {
        if (id != null && model != null){
            var endereco = iFindByIdParentescoService.execute(id).orElseThrow(()-> {
                log.error("Endereco nao localizado na base de dados. ID: {}", id);
                return new NotFoundException("Endereco nao localizado na base de dados. ID: " + id);
            });
            model.setId(endereco.getId());
            return iUpdateParentescoService.execute(model);
        }else {
            log.error("Parentesco ou id nao pode ser nulo.");
            throw new IllegalArgumentException("Parentesco ou id nao pode ser nulo.");
        }
    }
}
