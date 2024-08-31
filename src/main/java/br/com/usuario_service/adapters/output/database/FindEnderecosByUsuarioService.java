package br.com.usuario_service.adapters.output.database;

import br.com.usuario_service.adapters.output.database.repository.IEnderecoRepository;
import br.com.usuario_service.application.domain.model.EnderecoModel;
import br.com.usuario_service.application.port.out.IFindEnderecosByUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FindEnderecosByUsuarioService implements IFindEnderecosByUsuarioService {

    private final IEnderecoRepository iEnderecoRepository;
    private final ObjectMapper mapper;

    @Override
    public List<EnderecoModel> execute(Long id) {
        return iEnderecoRepository.findEnderecosByUsuarioIdNative(id).stream().map(x ->
                mapper.convertValue(x , EnderecoModel.class)).collect(Collectors.toList());
    }
}
