package br.com.usuario_service.adapters.input.controller;

import br.com.usuario_service.adapters.input.api.IApiUsuarioController;
import br.com.usuario_service.adapters.input.api.request.RequestEndereco;
import br.com.usuario_service.adapters.input.api.request.RequestUsuario;
import br.com.usuario_service.adapters.input.api.response.ResponseEndereco;
import br.com.usuario_service.adapters.input.api.response.ResponseUsuario;
import br.com.usuario_service.application.domain.model.EnderecoModel;
import br.com.usuario_service.application.domain.model.UsuarioModel;
import br.com.usuario_service.application.port.in.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class UsuarioController implements IApiUsuarioController {

    private final ICreateUsuarioUseCase iCreateUsuarioUseCase;
    private final IFindByIdUsuarioUseCase iFindByIdUsuarioUseCase;
    private final IDeleteUsuarioUseCase iDeleteUsuarioUseCase;
    private final IFindAllUsuariosUseCase iFindAllUsuariosUseCase;
    private final IUpdateUsuarioUseCase iUpdateUsuarioUseCase;
    private final ICreateEnderecoUseCase iCreateEnderecoUseCase;
    private final IUpdateEnderecoUseCase iUpdateEnderecoUseCase;
    private final IFindByIdEnderecoUseCase iFindByIdEnderecoUseCase;
    private final IFindAllEnderecoUseCase iFindAllEnderecoUseCase;
    private final IFindEnderecosByUsuarioUseCase iFindEnderecosByUsuarioUseCase;
    private final IDeleteEnderecoUseCase iDeleteEnderecoUseCase;
    private final ObjectMapper mapper;

    @Override
    public ResponseEntity<ResponseUsuario> create(RequestUsuario request) {
        var model = iCreateUsuarioUseCase.execute(
                mapper.convertValue(request, UsuarioModel.class));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(model.getId())
                .toUri();
        return ResponseEntity.created(uri).body(mapper.convertValue(model, ResponseUsuario.class));
    }

    @Override
    public ResponseEntity<ResponseUsuario> findById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(
                iFindByIdUsuarioUseCase.execute(id), ResponseUsuario.class));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        iDeleteUsuarioUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Page<ResponseUsuario>> findAll(int page, int size, String sort, String direction) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.fromString(direction), sort));
        var domain = iFindAllUsuariosUseCase.execute(pageable);
        var responseList = domain.getContent().stream().map(x -> mapper.convertValue(x, ResponseUsuario.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(new PageImpl<>(responseList, pageable, domain.getTotalElements()));
    }

    @Override
    public ResponseEntity<ResponseUsuario> update(Long id, RequestUsuario request) {
        var model = iUpdateUsuarioUseCase.execute(id, mapper.convertValue(request, UsuarioModel.class));
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    public ResponseEntity<ResponseEndereco> createEndereco(Long id, RequestEndereco request) {
        var response = iCreateEnderecoUseCase.execute(id, mapper.convertValue(request, EnderecoModel.class));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<ResponseEndereco> updateEndereco(Long id, RequestEndereco request) {
        var domain = iUpdateEnderecoUseCase.execute(id, mapper.convertValue(request, EnderecoModel.class));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(domain, ResponseEndereco.class));
    }

    @Override
    public ResponseEntity<Page<ResponseEndereco>> findByIdEndereco(int page, int size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sort));
        Page<EnderecoModel> endereco = iFindAllEnderecoUseCase.execute(pageable);
        var response = endereco.getContent().stream().map(x -> mapper.convertValue(x, ResponseEndereco.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(new PageImpl<>(response, pageable, endereco.getTotalElements()));
    }

    @Override
    public ResponseEntity<ResponseEndereco> findByIdEndereco(Long id) {
        var domain = iFindByIdEnderecoUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(domain, ResponseEndereco.class));
    }

    @Override
    public ResponseEntity<List<ResponseEndereco>> findByIdEnderecoAndUsuario(Long id) {
        List<ResponseEndereco> response = iFindEnderecosByUsuarioUseCase.execute(id)
                .stream().map(x -> mapper.convertValue(x, ResponseEndereco.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Void> deleteEndereco(Long id) {
        iDeleteEnderecoUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
