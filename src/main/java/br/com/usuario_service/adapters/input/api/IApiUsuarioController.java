package br.com.usuario_service.adapters.input.api;

import br.com.usuario_service.adapters.input.api.request.RequestUsuario;
import br.com.usuario_service.adapters.input.api.response.ResponseUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("v1/integraedu/usuario/")
public interface IApiUsuarioController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("create")
    public ResponseEntity<ResponseUsuario> create(@RequestBody RequestUsuario request);
}
