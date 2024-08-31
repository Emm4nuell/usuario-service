package br.com.usuario_service.adapters.output.database;

import br.com.usuario_service.adapters.output.database.repository.IParentescoRepository;
import br.com.usuario_service.application.domain.model.ParentescoModel;
import br.com.usuario_service.application.port.out.IFindParentescoByUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FindParentescoByUsuarioService implements IFindParentescoByUsuarioService {

    private final IParentescoRepository iParentescoRepository;
    private final ObjectMapper mapper;

    @Override
    public List<ParentescoModel> execute(Long id) {
        return iParentescoRepository.findEnderecosByUsuarioIdNative(id).stream().map(x ->
                mapper.convertValue(x , ParentescoModel.class)).collect(Collectors.toList());
    }
}
