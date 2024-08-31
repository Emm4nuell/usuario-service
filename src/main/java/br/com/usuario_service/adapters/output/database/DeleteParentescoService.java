package br.com.usuario_service.adapters.output.database;

import br.com.usuario_service.application.port.out.IDeleteParentescoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteParentescoService implements IDeleteParentescoService {
    @Override
    public void execute(Long id) {

    }
}
