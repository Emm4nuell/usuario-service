package br.com.usuario_service.adapters.output.database;

import br.com.usuario_service.adapters.output.database.repository.IEnderecoRepository;
import br.com.usuario_service.adapters.output.database.repository.IUsuarioRepository;
import br.com.usuario_service.application.domain.model.EnderecoModel;
import br.com.usuario_service.application.port.out.IFindAllEnderecoService;
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
public class FindAllEnderecoService implements IFindAllEnderecoService {

    private final IEnderecoRepository iEnderecoRepository;
    private final ObjectMapper mapper;

    @Override
    public Page<EnderecoModel> execute(Pageable pageable) {
        var entity = iEnderecoRepository.findAll(pageable);
        List<EnderecoModel> endereco = entity.getContent().stream().map(x -> mapper.convertValue(x, EnderecoModel.class)).collect(Collectors.toList());
        return new PageImpl<>(endereco, pageable, entity.getTotalElements());
    }
}
