package br.com.usuario_service.adapters.output.database;

import br.com.usuario_service.adapters.output.database.entity.EnderecoEntity;
import br.com.usuario_service.adapters.output.database.repository.IEnderecoRepository;
import br.com.usuario_service.application.domain.model.EnderecoModel;
import br.com.usuario_service.application.port.out.ICreateEnderecoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateEnderecoService implements ICreateEnderecoService {

    private final IEnderecoRepository iEnderecoRepository;
    private final ObjectMapper mapper;

    @Override
    public EnderecoModel execute(Long id, EnderecoModel model) {
        var entity = iEnderecoRepository.save(mapper.convertValue(model, EnderecoEntity.class));
        return mapper.convertValue(entity, EnderecoModel.class);
    }
}
