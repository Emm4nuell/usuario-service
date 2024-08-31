package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.port.in.IDeleteEnderecoUseCase;
import br.com.usuario_service.application.port.out.IDeleteEnderecoService;
import br.com.usuario_service.application.port.out.IFindByIdEnderecoService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class DeleteEnderecoUseCase implements IDeleteEnderecoUseCase {

    private final IDeleteEnderecoService iDeleteEnderecoService;
    private final IFindByIdEnderecoService iFindByIdEnderecoService;

    @Override
    public void execute(Long id) {
        if (iFindByIdEnderecoService.execute(id).isPresent()){
            iDeleteEnderecoService.execute(id);
        }else{
            throw new NotFoundException("Endereco nao localizado na base de dados ID: " + id);
        }
    }
}
