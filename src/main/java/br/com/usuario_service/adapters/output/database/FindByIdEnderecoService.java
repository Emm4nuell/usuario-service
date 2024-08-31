package br.com.usuario_service.adapters.output.database;

import br.com.usuario_service.adapters.output.database.repository.IEnderecoRepository;
import br.com.usuario_service.application.domain.model.EnderecoModel;
import br.com.usuario_service.application.port.out.IFindByIdEnderecoService;
import br.com.usuario_service.infrastructure.config.UseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FindByIdEnderecoService implements IFindByIdEnderecoService {

    private final IEnderecoRepository iEnderecoRepository;
    private final ObjectMapper mapper;

    @Override
    public Optional<EnderecoModel> execute(Long id) {
        return Optional.ofNullable(mapper.convertValue(iEnderecoRepository.findById(id), EnderecoModel.class));
    }
}
