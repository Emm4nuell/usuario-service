package br.com.usuario_service.application.port.in;

import br.com.usuario_service.application.domain.model.ParentescoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFindAllParentescoUseCase {
    Page<ParentescoModel> execute(Pageable pageable);
}
