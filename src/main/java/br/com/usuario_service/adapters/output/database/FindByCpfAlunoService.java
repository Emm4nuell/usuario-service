package br.com.usuario_service.adapters.output.database;

import br.com.usuario_service.adapters.output.database.repository.IAlunoRepository;
import br.com.usuario_service.application.domain.model.AlunoModel;
import br.com.usuario_service.application.port.out.IFindByCpfAlunoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FindByCpfAlunoService implements IFindByCpfAlunoService {

    private final IAlunoRepository iAlunoRepository;
    private final ObjectMapper mapper;

    @Override
    public Optional<AlunoModel> execute(String cpf) {
        return Optional.ofNullable(mapper.convertValue(iAlunoRepository.findByCpf(cpf), AlunoModel.class));
    }
}
