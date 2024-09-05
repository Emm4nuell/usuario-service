package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.LogAndThrow;
import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.port.in.IDeleteUsuarioUseCase;
import br.com.usuario_service.application.port.out.IDeleteUsuarioService;
import br.com.usuario_service.application.port.out.IFindByIdUsuarioService;
import br.com.usuario_service.application.port.out.IKafkaLog;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class DeleteUsuarioUseCase implements IDeleteUsuarioUseCase {

    private final IDeleteUsuarioService iDeleteUsuarioService;
    private final IFindByIdUsuarioService iFindByIdUsuarioService;
    private final IKafkaLog iKafkaLog;

    @Override
    public void execute(Long id) {
        if (id == null){
            throw new LogAndThrow(
                    iKafkaLog,
                    new IllegalArgumentException("Id Usuario nao pode ser nulo.")
            );
        }

        if (iFindByIdUsuarioService.execute(id).isEmpty()){
            throw new LogAndThrow(
                    iKafkaLog,
                    new NotFoundException("Usuario nao localizado na base de dados. ID: " + id)
            );
        }

        iDeleteUsuarioService.execute(id);
    }
}
