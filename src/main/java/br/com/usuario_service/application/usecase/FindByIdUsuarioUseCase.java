package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.LogAndThrow;
import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.domain.model.UsuarioModel;
import br.com.usuario_service.application.port.in.IFindByIdUsuarioUseCase;
import br.com.usuario_service.application.port.out.IFindByIdUsuarioService;
import br.com.usuario_service.application.port.out.IKafkaLog;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class FindByIdUsuarioUseCase implements IFindByIdUsuarioUseCase {

    private final IFindByIdUsuarioService iFindByIdUsuarioService;
    private final IKafkaLog iKafkaLog;

    @Override
    public UsuarioModel execute(Long id) {
        if (id == null){
            throw new LogAndThrow(
                    iKafkaLog,
                    new IllegalArgumentException("Id Usuario nao pode ser nulo.")
            );
        }
        return iFindByIdUsuarioService.execute(id).orElseThrow(() ->
                new LogAndThrow(
                        iKafkaLog,
                        new NotFoundException("Endereco nao localizado na base de dados ID: " + id)
                ));
    }
}
