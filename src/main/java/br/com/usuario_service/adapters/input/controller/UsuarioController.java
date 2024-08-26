package br.com.usuario_service.adapters.input.controller;

import br.com.usuario_service.adapters.input.api.IApiUsuarioController;
import br.com.usuario_service.adapters.input.api.request.RequestUsuario;
import br.com.usuario_service.adapters.input.api.response.ResponseUsuario;
import br.com.usuario_service.application.domain.model.UsuarioModel;
import br.com.usuario_service.application.port.in.ICreateUsuarioUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
public class UsuarioController implements IApiUsuarioController {

    private final ICreateUsuarioUseCase iCreateUsuarioUseCase;
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
}
