package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.domain.model.ParentescoModel;
import br.com.usuario_service.application.port.in.IFindByIdParentescoUseCase;
import br.com.usuario_service.application.port.out.IFindByIdParentescoService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@AllArgsConstructor
@Slf4j
public class FindByIdParentescoUseCase implements IFindByIdParentescoUseCase {

    private final IFindByIdParentescoService iFindByIdParentescoService;

    @Override
    public ParentescoModel execute(Long id) {
        if (id != null){
            return iFindByIdParentescoService.execute(id).orElseThrow(() -> {
                log.error("Usuario nao localizado na base de dados ID: {}", id);
                return new NotFoundException("Usuario nao localizado na base de dados ID: " + id);
            });
        }else {
            log.error("Id nao pode ser nulo.");
            throw new IllegalArgumentException("Id nao pode ser nulo.");
        }
    }
}
