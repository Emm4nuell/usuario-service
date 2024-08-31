package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.ResourceNotFoundException;
import br.com.usuario_service.application.domain.model.EnderecoModel;
import br.com.usuario_service.application.port.in.IFindAllEnderecoUseCase;
import br.com.usuario_service.application.port.out.IFindAllEnderecoService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@AllArgsConstructor
public class FindAllEnderecoUseCase implements IFindAllEnderecoUseCase {

    private final IFindAllEnderecoService iFindAllEnderecoService;

    @Override
    public Page<EnderecoModel> execute(Pageable pageable) {
        var endereco = iFindAllEnderecoService.execute(pageable);
        if (endereco.isEmpty()){
            throw new ResourceNotFoundException("Ausencia de dados para mostrar.");
        }
        return endereco;
    }
}
