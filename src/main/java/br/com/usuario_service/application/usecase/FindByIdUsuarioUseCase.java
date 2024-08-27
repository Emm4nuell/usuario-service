package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.UserNotFoundException;
import br.com.usuario_service.application.domain.model.UsuarioModel;
import br.com.usuario_service.application.port.in.IFindByIdUsuarioUseCase;
import br.com.usuario_service.application.port.out.IFindByIdUsuarioService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@AllArgsConstructor
@Slf4j
public class FindByIdUsuarioUseCase implements IFindByIdUsuarioUseCase {

    private final IFindByIdUsuarioService iFindByIdUsuarioService;

    @Override
    public UsuarioModel execute(Long id) {
        return iFindByIdUsuarioService.execute(id).orElseThrow(() ->
                new UserNotFoundException("Usuario nao localizado na base de dados ID: " + id));
    }
}
