package br.com.usuario_service.adapters.output.database;

import br.com.usuario_service.application.port.out.IDeleteEnderecoService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteEnderecoService implements IDeleteEnderecoService {
    @Override
    public void execute(Long id) {

    }
}
