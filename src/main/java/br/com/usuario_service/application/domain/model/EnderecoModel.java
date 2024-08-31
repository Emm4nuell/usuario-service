package br.com.usuario_service.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoModel {
    private Long id;
    private String nome;
    private String telefone;
    private String celular;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String estado;
    private String parentesco;
    private UsuarioModel usuario;
}
