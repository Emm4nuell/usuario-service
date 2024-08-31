package br.com.usuario_service.adapters.output.database;

import br.com.usuario_service.adapters.output.database.repository.IParentescoRepository;
import br.com.usuario_service.application.domain.model.ParentescoModel;
import br.com.usuario_service.application.port.out.IFindByIdParentescoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FindByIdParentescoService implements IFindByIdParentescoService {

    private final IParentescoRepository iParentescoRepository;
    private final ObjectMapper mapper;

    @Override
    public Optional<ParentescoModel> execute(Long id) {
        return Optional.ofNullable(mapper.convertValue(iParentescoRepository.findById(id), ParentescoModel.class));
    }
}
