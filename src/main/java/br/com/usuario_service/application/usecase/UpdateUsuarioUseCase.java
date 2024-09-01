package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.domain.model.UsuarioModel;
import br.com.usuario_service.application.port.in.IUpdateUsuarioUseCase;
import br.com.usuario_service.application.port.out.ICreateUsuarioService;
import br.com.usuario_service.application.port.out.IFindByIdUsuarioService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@AllArgsConstructor
@Slf4j
public class UpdateUsuarioUseCase implements IUpdateUsuarioUseCase {

    private final IFindByIdUsuarioService iFindByIdUsuarioService;
    private final ICreateUsuarioService iCreateUsuarioService;

    @Override
    public UsuarioModel execute(Long id, UsuarioModel model) {
        if (id != null && model != null){
            var domain = iFindByIdUsuarioService.execute(id).orElseThrow(()-> {
                log.error("Usuario nao localizado na base de dados ID: {}", id);
                return new NotFoundException("Usuario nao localizado na base de dados ID: " + id);
            });
            model.setId(domain.getId());
            model.setData_created(domain.getData_created());
            model.setStatus(domain.isStatus());
            return iCreateUsuarioService.execute(model);
        }else {
            log.error("Usuario ou id nao pode ser nulo.");
            throw new IllegalArgumentException("Usuario ou id nao pode ser nulo.");
        }
    }
}
