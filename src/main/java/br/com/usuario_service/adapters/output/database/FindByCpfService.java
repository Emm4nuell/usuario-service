package br.com.usuario_service.adapters.output.database;

import br.com.usuario_service.adapters.output.database.repository.IUsuarioRepository;
import br.com.usuario_service.application.domain.model.UsuarioModel;
import br.com.usuario_service.application.port.out.IFindByCpfService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FindByCpfService implements IFindByCpfService {

    private final IUsuarioRepository iUsuarioRepository;
    private final ObjectMapper mapper;

    @Override
    public Optional<UsuarioModel> execute(String cpf) {
        var entity = iUsuarioRepository.findByCpf(cpf);
        return Optional.ofNullable(mapper.convertValue(entity, UsuarioModel.class));
    }
}
