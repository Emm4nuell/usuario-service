package br.com.usuario_service.adapters.output.database.entity;

import br.com.usuario_service.infrastructure.constants.ParentescoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_parentesco")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentescoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Enumerated(EnumType.STRING)
    private ParentescoEnum parentesco;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
//    @JsonIgnore
    private UsuarioEntity usuario;
}
