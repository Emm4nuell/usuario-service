package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.port.in.IDeleteUsuarioUseCase;
import br.com.usuario_service.application.port.out.IDeleteUsuarioService;
import br.com.usuario_service.application.port.out.IFindByIdUsuarioService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class DeleteUsuarioUseCase implements IDeleteUsuarioUseCase {

    private final IDeleteUsuarioService iDeleteUsuarioService;
    private final IFindByIdUsuarioService iFindByIdUsuarioService;

    @Override
    public void execute(Long id) {
        if (id != null) {
            if (iFindByIdUsuarioService.execute(id).isPresent()){
                iDeleteUsuarioService.execute(id);
            }else {
                throw new NotFoundException("Usuario nao localizado na base de dados. ID: " + id);
            }
        }else {
            throw new IllegalArgumentException("Id Usuario nao pode ser nulo.");
        }
    }
}
