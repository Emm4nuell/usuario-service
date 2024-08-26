package br.com.usuario_service.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioModel {
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String rg;
    private String contato;
    private String celular;
    private String data_nascimento;
    private LocalDateTime data_created;
    private boolean status;
}
