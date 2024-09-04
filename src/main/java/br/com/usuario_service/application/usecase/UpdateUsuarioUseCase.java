package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.domain.model.UsuarioModel;
import br.com.usuario_service.application.port.in.IUpdateUsuarioUseCase;
import br.com.usuario_service.application.port.out.ICreateUsuarioService;
import br.com.usuario_service.application.port.out.IFindByIdUsuarioService;
import br.com.usuario_service.application.port.out.IKafkaLog;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class UpdateUsuarioUseCase implements IUpdateUsuarioUseCase {

    private final IFindByIdUsuarioService iFindByIdUsuarioService;
    private final ICreateUsuarioService iCreateUsuarioService;
    private final IKafkaLog iKafkaLog;

    @Override
    public UsuarioModel execute(Long id, UsuarioModel model) {
        if (id != null && model != null){
            var domain = iFindByIdUsuarioService.execute(id).orElseThrow(()-> {
                iKafkaLog.execute("Usuario nao localizado na base de dados ID: " + id);
                return new NotFoundException("Usuario nao localizado na base de dados ID: " + id);
            });
            model.setId(domain.getId());
            model.setData_created(domain.getData_created());
            model.setStatus(domain.isStatus());
            return iCreateUsuarioService.execute(model);
        }else {
            iKafkaLog.execute("Usuario ou id nao pode ser nulo.");
            throw new IllegalArgumentException("Usuario ou id nao pode ser nulo.");
        }
    }
}
