package br.com.usuario_service.application.port.in;

import br.com.usuario_service.application.domain.model.AlunoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFindAllAlunoUseCase {
    Page<AlunoModel> execute(Pageable pageable);
}
