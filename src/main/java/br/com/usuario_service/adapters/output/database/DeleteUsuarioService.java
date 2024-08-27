package br.com.usuario_service.adapters.output.database;

import br.com.usuario_service.adapters.output.database.repository.IUsuarioRepository;
import br.com.usuario_service.application.port.out.IDeleteUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUsuarioService implements IDeleteUsuarioService {

    private final IUsuarioRepository iUsuarioRepository;

    @Override
    public void execute(Long id) {
        iUsuarioRepository.deleteById(id);
    }
}
