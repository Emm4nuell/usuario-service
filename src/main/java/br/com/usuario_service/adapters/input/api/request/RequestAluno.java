package br.com.usuario_service.adapters.input.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAluno {
    private String nome;
    private String cpf;
    private String rg;
    private String email;
    private Date data_nascimento;
    private String sexo;
}
