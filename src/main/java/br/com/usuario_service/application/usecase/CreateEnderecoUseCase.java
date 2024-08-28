package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.UserNotFoundException;
import br.com.usuario_service.application.domain.model.EnderecoModel;
import br.com.usuario_service.application.domain.model.UsuarioModel;
import br.com.usuario_service.application.port.in.ICreateEnderecoUseCase;
import br.com.usuario_service.application.port.out.ICreateEnderecoService;
import br.com.usuario_service.application.port.out.IFindByIdUsuarioService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class CreateEnderecoUseCase implements ICreateEnderecoUseCase {

    private final ICreateEnderecoService iCreateEnderecoService;
    private final IFindByIdUsuarioService iFindByIdUsuarioService;

    @Override
    public EnderecoModel execute(Long id, EnderecoModel model) {
        var usuario = iFindByIdUsuarioService.execute(id).orElseThrow(() ->
                new UserNotFoundException("Usuario nao localizado na base de dados com ID: " + id));
        model.setUsuario(usuario);
        return iCreateEnderecoService.execute(id, model);
    }
}
