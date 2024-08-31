package br.com.usuario_service.adapters.output.database;

import br.com.usuario_service.adapters.output.database.repository.IParentescoRepository;
import br.com.usuario_service.application.domain.model.ParentescoModel;
import br.com.usuario_service.application.port.out.IFindAllParentescoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FindAllParentescoService implements IFindAllParentescoService {

    private final IParentescoRepository iParentescoRepository;
    private final ObjectMapper mapper;

    @Override
    public Page<ParentescoModel> execute(Pageable pageable) {
        var entity = iParentescoRepository.findAll(pageable);
        List<ParentescoModel> endereco = entity.getContent().stream().map(x -> mapper.convertValue(x, ParentescoModel.class)).collect(Collectors.toList());
        return new PageImpl<>(endereco, pageable, entity.getTotalElements());
    }
}
