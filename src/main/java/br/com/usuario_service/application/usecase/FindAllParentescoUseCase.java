package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.LogAndThrow;
import br.com.usuario_service.application.domain.exception.ResourceNotFoundException;
import br.com.usuario_service.application.domain.model.ParentescoModel;
import br.com.usuario_service.application.port.in.IFindAllParentescoUseCase;
import br.com.usuario_service.application.port.out.IFindAllParentescoService;
import br.com.usuario_service.application.port.out.IKafkaLog;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@AllArgsConstructor
public class FindAllParentescoUseCase implements IFindAllParentescoUseCase {

    private final IFindAllParentescoService iFindAllParentescoService;
    private final IKafkaLog iKafkaLog;

    @Override
    public Page<ParentescoModel> execute(Pageable pageable) {
        var endereco = iFindAllParentescoService.execute(pageable);
        if (endereco.isEmpty()){
            throw new LogAndThrow(
                    iKafkaLog,
                    new ResourceNotFoundException("Ausencia de dados para mostrar.")
            );
        }
        return endereco;
    }
}
