package br.com.usuario_service.application.port.out;

import br.com.usuario_service.application.domain.model.AlunoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFindAllAlunoService {
    Page<AlunoModel> execute(Pageable pageable);
}
