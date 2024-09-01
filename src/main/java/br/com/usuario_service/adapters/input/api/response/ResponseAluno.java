package br.com.usuario_service.adapters.input.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAluno {
    private Long id;
    private String nome;
    private String cpf;
    private String rg;
    private String email;
    private Date data_nascimento;
    private String sexo;
    private LocalDateTime data_cadastro;
}
