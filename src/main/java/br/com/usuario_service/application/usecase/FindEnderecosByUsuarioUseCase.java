package br.com.usuario_service.application.usecase;

import br.com.usuario_service.application.domain.exception.NotFoundException;
import br.com.usuario_service.application.domain.model.EnderecoModel;
import br.com.usuario_service.application.port.in.IFindEnderecosByUsuarioUseCase;
import br.com.usuario_service.application.port.out.IFindEnderecosByUsuarioService;
import br.com.usuario_service.infrastructure.config.UseCase;
import lombok.AllArgsConstructor;

import java.util.List;

@UseCase
@AllArgsConstructor
public class FindEnderecosByUsuarioUseCase implements IFindEnderecosByUsuarioUseCase {

    private final IFindEnderecosByUsuarioService iFindEnderecosByUsuarioService;

    @Override
    public List<EnderecoModel> execute(Long id) {
        var endereco = iFindEnderecosByUsuarioService.execute(id);
        if (endereco.isEmpty()){
            throw new NotFoundException("Endereco nao localizado na base de dados ID: " + id);
        }else {
            return endereco;
        }
    }
}
