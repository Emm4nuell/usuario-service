package br.com.usuario_service.application.port.in;

import br.com.usuario_service.application.domain.model.UsuarioModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IFindAllUsuariosUseCase {
    Page<UsuarioModel> execute(Pageable pageable);
}
