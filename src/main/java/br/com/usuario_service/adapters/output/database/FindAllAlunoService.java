package br.com.usuario_service.adapters.output.database;

import br.com.usuario_service.adapters.output.database.repository.IAlunoRepository;
import br.com.usuario_service.application.domain.model.AlunoModel;
import br.com.usuario_service.application.port.out.IFindAllAlunoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FindAllAlunoService implements IFindAllAlunoService {

    private final IAlunoRepository iAlunoRepository;
    private final ObjectMapper mapper;

    @Override
    public Page<AlunoModel> execute(Pageable pageable) {
        var entitys = iAlunoRepository.findAll(pageable);
        var models = entitys.getContent().stream().map(x ->
                mapper.convertValue(x, AlunoModel.class)).collect(Collectors.toList());
        return new PageImpl<>(models, pageable, entitys.getTotalElements());
    }
}
