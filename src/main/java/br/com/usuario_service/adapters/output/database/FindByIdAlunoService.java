package br.com.usuario_service.adapters.output.database;

import br.com.usuario_service.adapters.output.database.repository.IAlunoRepository;
import br.com.usuario_service.application.domain.model.AlunoModel;
import br.com.usuario_service.application.port.out.IFindByIdAlunoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FindByIdAlunoService implements IFindByIdAlunoService {

    private final IAlunoRepository iAlunoRepository;
    private final ObjectMapper mapper;

    @Override
    public Optional<AlunoModel> execute(Long id) {
        return Optional.ofNullable(mapper.convertValue(iAlunoRepository.findById(id), AlunoModel.class));
    }
}
