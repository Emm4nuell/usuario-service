package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.LogAndThrow;
import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.domain.model.ParentescoModel;
import br.com.usuario_service.application.port.in.IUpdateParentescoUseCase;
import br.com.usuario_service.application.port.out.IFindByIdParentescoService;
import br.com.usuario_service.application.port.out.IKafkaLog;
import br.com.usuario_service.application.port.out.IUpdateParentescoService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class UpdateParentescoUseCase implements IUpdateParentescoUseCase {

    private final IUpdateParentescoService iUpdateParentescoService;
    private final IFindByIdParentescoService iFindByIdParentescoService;
    private final IKafkaLog iKafkaLog;

    @Override
    public ParentescoModel execute(Long id, ParentescoModel model) {
        if (id == null || model == null){
            throw new LogAndThrow(
                    iKafkaLog,
                    new IllegalArgumentException("Parentesco ou id nao pode ser nulo.")
            );
        }

        var endereco = iFindByIdParentescoService.execute(id).orElseThrow(()->
                new LogAndThrow(
                        iKafkaLog,
                        new NotFoundException("Endereco nao localizado na base de dados. ID: " + id)
                ));

        model.setId(endereco.getId());
        return iUpdateParentescoService.execute(model);
    }
}
