package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.LogAndThrow;
import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.domain.model.ParentescoModel;
import br.com.usuario_service.application.port.in.IFindParentescoByUsuarioUseCase;
import br.com.usuario_service.application.port.out.IFindParentescoByUsuarioService;
import br.com.usuario_service.application.port.out.IKafkaLog;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;

import java.util.List;

@UseCase
@AllArgsConstructor
public class FindParentescoByUsuarioUseCase implements IFindParentescoByUsuarioUseCase {

    private final IFindParentescoByUsuarioService iFindParentescoByUsuarioService;
    private final IKafkaLog iKafkaLog;

    @Override
    public List<ParentescoModel> execute(Long id) {
        if (id == null){
            throw new LogAndThrow(
                    iKafkaLog,
                    new IllegalArgumentException("Id nao pode ser nulo.")
            );
        }
        var endereco = iFindParentescoByUsuarioService.execute(id);
        if (endereco.isEmpty()) {
            throw new LogAndThrow(
                    iKafkaLog,
                    new NotFoundException("Endereco nao localizado na base de dados ID: " + id)
            );
        }
        return endereco;
    }
}
