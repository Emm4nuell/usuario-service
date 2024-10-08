package br.com.usuario_service.adapters.input.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestParentesco {
    private String nome;
    private String telefone;
    private String celular;
    private String cpf;
    private String rg;
    private String email;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String estado;
    private String parentesco;
}
