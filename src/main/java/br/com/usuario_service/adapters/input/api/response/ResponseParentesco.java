package br.com.usuario_service.adapters.input.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseParentesco {
    private Long id;
    private String nome;
    private String telefone;
    private String celular;
    private String cpf;
    private String rg;
    private String email;
    private LocalDateTime data_cadastro;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String estado;
    private String parentesco;
}
