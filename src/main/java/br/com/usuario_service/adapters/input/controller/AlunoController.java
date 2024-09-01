package br.com.usuario_service.adapters.input.controller;

import br.com.usuario_service.adapters.input.api.IApiAlunoController;
import br.com.usuario_service.adapters.input.api.request.RequestAluno;
import br.com.usuario_service.adapters.input.api.response.ResponseAluno;
import br.com.usuario_service.application.domain.model.AlunoModel;
import br.com.usuario_service.application.port.in.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AlunoController implements IApiAlunoController {

    private final ICreateAlunoUseCase iCreateAlunoUseCase;
    private final IFindByIdAlunoUseCase iFindByIdAlunoUseCase;
    private final IFindAllAlunoUseCase iFindAllAlunoUseCase;
    private final IUpdateAlunoUseCase iUpdateAlunoUseCase;
    private final IDeleteAlunoUseCase iDeleteAlunoUseCase;
    private final ObjectMapper mapper;

    @Override
    public ResponseEntity<ResponseAluno> create(Long id, RequestAluno request) {
        var model = iCreateAlunoUseCase.execute(id, mapper.convertValue(request, AlunoModel.class));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(model.getId())
                .toUri();
        return ResponseEntity.created(uri).body(mapper.convertValue(model, ResponseAluno.class));
    }

    @Override
    public ResponseEntity<ResponseAluno> findById(Long id) {
        var entity = iFindByIdAlunoUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(entity, ResponseAluno.class));
    }

    @Override
    public ResponseEntity<Page<ResponseAluno>> findAll(int page, int size, String sort, String direction) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.fromString(direction), sort));
        var models = iFindAllAlunoUseCase.execute(pageable);
        var response = models.getContent().stream().map(x ->
                mapper.convertValue(x, ResponseAluno.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(new PageImpl<>(response, pageable, models.getTotalElements()));
    }

    @Override
    public ResponseEntity<ResponseAluno> update(Long id, RequestAluno request) {
        var model = iUpdateAlunoUseCase.execute(id, mapper.convertValue(request, AlunoModel.class));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(model, ResponseAluno.class));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        iDeleteAlunoUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
