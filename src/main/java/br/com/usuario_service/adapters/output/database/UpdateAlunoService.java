package br.com.usuario_service.adapters.output.database;

import br.com.usuario_service.adapters.output.database.entity.AlunoEntity;
import br.com.usuario_service.adapters.output.database.repository.IAlunoRepository;
import br.com.usuario_service.application.domain.model.AlunoModel;
import br.com.usuario_service.application.port.out.IUpdateAlunoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateAlunoService implements IUpdateAlunoService {

    private final IAlunoRepository iAlunoRepository;
    private final ObjectMapper mapper;

    @Override
    public AlunoModel execute(AlunoModel model) {
        var entity = iAlunoRepository.save(mapper.convertValue(model, AlunoEntity.class));
        return mapper.convertValue(entity, AlunoModel.class);
    }
}
