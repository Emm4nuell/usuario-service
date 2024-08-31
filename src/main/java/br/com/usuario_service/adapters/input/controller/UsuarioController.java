package br.com.usuario_service.adapters.input.controller;

import br.com.usuario_service.adapters.input.api.IApiUsuarioController;
import br.com.usuario_service.adapters.input.api.request.RequestParentesco;
import br.com.usuario_service.adapters.input.api.request.RequestUsuario;
import br.com.usuario_service.adapters.input.api.response.ResponseParentesco;
import br.com.usuario_service.adapters.input.api.response.ResponseUsuario;
import br.com.usuario_service.application.domain.model.ParentescoModel;
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
    private final ICreateParentescoUseCase iCreateParentescoUseCase;
    private final IUpdateParentescoUseCase iUpdateParentescoUseCase;
    private final IFindByIdParentescoUseCase iFindByIdParentescoUseCase;
    private final IFindAllParentescoUseCase iFindAllParentescoUseCase;
    private final IFindParentescoByUsuarioUseCase iFindParentescoByUsuarioUseCase;
    private final IDeleteParentescoUseCase iDeleteParentescoUseCase;
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
    public ResponseEntity<ResponseParentesco> createParentesco(Long id, RequestParentesco request) {
        var response = iCreateParentescoUseCase.execute(id, mapper.convertValue(request, ParentescoModel.class));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<ResponseParentesco> updateParentesco(Long id, RequestParentesco request) {
        var domain = iUpdateParentescoUseCase.execute(id, mapper.convertValue(request, ParentescoModel.class));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(domain, ResponseParentesco.class));
    }

    @Override
    public ResponseEntity<Page<ResponseParentesco>> findByIdParentesco(int page, int size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sort));
        Page<ParentescoModel> endereco = iFindAllParentescoUseCase.execute(pageable);
        var response = endereco.getContent().stream().map(x -> mapper.convertValue(x, ResponseParentesco.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(new PageImpl<>(response, pageable, endereco.getTotalElements()));
    }

    @Override
    public ResponseEntity<ResponseParentesco> findByIdEndereco(Long id) {
        var domain = iFindByIdParentescoUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(domain, ResponseParentesco.class));
    }

    @Override
    public ResponseEntity<List<ResponseParentesco>> findByIdParentescoAndUsuario(Long id) {
        List<ResponseParentesco> response = iFindParentescoByUsuarioUseCase.execute(id)
                .stream().map(x -> mapper.convertValue(x, ResponseParentesco.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Void> deleteParentesco(Long id) {
        iDeleteParentescoUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
