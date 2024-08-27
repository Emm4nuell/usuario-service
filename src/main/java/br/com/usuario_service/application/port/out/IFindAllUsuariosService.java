package br.com.usuario_service.application.port.out;

import br.com.usuario_service.application.domain.model.UsuarioModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IFindAllUsuariosService {
    Page<UsuarioModel> execute(Pageable pageable);
}
