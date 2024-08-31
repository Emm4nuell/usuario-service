package br.com.usuario_service.adapters.output.database;

import br.com.usuario_service.adapters.output.database.entity.ParentescoEntity;
import br.com.usuario_service.adapters.output.database.repository.IParentescoRepository;
import br.com.usuario_service.application.domain.model.ParentescoModel;
import br.com.usuario_service.application.port.out.ICreateParentescoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateParentescoService implements ICreateParentescoService {

    private final IParentescoRepository iParentescoRepository;
    private final ObjectMapper mapper;

    @Override
    public ParentescoModel execute(ParentescoModel model) {
        var entity = iParentescoRepository.save(mapper.convertValue(model, ParentescoEntity.class));
        return mapper.convertValue(entity, ParentescoModel.class);
    }
}
