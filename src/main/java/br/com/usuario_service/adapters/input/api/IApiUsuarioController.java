package br.com.usuario_service.adapters.input.api;

import br.com.usuario_service.adapters.input.api.request.RequestParentesco;
import br.com.usuario_service.adapters.input.api.request.RequestUsuario;
import br.com.usuario_service.adapters.input.api.response.ResponseParentesco;
import br.com.usuario_service.adapters.input.api.response.ResponseUsuario;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ResponseParentesco> createParentesco(@PathVariable("id") Long id, @RequestBody RequestParentesco request);

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("usuario/endereco/update/{id}")
    public ResponseEntity<ResponseParentesco> updateParentesco(@PathVariable("id") Long id, @RequestBody RequestParentesco request);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("usuario/enderecos")
    public ResponseEntity<Page<ResponseParentesco>> findByIdParentesco(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "sort", defaultValue = "nome") String sort,
            @RequestParam(name = "direction", defaultValue = "asc") String direction);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("usuario/endereco/{id}")
    public ResponseEntity<ResponseParentesco> findByIdEndereco(@PathVariable("id") Long id);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("usuario/enderecousuario/{id}")
    public ResponseEntity<List<ResponseParentesco>> findByIdParentescoAndUsuario(@PathVariable("id") Long id);

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("usuario/endereco/delete/{id}")
    public ResponseEntity<Void> deleteParentesco(@PathVariable("id") Long id);
}
