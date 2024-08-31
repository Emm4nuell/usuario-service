package br.com.usuario_service.application.port.out;

import br.com.usuario_service.application.domain.model.ParentescoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFindAllParentescoService {
    Page<ParentescoModel> execute(Pageable pageable);
}
