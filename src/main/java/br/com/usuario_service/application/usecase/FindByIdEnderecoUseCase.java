package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.domain.model.EnderecoModel;
import br.com.usuario_service.application.port.in.IFindByIdEnderecoUseCase;
import br.com.usuario_service.application.port.out.IFindByIdEnderecoService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class FindByIdEnderecoUseCase implements IFindByIdEnderecoUseCase {

    private final IFindByIdEnderecoService iFindByIdEnderecoService;

    @Override
    public EnderecoModel execute(Long id) {
        return iFindByIdEnderecoService.execute(id).orElseThrow(() ->
                new NotFoundException("Usuario nao localizado na base de dados ID: " + id));
    }
}
