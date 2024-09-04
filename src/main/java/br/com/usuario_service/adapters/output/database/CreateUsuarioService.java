package br.com.usuario_service.adapters.output.database;

import br.com.usuario_service.adapters.output.database.entity.UsuarioEntity;
import br.com.usuario_service.adapters.output.database.repository.IUsuarioRepository;
import br.com.usuario_service.application.domain.model.UsuarioModel;
import br.com.usuario_service.application.port.out.ICreateUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUsuarioService implements ICreateUsuarioService {

    private final IUsuarioRepository iUsuarioRepository;
    private final ObjectMapper mapper;
    private final KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public UsuarioModel execute(UsuarioModel model) {
        var entity = iUsuarioRepository.save(mapper.convertValue(model, UsuarioEntity.class));
        return mapper.convertValue(entity, UsuarioModel.class);
    }
}
