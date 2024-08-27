package br.com.usuario_service.adapters.output.database;

import br.com.usuario_service.adapters.output.database.entity.UsuarioEntity;
import br.com.usuario_service.adapters.output.database.repository.IUsuarioRepository;
import br.com.usuario_service.application.domain.model.UsuarioModel;
import br.com.usuario_service.application.port.out.IFindAllUsuariosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FindAllUsuariosService implements IFindAllUsuariosService {

    private final IUsuarioRepository iUsuarioRepository;
    private final ObjectMapper mapper;

    @Override
    public Page<UsuarioModel> execute(Pageable pageable) {
        Page<UsuarioEntity> usuarios = iUsuarioRepository.findAll(pageable);
        var models = usuarios.getContent().stream().map(x -> mapper.convertValue(x, UsuarioModel.class)).collect(Collectors.toList());
        return new PageImpl<>(models, pageable, usuarios.getTotalElements());
    }
}
