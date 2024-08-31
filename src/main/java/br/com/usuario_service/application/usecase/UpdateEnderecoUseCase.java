package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.domain.model.EnderecoModel;
import br.com.usuario_service.application.port.in.IUpdateEnderecoUseCase;
import br.com.usuario_service.application.port.out.IFindByIdEnderecoService;
import br.com.usuario_service.application.port.out.IUpdateEnderecoService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class UpdateEnderecoUseCase implements IUpdateEnderecoUseCase {

    private final IUpdateEnderecoService iUpdateEnderecoService;
    private final IFindByIdEnderecoService iFindByIdEnderecoService;

    @Override
    public EnderecoModel execute(Long id, EnderecoModel model) {
        var endereco = iFindByIdEnderecoService.execute(id).orElseThrow(()->
                new NotFoundException("Endereco nao localizado na base de dados. ID: " + id));
        model.setId(endereco.getId());
        return iUpdateEnderecoService.execute(model);
    }
}
