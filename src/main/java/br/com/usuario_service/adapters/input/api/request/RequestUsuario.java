package br.com.usuario_service.adapters.input.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUsuario {
    private String nome;
    private String email;
    private String cpf;
    private String rg;
    private String contato;
    private String celular;
    private String data_nascimento;
}
