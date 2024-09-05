package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.LogAndThrow;
import br.com.usuario_service.application.domain.exception.ResourceNotFoundException;
import br.com.usuario_service.application.domain.model.UsuarioModel;
import br.com.usuario_service.application.port.in.IFindAllUsuariosUseCase;
import br.com.usuario_service.application.port.out.IFindAllUsuariosService;
import br.com.usuario_service.application.port.out.IKafkaLog;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@AllArgsConstructor
public class FindAllUsuariosUseCase implements IFindAllUsuariosUseCase {

    private final IFindAllUsuariosService iFindAllUsuariosService;
    private final IKafkaLog iKafkaLog;

    @Override
    public Page<UsuarioModel> execute(Pageable pageable) {
        var usuarios = iFindAllUsuariosService.execute(pageable);
        if (usuarios.isEmpty()){
            throw new LogAndThrow(
                    iKafkaLog,
                    new ResourceNotFoundException("Não é possível processar: a lista está vazia.")
            );
        }
        return usuarios;
    }
}
