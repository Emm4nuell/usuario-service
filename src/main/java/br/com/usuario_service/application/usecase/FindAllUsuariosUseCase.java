package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.ResourceNotFoundException;
import br.com.usuario_service.application.domain.model.UsuarioModel;
import br.com.usuario_service.application.port.in.IFindAllUsuariosUseCase;
import br.com.usuario_service.application.port.out.IFindAllUsuariosService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@AllArgsConstructor
@Slf4j
public class FindAllUsuariosUseCase implements IFindAllUsuariosUseCase {

    private final IFindAllUsuariosService iFindAllUsuariosService;

    @Override
    public Page<UsuarioModel> execute(Pageable pageable) {
        var usuarios = iFindAllUsuariosService.execute(pageable);
        if (usuarios.isEmpty()){
            log.error("Ausencia de dados para mostrar.");
            throw new ResourceNotFoundException("Ausencia de dados para mostrar.");
        }
        return usuarios;
    }
}
