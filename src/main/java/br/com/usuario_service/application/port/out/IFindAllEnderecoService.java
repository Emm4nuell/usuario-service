package br.com.usuario_service.application.port.out;

import br.com.usuario_service.application.domain.model.EnderecoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFindAllEnderecoService {
    Page<EnderecoModel> execute(Pageable pageable);
}
