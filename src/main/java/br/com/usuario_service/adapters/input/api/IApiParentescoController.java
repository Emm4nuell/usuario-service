package br.com.usuario_service.adapters.input.api;

import br.com.usuario_service.adapters.input.api.request.RequestParentesco;
import br.com.usuario_service.adapters.input.api.response.ResponseParentesco;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("v1/integraedu/")
public interface IApiParentescoController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("endereco/create/{id}")
    public ResponseEntity<ResponseParentesco> createParentesco(@PathVariable("id") Long id, @RequestBody RequestParentesco request);

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("endereco/update/{id}")
    public ResponseEntity<ResponseParentesco> updateParentesco(@PathVariable("id") Long id, @RequestBody RequestParentesco request);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("enderecos")
    public ResponseEntity<Page<ResponseParentesco>> findByIdParentesco(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "sort", defaultValue = "nome") String sort,
            @RequestParam(name = "direction", defaultValue = "asc") String direction);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("endereco/{id}")
    public ResponseEntity<ResponseParentesco> findByIdEndereco(@PathVariable("id") Long id);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("enderecousuario/{id}")
    public ResponseEntity<List<ResponseParentesco>> findByIdParentescoAndUsuario(@PathVariable("id") Long id);

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("endereco/delete/{id}")
    public ResponseEntity<Void> deleteParentesco(@PathVariable("id") Long id);
}
