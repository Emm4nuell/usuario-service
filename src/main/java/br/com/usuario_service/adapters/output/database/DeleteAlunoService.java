package br.com.usuario_service.adapters.output.database;

import br.com.usuario_service.adapters.output.database.repository.IAlunoRepository;
import br.com.usuario_service.application.port.out.IDeleteAlunoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAlunoService implements IDeleteAlunoService {

    private final IAlunoRepository iAlunoRepository;

    @Override
    public void execute(Long id) {
        iAlunoRepository.deleteById(id);
    }
}
