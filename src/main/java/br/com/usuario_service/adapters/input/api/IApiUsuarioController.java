package br.com.usuario_service.adapters.input.api;

import br.com.usuario_service.adapters.input.api.request.RequestEndereco;
import br.com.usuario_service.adapters.input.api.request.RequestUsuario;
import br.com.usuario_service.adapters.input.api.response.ResponseEndereco;
import br.com.usuario_service.adapters.input.api.response.ResponseUsuario;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("v1/integraedu/")
public interface IApiUsuarioController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("usuario/create")
    public ResponseEntity<ResponseUsuario> create(@RequestBody RequestUsuario request);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("usuario/{id}")
    public ResponseEntity<ResponseUsuario> findById(@PathVariable("id") Long id);

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("usuario/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("usuarios")
    public ResponseEntity<Page<ResponseUsuario>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "sort", defaultValue = "nome") String sort,
            @RequestParam(value = "direction", defaultValue = "asc") String direction);

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("usuario/update/{id}")
    public ResponseEntity<ResponseUsuario> update(@PathVariable("id") Long id, @RequestBody RequestUsuario request);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("usuario/endereco/create/{id}")
    public ResponseEntity<ResponseEndereco> createEndereco(@PathVariable("id") Long id, @RequestBody RequestEndereco request);

}
