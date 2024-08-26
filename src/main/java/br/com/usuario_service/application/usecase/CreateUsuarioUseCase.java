package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.model.UsuarioModel;
import br.com.usuario_service.application.port.in.ICreateUsuarioUseCase;
import br.com.usuario_service.application.port.out.ICreateUsuarioService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@UseCase
@AllArgsConstructor
public class CreateUsuarioUseCase implements ICreateUsuarioUseCase {

    private final ICreateUsuarioService iCreateUsuarioService;

    @Override
    public UsuarioModel execute(UsuarioModel model) {
        model.setData_created(LocalDateTime.now());
        model.setStatus(false);
        return iCreateUsuarioService.execute(model);
    }
}
