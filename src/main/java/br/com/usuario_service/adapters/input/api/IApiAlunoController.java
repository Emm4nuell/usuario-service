package br.com.usuario_service.adapters.input.api;


import br.com.usuario_service.adapters.input.api.request.RequestAluno;
import br.com.usuario_service.adapters.input.api.response.ResponseAluno;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("v1/integraedu/")
public interface IApiAlunoController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("aluno/create/{id}")
    ResponseEntity<ResponseAluno> create(@PathVariable("id") Long id, @RequestBody RequestAluno request);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("aluno/{id}")
    ResponseEntity<ResponseAluno> findById(@PathVariable("id") Long id);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("alunos")
    ResponseEntity<Page<ResponseAluno>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "sort", defaultValue = "nome") String sort,
            @RequestParam(name = "direction", defaultValue = "asc") String direction
    );

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("aluno/update/{id}")
    ResponseEntity<ResponseAluno> update(@PathVariable("id") Long id, @RequestBody RequestAluno request);

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("aluno/delete/{id}")
    ResponseEntity<Void> delete(Long id);

}
